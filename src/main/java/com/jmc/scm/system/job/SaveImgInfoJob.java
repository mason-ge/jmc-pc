package com.jmc.scm.system.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.dorado.core.Configure;
import com.jmc.scm.framework.dao.BaseDao;
import com.jmc.scm.framework.dao.impl.BaseDaoImpl;
import com.jmc.scm.system.model.SysFile;
import com.jmc.scm.system.service.ClientCfgService;
import com.jmc.scm.system.service.FTPService;
import com.jmc.scm.system.service.FileService;
import com.jmc.scm.system.service.impl.ClientCfgServiceImpl;
import com.jmc.scm.system.service.impl.FTPServiceImpl;
import com.jmc.scm.system.service.impl.FileServiceImpl;

/**
 * 获取FTP图片信息，存到数据库中
 * 
 * @author Mason_Ge
 * 
 */
@Component("saveImgInfoJob")
@Scope("prototype")
public class SaveImgInfoJob extends BaseDaoImpl implements Job {

	@Autowired
	@Qualifier(FTPServiceImpl.BEAN_ID)
	private FTPService ftpService;

	@Autowired
	@Qualifier(FileServiceImpl.BEAN_ID)
	private FileService fileService;

	@Autowired
	@Qualifier(BaseDaoImpl.BEAN_ID)
	private BaseDao baseDao;

	@Autowired
	@Qualifier(ClientCfgServiceImpl.BEAN_ID)
	private ClientCfgService clientCfgService;

	@Value("${scm.custom.log.enable}")
	private String customLogEnable;

	private static String fileOpenPath = Configure
			.getString("jmc.scm.ftp.fileOpen.httpPath");

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		FTPClient ftpClient;
		FTPFile[] files;
		String fileName, filePath, bizCode;
		List<Map<String, Object>> listCheck = new ArrayList<>();
		List<Map<String, Object>> listFiles2Save = new ArrayList<>();

		List<SysFile> listFile;
		SysFile sysFile;
		String key1, key2;
		Map<String, Object> mapPut = new HashMap<>();
		try {
			if (Configure.getBoolean("sys.job.swich")) {
				// 获取所有客户端号
				List<String> listClients = clientCfgService.getClientsWithNoRepeatFtp();
				if (listClients != null && !listClients.isEmpty()) {
					for (String str : listClients) {
						//判断该客户端配置的FTP服务器有没有重复，有的话不用再保存,否则会造成错误重复数据
						ftpClient = ftpService
								.newFTPClientInstanceByClient(str);
						files = ftpClient.listFiles("/Thumbnails");
						for (FTPFile file : files) {
							mapPut = new HashMap<>();
							fileName = file.getName();
							filePath = "Thumbnails/" + fileName;

							mapPut.put("abPath", filePath);
							mapPut.put("bizCode", fileName.split("[.]")[0]);
							mapPut.put("fileName", fileName);
							listCheck.add(mapPut);
						}
						listFiles2Save.addAll(listCheck);
						// 查询相同bizeCode和绝对路径的已经存在的数据，则不需要保存
						listFile = fileService.findListByListParam(listCheck);
						if (listFile != null && !listFile.isEmpty()) {
							for (SysFile t : listFile) {
								for (Map<String, Object> file : listCheck) {
									fileName = file.get("fileName").toString();
									filePath = file.get("abPath").toString();
									bizCode = file.get("bizCode").toString();
									key1 = bizCode + "," + filePath;
									key2 = t.getBizCode() + ","
											+ t.getFileAbsolutePath();
									if (key1.equals(key2)) {
										// 相同key的，remove掉这条数据，不保存
										listFiles2Save.remove(file);
									}
								}
							}
						}
						for (Map<String, Object> t : listFiles2Save) {
							sysFile = new SysFile();
							fileName = t.get("fileName").toString();
							filePath = t.get("abPath").toString();
							bizCode = t.get("bizCode").toString();
							sysFile.setBizCode(bizCode);
							sysFile.setFileName(fileName);
							sysFile.setFileAbsolutePath(filePath);
							sysFile.setAttr1(fileName.split("-")[0]);
							sysFile.setCreatedBy(ContextHolder
									.getLoginUserName());
							sysFile.setCreatedD(new Date());
							baseDao.save(sysFile);
							sysFile.setFileHttpPath(fileOpenPath
									+ sysFile.getPkId() + "/0");
							baseDao.update(sysFile);
						}
						listFiles2Save.clear();
						listCheck.clear();
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

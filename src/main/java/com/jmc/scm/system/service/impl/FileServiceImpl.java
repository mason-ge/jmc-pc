package com.jmc.scm.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jmc.scm.system.dao.FileDao;
import com.jmc.scm.system.model.SysFile;
import com.jmc.scm.system.service.FTPService;
import com.jmc.scm.system.service.FileService;

@Service("fileServiceImpl")
public class FileServiceImpl implements FileService {

	public static final String BEAN_ID = "fileServiceImpl";

	@Resource
	private FileDao fileDao;

	@Autowired
	@Qualifier(FTPServiceImpl.BEAN_ID)
	private FTPService ftpService;

	@Override
	public Map<String, Object> saveAtt(Map<String, Object> params) {
		return fileDao.saveAtt(params);
	}

	@Override
	public List<SysFile> findFile(String bizCode) {
		return fileDao.queryFile(bizCode);
	}

	@Override
	public SysFile findFile(Map<String, Object> parameter) {
		return fileDao.findFile(parameter);
	}

	@Override
	public void deleteAtt(Map<String, Object> params) {
		fileDao.deleteAtt(params);
	}

	@Override
	public Map<String, Object> findPathUriMappingByUris(List<Object> uris) {
		List<Map<String, Object>> buffer;
		Map<String, Object> result;

		buffer = this.fileDao.findListPathByUris(uris);
		result = new HashMap<>(buffer.size());

		for (Map<String, Object> mapping : buffer) {
			result.put(mapping.get("fileHttpPath").toString(),
					mapping.get("fileAbsolutePath"));
		}

		return result;
	}

	@Override
	public List<SysFile> findListImagesByProdCode(String prodCode) {
		Map<String, String> fileNames;
		List<SysFile> files;
		SysFile file;

		fileNames = this.ftpService.findImageByProdCode(prodCode);

		files = new ArrayList<>(fileNames.size());
		for (Entry<String, String> fileName : fileNames.entrySet()) {
			file = new SysFile();
			file.setFileName(fileName.getKey());
			file.setFileHttpPath(fileName.getValue());

			files.add(file);
		}

		return files;
	}

	@Override
	public List<SysFile> findListImagesByProdCodeByCodes(List<String> list) {
		Map<String, String> fileNames;
		List<SysFile> files;
		SysFile file;

		fileNames = this.ftpService.findImageByProdCodeByCodes(list);

		files = new ArrayList<>(fileNames.size());
		for (Entry<String, String> fileName : fileNames.entrySet()) {
			file = new SysFile();
			file.setFileName(fileName.getKey());
			file.setFileHttpPath(fileName.getValue());
			files.add(file);
		}

		return files;
	}

	@Override
	public List<SysFile> findListByListParam(List<Map<String, Object>> list) {
		return fileDao.findListByListParam(list);
	}

}

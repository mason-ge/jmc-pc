package com.jmc.scm.system.view;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.uploader.DownloadFile;
import com.bstek.dorado.uploader.UploadFile;
import com.bstek.dorado.uploader.annotation.FileProvider;
import com.bstek.dorado.uploader.annotation.FileResolver;
import com.bstek.dorado.web.DoradoContext;
import com.jmc.scm.framework.service.BaseService;
import com.jmc.scm.framework.service.impl.BaseServiceImpl;
import com.jmc.scm.system.model.SysFile;
import com.jmc.scm.system.service.FTPService;
import com.jmc.scm.system.service.FileService;
import com.jmc.scm.system.service.impl.FTPServiceImpl;
import com.jmc.scm.system.service.impl.FileServiceImpl;

/**
 * 文件上传下载PR
 * 
 * @author 三影塔
 * 
 */
@Component("filePR")
public class FilePR {

	@Autowired
	@Qualifier(FTPServiceImpl.BEAN_ID)
	private FTPService ftpService;

	@Autowired
	@Qualifier(BaseServiceImpl.BEAN_ID)
	BaseService baseService;

	@Autowired
	@Qualifier(FileServiceImpl.BEAN_ID)
	FileService fileService;

	/**
	 * 文件上传
	 * 
	 * @param uploadFile
	 * @param params
	 * @return
	 */
	@FileResolver
	public Map<String, Object> uploadFile(UploadFile uploadFile,
			Map<String, Object> params) {
		return ftpService.uploadFile(uploadFile);
	}

	/**
	 * 文件下载
	 * 
	 * @param pathName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@FileProvider
	public DownloadFile downloadFile(Map<String, Object> params)
			throws UnsupportedEncodingException {
		return ftpService.downloadFileForDorado(params);
	}

	/**
	 * 文件查询
	 * 
	 * @param bizCode
	 * @return
	 */
	@DataProvider
	public List<SysFile> getFile(String bizCode) {
		return fileService.findFile(bizCode);
	}

	/**
	 * 商品图片查询
	 * 
	 * @param prodCode
	 *            商品编码
	 * @return List 匹配的商品图片
	 */
	@DataProvider
	public List<SysFile> getListProdImage(String prodCode) {
		List<SysFile> file;
		file = this.fileService.findListImagesByProdCode(prodCode);
		return file;
	}

	/**
	 * 商品图片查询（多个商品编码一次查询）
	 * 
	 * @param list
	 *            商品编码list
	 * @return List 匹配的商品图片
	 */
	@DataProvider
	public List<SysFile> getListProdImageByCodes(List<String> list) {
		List<SysFile> file;
		file = this.fileService.findListImagesByProdCodeByCodes(list);
		return file;
	}

	/**
	 * 保存附件信息
	 * 
	 * @param params
	 */
	@DataResolver
	public Map<String, Object> saveAtt(Map<String, Object> params) {
		return fileService.saveAtt(params);
	}

	/**
	 * 删除附件信息
	 * 
	 * @param params
	 */
	@DataResolver
	public void deleteAtt(Map<String, Object> params) {
		fileService.deleteAtt(params);
	}

	/**
	 * 根据文件名下载(从应用服务器下载)
	 * 
	 * @param parameter
	 * @return
	 * @throws IOException
	 */
	@FileProvider
	public DownloadFile downloadFileFromTct(Map<String, String> params)
			throws IOException {
		String path = null;
		path = DoradoContext.getAttachedServletContext().getRealPath("/");
		String fileName = String.valueOf(params.get("fileName"));
		Base64 base64 = new Base64();
		fileName = new String(base64.decode(fileName), "UTF-8");
		path = path.concat("files/" + fileName);
		// 返回一个文件对象就可以
		return new DownloadFile(new File(path));
	}

}

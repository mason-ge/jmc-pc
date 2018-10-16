package com.jmc.scm.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jmc.scm.system.model.ContentType;
import com.jmc.scm.system.model.SysFile;
import com.jmc.scm.system.service.FTPService;
import com.jmc.scm.system.service.FileService;
import com.jmc.scm.system.service.impl.FTPServiceImpl;
import com.jmc.scm.system.service.impl.FileServiceImpl;

@Controller
@RequestMapping
public class FileOpenController {

	@Autowired
	@Qualifier(FTPServiceImpl.BEAN_ID)
	private FTPService ftpService;

	@Autowired
	@Qualifier(FileServiceImpl.BEAN_ID)
	private FileService fileService;

	@RequestMapping(value = "/open/{uuid}/{index}", method = RequestMethod.GET)
	public void openFile(@PathVariable String uuid, @PathVariable String index,
			HttpServletResponse response) {
		Map<String, Object> parameter = new HashMap<>(1);
		String fileSuffix = null, fileName, pathName = null, contentType = null;
		int dotPosition;
		SysFile alloveFile;

		try {
			parameter.put("pkId", uuid);
			alloveFile = fileService.findFile(parameter);

			pathName = alloveFile.getFileAbsolutePath();
			fileName = alloveFile.getFileName();
			dotPosition = fileName.lastIndexOf(".");

			fileSuffix = fileName.substring(dotPosition);
			contentType = ContentType.getContentTypeByFileType(fileSuffix);
			response.setContentType(contentType);
			OutputStream os = response.getOutputStream();

			ftpService.downloadFile(pathName, os);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

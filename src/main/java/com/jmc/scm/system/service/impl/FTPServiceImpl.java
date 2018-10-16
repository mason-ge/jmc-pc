package com.jmc.scm.system.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axiom.util.base64.Base64Utils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bstek.dorado.core.Configure;
import com.bstek.dorado.uploader.DownloadFile;
import com.bstek.dorado.uploader.UploadFile;
import com.jmc.scm.system.model.ClientCfg;
import com.jmc.scm.system.service.ClientCfgService;
import com.jmc.scm.system.service.FTPService;

@Service("ftpServiceImpl")
public class FTPServiceImpl implements FTPService {

	public static final String BEAN_ID = "ftpServiceImpl";

	private static String ftpAddress = Configure
			.getString("jmc.scm.ftp.address");

	private static int defaultTimeout = ((Long) Configure
			.getLong("jmc.scm.ftp.defaultTimeout")).intValue();

	private static int dataTimeout = ((Long) Configure
			.getLong("jmc.scm.ftp.dataTimeout")).intValue();

	private static int connectTimeout = ((Long) Configure
			.getLong("jmc.scm.ftp.connectTimeout")).intValue();

	@Autowired
	@Qualifier(ClientCfgServiceImpl.BEAN_ID)
	private ClientCfgService clientCfgService;

	@Override
	public Map<String, Object> uploadFile(UploadFile file) {
		FTPClient ftpClient;
		String fileName;
		InputStream inputStream;
		Map<String, Object> result = new HashMap<>();
		try {
			ftpClient = this.newFTPClientInstance();
			ftpClient.setControlEncoding("GBK");// 这里设置编码GBK
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			fileName = file.getFileName();
			inputStream = file.getInputStream();
			ftpClient.changeWorkingDirectory("scm/files/");
			ftpClient.storeFile(new String(fileName.getBytes("GBK"),
					"iso-8859-1"), inputStream);// 编码转换
			result.put("sName", file.getFileName());
			result.put("absolutePath", "scm/files/" + fileName);
			inputStream.close();

			ftpClient.logout();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public OutputStream uploadFile(String pathName) {
		FTPClient ftpClient;
		OutputStream outputStream = null;

		try {
			ftpClient = this.newFTPClientInstance();
			ftpClient.setControlEncoding("GBK");// 这里设置编码GBK
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

			outputStream = ftpClient.storeFileStream(pathName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return outputStream;
	}

	@Override
	public InputStream downloadFile(String pathName) {
		FTPClient ftpClient;
		InputStream inputStream = null;

		try {
			ftpClient = this.newFTPClientInstance();
			ftpClient.setControlEncoding("GBK");
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			inputStream = ftpClient.retrieveFileStream(new String(pathName
					.getBytes("GBK"), "iso-8859-1"));

			ftpClient.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputStream;
	}

	@Override
	public void downloadFile(String pathName, OutputStream outputStream) {
		FTPClient ftpClient;

		try {
			ftpClient = this.newFTPClientInstance();
			ftpClient.setControlEncoding("GBK");
			ftpClient.retrieveFile(new String(pathName.getBytes("GBK"),
					"iso-8859-1"), outputStream);

			outputStream.flush();
			outputStream.close();
			ftpClient.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void downloadFile(String pathName, InputStream inputStream,
			Long fileSize) {
		FTPClient ftpClient;
		FTPFile[] ftpFiles;
		FTPFile ftpFile;
		FTPServiceFileFilter ftpFileFilter;

		try {
			ftpClient = this.newFTPClientInstance();
			ftpFileFilter = FTPServiceFileFilter.newInstance();
			ftpFiles = ftpClient.listFiles(pathName, ftpFileFilter);

			if (ftpFiles.length > 0) {
				ftpFile = ftpFiles[0];
				inputStream = ftpClient.retrieveFileStream(pathName);
				fileSize = ftpFile.getSize();
			} else {
				throw new RuntimeException("文件不存在！");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public DownloadFile downloadFileForDorado(Map<String, Object> params)
			throws UnsupportedEncodingException {
		InputStream inputStream;
		DownloadFile downloadFile;
		String fileName;
		String pathName = String.valueOf(params.get("pathName"));
		Base64 base64 = new Base64();
		pathName = new String(base64.decode(pathName), "UTF-8");
		fileName = pathName.substring(pathName.lastIndexOf('/') + 1);

		inputStream = this.downloadFile(pathName);
		downloadFile = new DownloadFile(pathName, inputStream);
		downloadFile.setName(fileName);
		return downloadFile;
	}

	@Override
	public Map<String, String> findImageByProdCode(String prodCode) {
		Map<String, String> result = new HashMap<>(3);
		final String transferCode = prodCode + '-';
		String image, prefix = "data:image/jpeg;base64,";
		FTPClient ftpClient;
		FTPFile[] files;
		ByteArrayOutputStream bos;

		try {
			ftpClient = this.newFTPClientInstance();
			files = ftpClient.listFiles("/prodImage", new FTPFileFilter() {

				@Override
				public boolean accept(FTPFile file) {
					boolean flag = false;
					if (file.isDirectory()
							|| file.getName().indexOf(transferCode) == -1) {
						flag = false;
					} else {
						flag = true;
					}
					return flag;
				}
			});

			for (FTPFile file : files) {
				bos = new ByteArrayOutputStream();
				this.downloadFile("/prodImage/" + file.getName(), bos);
				image = Base64Utils.encode(bos.toByteArray());
				bos.close();

				image = prefix + image;

				result.put(file.getName(), image);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public FTPClient newFTPClientInstance() throws IOException {
		FTPClient ftpClient = new FTPClient();
		try {
			ClientCfg cfg = clientCfgService.getCfg();
			if (cfg != null) {
				ftpClient.connect(ftpAddress);
				ftpClient.login(cfg.getFtpUser(), cfg.getFtpPsw());
				ftpClient.setDefaultTimeout(defaultTimeout);
				ftpClient.setDataTimeout(dataTimeout);
				ftpClient.setConnectTimeout(connectTimeout);
				// 这里连接阿里云建的FTP 用主动模式请求
				ftpClient.enterLocalPassiveMode();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("连接FTP超时！");
		}

		return ftpClient;
	}

	/**
	 * 自定义FTP文件筛选，只要文件，不要目录
	 * 
	 * @author 三影塔
	 * 
	 */
	protected static class FTPServiceFileFilter implements FTPFileFilter {

		/**
		 * 产生一个实例
		 * 
		 * @return
		 */
		public static FTPServiceFileFilter newInstance() {
			return new FTPServiceFileFilter();
		}

		/**
		 * FTP文件筛选，只要文件，不要目录
		 */
		@Override
		public boolean accept(FTPFile file) {
			return file != null && file.isFile();
		}

	}

	@Override
	public Map<String, String> findImageByProdCodeByCodes(List<String> list) {
		Map<String, String> result = new HashMap<>(3);
		String image, prefix = "data:image/jpeg;base64,";
		FTPClient ftpClient;
		FTPFile[] files;
		ByteArrayOutputStream bos;
		String fileName, transferCode;

		try {
			ftpClient = this.newFTPClientInstance();
			files = ftpClient.listFiles("/prodImage");
			for (String str : list) {
				for (FTPFile file : files) {
					fileName = file.getName();
					transferCode = str + '-';
					if (!file.isDirectory()
							&& fileName.indexOf(transferCode) != -1) {

						// image = "ftp://" + ftpUserName + "." + ftpPassword
						// + "@" + ftpAddress + ":21" + "/prodImage/"
						// + fileName;
						bos = new ByteArrayOutputStream();
						this.downloadFile("/prodImage/" + file.getName(), bos);
						image = Base64Utils.encode(bos.toByteArray());
						bos.close();
						image = prefix + image;
						result.put(file.getName(), image);
						// 如果找到一个第一个匹配的图片就跳出循环，这里只需要拿到第一张展示
						break;
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public FTPClient newFTPClientInstanceByClient(String client) throws IOException {
		FTPClient ftpClient = new FTPClient();
		try {
			ClientCfg cfg = clientCfgService.getCfgByClient(client);
			if (cfg != null) {
				ftpClient.connect(ftpAddress);
				ftpClient.login(cfg.getFtpUser(), cfg.getFtpPsw());
				ftpClient.setDefaultTimeout(defaultTimeout);
				ftpClient.setDataTimeout(dataTimeout);
				ftpClient.setConnectTimeout(connectTimeout);
				// 这里连接阿里云建的FTP 用主动模式请求
				ftpClient.enterLocalPassiveMode();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("连接FTP超时！");
		}

		return ftpClient;
	}
	
}

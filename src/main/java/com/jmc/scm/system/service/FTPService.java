package com.jmc.scm.system.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;

import com.bstek.dorado.uploader.DownloadFile;
import com.bstek.dorado.uploader.UploadFile;

/**
 * FTP服务类，管理上传和下载文件
 * 
 * @author 三影塔
 * 
 */
public interface FTPService {

	/**
	 * 上传文件，Dorado专用
	 * 
	 * @param file
	 */
	Map<String, Object> uploadFile(UploadFile file);

	/**
	 * 上传文件，打开一个outputStream
	 * 
	 * @param pathName
	 *            文件名
	 * @return
	 */
	OutputStream uploadFile(String pathName);

	/**
	 * 通过文件路径去FTP上获取文件流
	 * 
	 * @param pathName
	 * @return
	 */
	InputStream downloadFile(String pathName);

	/**
	 * 通过文件路径取获取文件，并把数据写入到outputStream中
	 * 
	 * @param pathName
	 * @param outputStream
	 */
	void downloadFile(String pathName, OutputStream outputStream);

	/**
	 * 通过文件路径去FTP上获取流到指定参数里，并返回文件大小，这个方法用户HTTP图片返回
	 * 
	 * @param pathName
	 * @param inputStream
	 * @param fileSize
	 */
	void downloadFile(String pathName, InputStream inputStream, Long fileSize);

	/**
	 * Dorado文件下载，通过文件路径去FTP服务器上下载文件
	 * 
	 * @param pathName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	DownloadFile downloadFileForDorado(Map<String, Object> params)
			throws UnsupportedEncodingException;

	/**
	 * 通过商品编码查询FTP服务器上的图片，此方法会将匹配的图片转换成base加密字符串， 并以方法名为 key放到map中， 作为返回值返回出去
	 * 
	 * @param prodCode
	 *            商品编码
	 * @return Map 匹配的图片
	 */
	Map<String, String> findImageByProdCode(String prodCode);

	/**
	 * 通过商品编码List查询FTP服务器上的图片，此方法会将匹配的图片转换成base加密字符串， 并以方法名为 key放到map中，
	 * 作为返回值返回出去
	 * 
	 * @param list
	 *            商品编码list
	 * @return Map 匹配的图片
	 */
	Map<String, String> findImageByProdCodeByCodes(List<String> list);

	/**
	 * 创建FTP实例
	 * 
	 * @return
	 * @throws IOException
	 */
	FTPClient newFTPClientInstance() throws IOException;

	/**
	 * 创建FTP实例(根据指定客户端号)
	 * 
	 * @return
	 * @throws IOException
	 */
	FTPClient newFTPClientInstanceByClient(String client) throws IOException;

}

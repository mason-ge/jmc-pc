package com.jmc.scm.framework.service;

/**
 * 条形码、二维码Service
 * 
 * @author 三影塔
 * 
 */
public interface BarcodeService {

	/**
	 * 生成指定大小的条码
	 * 
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @param fontSize
	 *            字体大小
	 * @param dpi
	 *            清晰度
	 * @param content
	 *            内容
	 * @return String 条码的FTP地址
	 */
	String generateBarcode(double width, double height, double fontSize,
			double dpi, String content);

	/**
	 * 生成默认大小的条码
	 * 
	 * @param content
	 *            条码内容
	 * @return String 条码的FTP地址
	 */
	String generateBarcode(String content);

	/**
	 * 生成指定大小的二维码
	 * 
	 * @param content
	 *            二维码内容
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @param encoding
	 *            编码格式
	 * @return String 二维码的FTP地址
	 */
	String generateQRCode(String content, int width, int height, String encoding);

	/**
	 * 生成默认大小的二维码
	 * 
	 * @param content
	 *            二维码内容
	 * @return String 二维码的FTP地址
	 */
	String generateQRCode(String content);

}

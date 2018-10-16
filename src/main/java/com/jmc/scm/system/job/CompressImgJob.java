package com.jmc.scm.system.job;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bstek.dorado.core.Configure;
import com.jmc.scm.framework.dao.BaseDao;
import com.jmc.scm.framework.dao.impl.BaseDaoImpl;
import com.jmc.scm.system.model.ClientCfg;
import com.jmc.scm.system.service.ClientCfgService;
import com.jmc.scm.system.service.FTPService;
import com.jmc.scm.system.service.impl.ClientCfgServiceImpl;
import com.jmc.scm.system.service.impl.FTPServiceImpl;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 压缩图片JOB
 * 
 * @author Mason_Ge
 * 
 */
@Component("compressImgJob")
@Scope("prototype")
@SuppressWarnings("restriction")
public class CompressImgJob extends BaseDaoImpl implements Job {

	@Autowired
	@Qualifier(FTPServiceImpl.BEAN_ID)
	private FTPService ftpService;

	@Autowired
	@Qualifier(BaseDaoImpl.BEAN_ID)
	private BaseDao baseDao;

	@Autowired
	@Qualifier(ClientCfgServiceImpl.BEAN_ID)
	private ClientCfgService clientCfgService;

	private static Image img;
	private static int width;
	private static int height;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			if (Configure.getBoolean("sys.job.swich")) {
				// 压缩图片
				// 获取所有客户端号
				List<String> listClients = clientCfgService.getClientsWithNoRepeatFtp();
				if (listClients != null && !listClients.isEmpty()) {
					for (String str : listClients) {
						ClientCfg cfg = clientCfgService.getCfgByClient(str);
						if (cfg != null) {
							String dirFrom = "C:\\ftp\\LocalUser\\"
									+ cfg.getFtpUser() + "\\prodImage\\";
							String dirTo = "C:\\ftp\\LocalUser\\"
									+ cfg.getFtpUser() + "\\Thumbnails\\";

							File dirFile = new File(dirFrom);
							File existFile = new File(dirTo);
							File[] files = dirFile.listFiles();
							File[] existFiles = existFile.listFiles();
							List<String> existFileNames = new ArrayList<>();
							String fileName;

							if (existFiles != null && existFiles.length > 0) {
								for (File file : existFiles) {
									existFileNames.add(file.getName());
								}
							}
							if (files != null && files.length > 0) {
								for (File file : files) {
									fileName = file.getName();
									if (!existFileNames.contains(fileName)) {

										// File file = new File(fileName);//
										// 读入文件
										img = ImageIO.read(file); // 构造Image对象
										width = img.getWidth(null); // 得到源图宽
										height = img.getHeight(null); // 得到源图长
										//
										// ImgCompress imgCom = new ImgCompress(
										// file.getAbsolutePath());
										this.resizeFix(400, 400, fileName, cfg);
										System.out.println("压缩图片：" + new Date()
												+ "," + fileName);
									}
								}
							}
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 构造函数
	 * 
	 * @return
	 * 
	 * @return
	 */
	public void ImgCompress(String fileName) throws IOException {
		File file = new File(fileName);// 读入文件
		img = ImageIO.read(file); // 构造Image对象
		width = img.getWidth(null); // 得到源图宽
		height = img.getHeight(null); // 得到源图长
	}

	/**
	 * 按照宽度还是高度进行压缩
	 * 
	 * @param w
	 *            int 最大宽度
	 * @param h
	 *            int 最大高度
	 */
	public void resizeFix(int w, int h, String fileName, ClientCfg cfg)
			throws IOException {
		if (width / height > w / h) {
			resizeByWidth(w, fileName, cfg);
		} else {
			resizeByHeight(h, fileName, cfg);
		}
	}

	/**
	 * 以宽度为基准，等比例放缩图片
	 * 
	 * @param w
	 *            int 新宽度
	 */
	public void resizeByWidth(int w, String fileName, ClientCfg cfg)
			throws IOException {
		int h = (int) (height * w / width);
		resize(w, h, fileName, cfg);
	}

	/**
	 * 以高度为基准，等比例缩放图片
	 * 
	 * @param h
	 *            int 新高度
	 */
	public void resizeByHeight(int h, String fileName, ClientCfg cfg)
			throws IOException {
		int w = (int) (width * h / height);
		resize(w, h, fileName, cfg);
	}

	/**
	 * 强制压缩/放大图片到固定的大小
	 * 
	 * @param w
	 *            int 新宽度
	 * @param h
	 *            int 新高度
	 */
	public void resize(int w, int h, String fileName, ClientCfg cfg)
			throws IOException {

		String dirTo = "C:\\ftp\\LocalUser\\" + cfg.getFtpUser()
				+ "\\Thumbnails\\";

		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		File destFile = new File(dirTo + fileName);
		FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
		// 可以正常实现bmp、png、gif转jpg
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image); // JPEG编码
		out.close();
	}

}

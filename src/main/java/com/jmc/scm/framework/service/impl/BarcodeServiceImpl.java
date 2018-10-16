package com.jmc.scm.framework.service.impl;

import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.krysalis.barcode4j.output.BarcodeCanvasSetupException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jmc.scm.framework.service.BarcodeService;
import com.jmc.scm.system.service.FTPService;
import com.jmc.scm.system.service.impl.FTPServiceImpl;
import com.jmc.scm.util.BarcodeUtil;

/**
 * 条形码、二维码Service实现类
 * 
 * @author 三影塔
 * 
 */
@Service(BarcodeServiceImpl.BEAN_ID)
public class BarcodeServiceImpl implements BarcodeService {

	public static final String BEAN_ID = "barcodeServiceImpl";

	@Autowired
	@Qualifier(FTPServiceImpl.BEAN_ID)
	private FTPService ftpService;

	@Override
	public String generateBarcode(String content) {
		String fileName;
		fileName = this.generateBarcode(11d, 20d, 5, 300, content);
		return fileName;
	}

	@Override
	public String generateBarcode(double width, double height, double fontSize,
			double dpi, String content) {
		Source source;
		Transformer trans;
		OutputStream ous;
		Result res;
		String fileName = "scm/barcode/" + content + ".svg";

		try {
			trans = TransformerFactory.newInstance().newTransformer();
			source = BarcodeUtil.generateCode128(content, width, height,
					fontSize, dpi);

			ous = ftpService.uploadFile(fileName);

			if (source != null) {
				res = new StreamResult(ous);
				trans.transform(source, res);
				ous.flush();
				ous.close();
			}
		} catch (BarcodeCanvasSetupException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	@Override
	public String generateQRCode(String content, int width, int height,
			String encoding) {
		Source source;
		Transformer trans;
		OutputStream ous;
		Result res;
		PasswordEncoder encoder = new StandardPasswordEncoder();
		String fileName = "scm/qrcode/" + encoder.encode(content) + ".svg";

		try {
			trans = TransformerFactory.newInstance().newTransformer();
			source = BarcodeUtil.generateQRCode(content, width, height,
					encoding);

			ous = ftpService.uploadFile(fileName);

			if (source != null) {
				res = new StreamResult(ous);
				trans.transform(source, res);
				ous.flush();
				ous.close();
			}
		} catch (BarcodeCanvasSetupException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	@Override
	public String generateQRCode(String content) {
		String fileName;
		fileName = this.generateQRCode(content, 200, 200, "UTF-8");
		return fileName;
	}

}

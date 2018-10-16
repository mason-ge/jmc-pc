package com.jmc.scm.util;

import java.io.File;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.BarcodeCanvasSetupException;
import org.krysalis.barcode4j.output.svg.SVGCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.w3c.dom.DocumentFragment;

import com.jmc.scm.framework.barcode4j.QRCodeBean;

/**
 * 基于Barcode4j的条码生成工具类
 * 
 * @author 三影塔
 * 
 */
public class BarcodeUtil {

	public static Source generateCode128(String content, double width,
			double height, double fontSize, double dpi)
			throws BarcodeCanvasSetupException, TransformerException {
		Double moduleWidth = Double.valueOf(1.0D);
		Code128Bean bean = new Code128Bean();
		Integer barcodeLength = Integer.valueOf(content.length());
		SVGCanvasProvider canvas;
		DocumentFragment frag;
		Source src;

		if (barcodeLength.intValue() < 8) {
			moduleWidth = Double.valueOf(3.0D * width);
		} else if (barcodeLength.intValue() < 16) {
			moduleWidth = Double.valueOf(2.0D * width);
		} else if (barcodeLength.intValue() < 24) {
			moduleWidth = width;
		}
		if (moduleWidth.doubleValue() < 1.0D) {
			moduleWidth = Double.valueOf(1.0D);
		}
		moduleWidth = UnitConv.in2mm(moduleWidth.doubleValue() / dpi);

		bean.setModuleWidth(moduleWidth);
		bean.setHeight(height);
		bean.setFontSize(fontSize);

		canvas = new SVGCanvasProvider(false, 0);
		bean.generateBarcode(canvas, content);
		frag = canvas.getDOMFragment();
		src = new DOMSource(frag);

		return src;
	}

	public static Source generateQRCode(String content, int width, int height,
			String encoding) throws BarcodeCanvasSetupException,
			TransformerException {
		QRCodeBean bean = new QRCodeBean();
		SVGCanvasProvider canvas;
		DocumentFragment frag;
		Source src;

		bean.setImageWidth(width);
		bean.setImageHeight(height);
		bean.setEncoding(encoding);

		canvas = new SVGCanvasProvider(false, 0);
		bean.generateBarcode(canvas, content);
		frag = canvas.getDOMFragment();
		src = new DOMSource(frag);

		return src;
	}

	public static void transformBarcode(String filePath, String content)
			throws BarcodeCanvasSetupException, TransformerException {
		Transformer trans = TransformerFactory.newInstance().newTransformer();
		Source src;
		Result res;

		src = generateCode128(content, 11d, 20d, 5, 300);

		if (src != null) {
			res = new StreamResult(new File(filePath));
			trans.transform(src, res);
		}
	}

	public static void transformQRCode(String filePath, String content)
			throws BarcodeCanvasSetupException, TransformerException {
		Transformer trans = TransformerFactory.newInstance().newTransformer();
		Source src;
		Result res;

		src = generateQRCode(content, 200, 200, "UTF-8");

		if (src != null) {
			res = new StreamResult(new File(filePath));
			trans.transform(src, res);
		}
	}
}

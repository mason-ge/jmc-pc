/*
 * Copyright 2012 Jeremias Maerki.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* $Id: QRLogicImpl.java,v 1.3 2012/02/08 12:59:41 jmaerki Exp $ */

package com.jmc.scm.framework.barcode4j;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import org.krysalis.barcode4j.TwoDimBarcodeLogicHandler;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Top-level class for the logic part of the DataMatrix implementation.
 * 
 * @version $Id: QRLogicImpl.java,v 1.3 2012/02/08 12:59:41 jmaerki Exp $
 */
public class QRLogicImpl implements QRConstants {

	private int imageWidth = 200;

	private int imageHeight = 200;

	/**
	 * Generates the barcode logic.
	 * 
	 * @param logic
	 *            the logic handler to receive generated events
	 * @param msg
	 *            the message to encode
	 * @param errorCorrectionLevel
	 *            the error correction level (one of L, M, Q, H)
	 * @param encoding
	 *            the message encoding
	 * @param minSize
	 *            the minimum symbol size constraint or null for no constraint
	 * @param maxSize
	 *            the maximum symbol size constraint or null for no constraint
	 */
	public void generateBarcodeLogic(TwoDimBarcodeLogicHandler logic,
			String msg, String encoding, char errorCorrectionLevel,
			Dimension minSize, Dimension maxSize) {

		Map<EncodeHintType, ?> hints = createHints(encoding);

		BitMatrix matrix;
		try {
			matrix = new QRCodeWriter().encode(msg, BarcodeFormat.QR_CODE,
					this.getImageWidth(), this.getImageHeight(), hints);
		} catch (WriterException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		// finally, paint the barcode
		logic.startBarcode(msg, msg);
		encodeLowLevel(logic, matrix);
		logic.endBarcode();
	}

	protected static Map<EncodeHintType, Object> createHints(String encoding) {
		Map<EncodeHintType, Object> hints = null;
		if (!"ISO-8859-1".equals(encoding)) {
			hints = new HashMap<>();
			hints.put(EncodeHintType.CHARACTER_SET, encoding);
		}
		return hints;
	}

	private void encodeLowLevel(TwoDimBarcodeLogicHandler logic,
			BitMatrix matrix) {
		int symbolWidth = matrix.getWidth();
		int symbolHeight = matrix.getHeight();
		for (int y = 0; y < symbolHeight; y++) {
			logic.startRow();
			for (int x = 0; x < symbolWidth; x++) {
				logic.addBar(matrix.get(x, y), 1);
			}
			logic.endRow();
		}
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

}

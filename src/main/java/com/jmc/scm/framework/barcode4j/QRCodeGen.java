package com.jmc.scm.framework.barcode4j;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import org.krysalis.barcode4j.BarcodeDimension;
import org.krysalis.barcode4j.TwoDimBarcodeLogicHandler;
import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.impl.DefaultTwoDimCanvasLogicHandler;
import org.krysalis.barcode4j.output.Canvas;
import org.krysalis.barcode4j.output.CanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;

public class QRCodeGen extends AbstractBarcodeBean {

	protected static final double DEFAULT_MODULE_WIDTH = UnitConv
			.in2mm(1.0 / 72); // 1px at 72dpi

	private ErrorCorrectionLevel zxingErrLevel = ErrorCorrectionLevel.L;

	private String encoding = "ISO-8859-1"; // ECI 000003
    private Dimension minSize;
    /** Optional: the maximum size of the symbol. */
    private Dimension maxSize;

    /** Create a new instance. */
    public QRCodeGen() {
        this.height = 0.0; //not used by DataMatrix
        this.moduleWidth = DEFAULT_MODULE_WIDTH;
        setQuietZone(QRConstants.QUIET_ZONE_SIZE * moduleWidth);
    }

	public void generateBarcode(CanvasProvider canvas, String msg) {
		if ((msg == null) || (msg.length() == 0)) {
			throw new NullPointerException("Parameter msg must not be empty");
		}

		TwoDimBarcodeLogicHandler handler = new DefaultTwoDimCanvasLogicHandler(
				this, new Canvas(canvas));

		this.generateBarcodeLogic(handler, msg, encoding, getZxingErrLevel());
	}

	public void generateBarcodeLogic(TwoDimBarcodeLogicHandler logic,
			String msg, String encoding, ErrorCorrectionLevel zxingErrLevel) {
		Map<EncodeHintType, ?> hints = createHints(encoding);

		BitMatrix matrix;
        try {
			matrix = new QRCodeWriter().encode(msg, BarcodeFormat.QR_CODE, 200, 200, hints);
        } catch (WriterException e) {
            throw new RuntimeException(e.getMessage());
        }

		logic.startBarcode(msg, msg);
		encodeLowLevel(logic, matrix);
		logic.endBarcode();
	}
	
    public BarcodeDimension calcDimensions(String msg) {
        QRCode code;
        try {
        	code = Encoder.encode(msg,
                    this.getZxingErrLevel(),
                    this.createHints(encoding));
        } catch (WriterException e) {
            throw new RuntimeException(e.getMessage());
        }
        ByteMatrix matrix = code.getMatrix();
        int effWidth = matrix.getWidth();
        int effHeight = matrix.getHeight();
        checkSizeConstraints(effWidth, effHeight);

        double width = effWidth * getModuleWidth();
        double height = effHeight * getBarHeight();
        double qzh = (hasQuietZone() ? getQuietZone() : 0);
        double qzv = (hasQuietZone() ? getVerticalQuietZone() : 0);
        return new BarcodeDimension(width, height,
                width + (2 * qzh), height + (2 * qzv),
                qzh, qzv);
    }

    private void checkSizeConstraints(int width, int height) {
        //Note: we're only checking the constraints, we can't currently influence ZXing's encoder.
        if (this.minSize != null) {
            if (width < this.minSize.width || height < this.minSize.height) {
                throw new IllegalArgumentException(
                        "The given message would result in a smaller symbol than required."
                        + " Requested minimum: "
                        + this.minSize.width + " x " + this.minSize.height
                        + ", effective: "
                        + width + " x " + height);
            }
        }
        if (this.maxSize != null) {
            if (width > this.maxSize.width || height > this.maxSize.height) {
                throw new IllegalArgumentException(
                        "The given message would result in a larger symbol than required."
                        + " Requested maximum: "
                        + this.maxSize.width + " x " + this.maxSize.height
                        + ", effective: "
                        + width + " x " + height);
            }
        }
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

	private Map<EncodeHintType, String> createHints(String encoding) {
		Map<EncodeHintType, String> hints = null;
		if (!"ISO-8859-1".equals(encoding)) {
			hints = new HashMap<>();
			hints.put(EncodeHintType.CHARACTER_SET, encoding);
		}
		return hints;
	}

	public ErrorCorrectionLevel getZxingErrLevel() {
		return zxingErrLevel;
	}

	public void setZxingErrLevel(ErrorCorrectionLevel zxingErrLevel) {
		this.zxingErrLevel = zxingErrLevel;
	}

	@Override
	public double getBarWidth(int width) {
		return moduleWidth;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void setBarWidth(double width) {
		this.moduleWidth = width;
	}

	public Dimension getMinSize() {
		return minSize;
	}

	public void setMinSize(Dimension minSize) {
		this.minSize = minSize;
	}

	public Dimension getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Dimension maxSize) {
		this.maxSize = maxSize;
	}

}

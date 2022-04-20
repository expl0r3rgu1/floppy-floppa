package main.utilities;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CommonMethods {
	// Utility method to generate random integer in range (consider moving it)
	public static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	public static int getPixelsFromPercentageWidth(int percentage) {
		return (int) Constants.SCREEN_SIZE.getWidth() * percentage / 100;
	}

	public static int getPixelsFromPercentageHeight(int percentage) {
		return (int) Constants.SCREEN_SIZE.getHeight() * percentage / 100;
	}

	public static Image getImageResource(String imageName) {
		try {
			return ImageIO.read(CommonMethods.class.getResource("/resources/images/" + imageName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Font getFontResource(String fontName) {
		try {
			return Font.createFont(Font.TRUETYPE_FONT,
					new File(CommonMethods.class.getResource("/resources/fonts/" + fontName).getFile()));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static File getVideoResource(String videoName) {
		File videoFile = new File(CommonMethods.class.getResource("/resources/videos/" + videoName + ".mp4").getFile());

		if (videoFile.exists()) {
			return videoFile;
		}

		return null;
	}

	public static Image getAngledImage(Image image, int degrees) {
		double rotationRequired = Math.toRadians(degrees);
		double locationX = image.getWidth(null) / 2;
		double locationY = image.getHeight(null) / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		return op.filter(toBufferedImage(image), null);
	}

	private static BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		return bimage;
	}
}

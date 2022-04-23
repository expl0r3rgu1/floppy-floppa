package main.utilities;

import java.awt.Dimension;
import java.awt.Font;

import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * This class contains usefull methods used in more classes
 */
public class CommonMethods {
	/**
	 * Utility method to generate random integer in range
	 * 
	 * @param min the lowest number that can be generated
	 * @param max the biggest number that can be generated
	 * @return a random number between min and max
	 */
	public static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	/**
	 * Method that calculates how many pixels the percentage of the screen width
	 * corresponds to
	 * 
	 * @param percentage the percentage of the screen
	 * @return the pixels that correspond to the percentage
	 */
	public static int getPixelsFromPercentageWidth(int percentage) {
		return (int) Constants.SCREEN_SIZE.getWidth() * percentage / 100;
	}

	/**
	 * Method that calculates how many pixels the percentage of the screen height
	 * corresponds to
	 * 
	 * @param percentage the percentage of the screen
	 * @return the pixels that correspond to the percentage
	 */
	public static int getPixelsFromPercentageHeight(int percentage) {
		return (int) Constants.SCREEN_SIZE.getHeight() * percentage / 100;
	}

	/**
	 * Method that gets an image from the resources
	 * 
	 * @param imageName the name of the image to get
	 * @return the image
	 */
	public static Image getImageResource(String imageName) {
		try {
			return ImageIO.read(CommonMethods.class.getResource("/resources/images/" + imageName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * getJLabelImage is a method that reads an image file and creates the
	 * corresponding Image object which gets also scaled
	 * 
	 * @param fileName
	 * @return a JLabel containing the newly created Image
	 */
	public static JLabel getJLabelImage(String fileName, int dimension) {
		JLabel label = null;
		Image image = getImageResource(fileName);
		ImageIcon imageIcon = new ImageIcon(scale(image, new Dimension(
				(CommonMethods.getPixelsFromPercentageWidth(dimension)), (CommonMethods.getPixelsFromPercentageWidth(dimension)))));
		label = new JLabel(imageIcon);
		return label;
	}
	
	/**
	 * scale returns a scaled version of the image parameter
	 * 
	 * @param image - the image to scale
	 * @param dim   - the Dimension to which to scale the image
	 * @return a scaled version of the image parameter
	 */
	private static Image scale(Image image, Dimension dim) {
		return image.getScaledInstance((int) dim.getWidth(), (int) dim.getHeight(), Image.SCALE_DEFAULT);
	}
	
	/**
	 * Method that gets a font from the resources
	 * 
	 * @param fontName the name of the font to get
	 * @return the font
	 */
	public static Font getFontResource(String fontName) {
		try {
			return Font.createFont(Font.TRUETYPE_FONT,
					CommonMethods.class.getResourceAsStream("/resources/fonts/" + fontName + ".ttf"));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Method that gets a gif from the resources
	 * 
	 * @param gifName the name of the gif to get
	 * @return the ImageIcon of the gif
	 */
	public static ImageIcon getGifResource(String gifName) {
		return new ImageIcon(CommonMethods.class.getResource("/resources/gifs/" + gifName + ".gif"));
	}

	/**
	 * Utility method to rotate an image of a given angle
	 * 
	 * @param image   the image to be rotated
	 * @param degrees the degrees of the angle
	 * @return the image rotated
	 */
	public static Image getAngledImage(Image image, int degrees) {
		double rotationRequired = Math.toRadians(degrees);
		double locationX = image.getWidth(null) / 2;
		double locationY = image.getHeight(null) / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		return op.filter(toBufferedImage(image), null);
	}

	/**
	 * Utility method to get a BufferedImage from an image
	 * 
	 * @param img the image to change
	 * @return the image transformed in a BufferedImage
	 */
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

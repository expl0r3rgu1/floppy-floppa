package junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Dimension;
import java.awt.Image;

import org.junit.jupiter.api.Test;

import main.utilities.CommonMethods;
import main.utilities.Constants;

public class TestCommonMethods {

	final private int NUM_MIN = 0;
	final private int NUM_MAX = 10;
	final private int PERCENTAGE = 100;
	final private String IMAGE_NAME = "Background";
	final private String FONT_NAME = "pixel";
	final private String GIF_NAME = "Tutorial";

	@Test
	/**
	 * Checks if the number is actually in the given range
	 */
	void getRandomNumberTest() {
		int randomNum = CommonMethods.getRandomNumber(NUM_MIN, NUM_MAX);
		assertTrue(randomNum <= NUM_MAX && randomNum >= NUM_MIN);
	}

	@Test
	/**
	 * Checks if those methods calculate the right amount of pixels
	 */
	void getPixelsFromPercentageTest() {
		assertTrue(Constants.SCREEN_SIZE.equals(new Dimension(CommonMethods.getPixelsFromPercentageWidth(PERCENTAGE),
				CommonMethods.getPixelsFromPercentageHeight(PERCENTAGE))));
	}

	@Test
	/**
	 * Checks if those methods actually gets the resource
	 */
	void getResourceTest() {
		assertNotNull(CommonMethods.getImageResource(IMAGE_NAME));
		assertNotNull(CommonMethods.getFontResource(FONT_NAME));
		assertNotNull(CommonMethods.getGifResource(GIF_NAME));
	}
}

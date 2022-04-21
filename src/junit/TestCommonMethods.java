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
	final private int PERCENTAGE = 10;
	final private int SCREEN_WIDTH = 1536;
	final private int SCREEN_HEIGHT = 864;
	final private int PIXELS_WIDTH_RESULT = 153;
	final private int PIXELS_HEIGHT_RESULT = 86;
	final private String IMAGE_NAME = "Background";
	final private String FONT_NAME = "pixel.TTF";
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
	 * Checks if those methods calculate the right amount of pixels;
	 * Considering that every screen has a different size, the results of this test are 
	 * valid only if the width of the screen is 1536 and the height is 864
	 */
	void getPixelsFromPercentageTest() {
		if (Constants.SCREEN_SIZE.equals(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT))) {
			assertTrue(CommonMethods.getPixelsFromPercentageHeight(PERCENTAGE) == PIXELS_HEIGHT_RESULT);
			assertTrue(CommonMethods.getPixelsFromPercentageWidth(PERCENTAGE) == PIXELS_WIDTH_RESULT);
		}
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

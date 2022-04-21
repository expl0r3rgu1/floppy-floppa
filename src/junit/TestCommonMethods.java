package junit;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import main.utilities.CommonMethods; 

public class TestCommonMethods {

	final private int NUM_MIN = 0;
	final private int NUM_MAX = 10;
	
	@Test
	/**
	 * Checks if the number is actually in the given range
	 */
	void getRandomNumberTest() {
		int randomNum = CommonMethods.getRandomNumber(NUM_MIN, NUM_MAX);
		assertTrue(randomNum <= NUM_MAX && randomNum >= NUM_MIN);
	}
}

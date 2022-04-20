package junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import main.menu.shop.Shop;

public class TestShop {

	@Test
	/**
	 * Checks if the Shop correctly reads info from the savings file. The Shop class
	 * only reads the file at the beginning, when the program is launched, so to
	 * initialize its data structures of Purchase Statuses. If the JUnit test is
	 * lunched after, when the savings file has been modified, the JUnit results are
	 * wrong.
	 */
	void fileReading() {
		Shop shop = new Shop();

		for (int i = 0; i < shop.getSkinsNum(); i++) {
			if (i == 0) {
				assertTrue(shop.getSkins().get(i).isPurchased());
			} else {
				assertFalse(shop.getSkins().get(i).isPurchased());
			}
		}
		for (int i = 0; i < shop.getSceneriesNum(); i++) {
			if (i == 0) {
				assertTrue(shop.getSceneries().get(i).isPurchased());
			} else {
				assertFalse(shop.getSceneries().get(i).isPurchased());
			}
		}
	}
}

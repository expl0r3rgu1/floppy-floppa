package junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import main.menu.shop.Shop;

public class TestShop {
	
	final private int ENOUGH_COINS = 1000;
	final private int NOT_ENOUGH_COINS = 0;

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

	@Test
	/**
	 * Checks if the Shop correctly buys items
	 */
	void buying() {
		Shop shop = new Shop();

		shop.setCoins(this.NOT_ENOUGH_COINS);
		for (int i = 1; i < shop.getSkinsNum(); i++) {
			shop.buy(shop.getSkins().get(i).getX());
			assertFalse(shop.getSkins().get(i).isPurchased());
		}
		for (int i = 1; i < shop.getSceneriesNum(); i++) {
			shop.buy(shop.getSceneries().get(i).getX());
			assertFalse(shop.getSceneries().get(i).isPurchased());
		}

		shop.setCoins(this.ENOUGH_COINS);
		int prevCoins = shop.getCoins();
		for (int i = 1; i < shop.getSkinsNum(); i++) {
			shop.buy(shop.getSkins().get(i).getX());
			assertTrue(shop.getCoins() < prevCoins);
			prevCoins = shop.getCoins();
			assertTrue(shop.getSkins().get(i).isPurchased());
		}

		shop.setCoins(this.ENOUGH_COINS);
		prevCoins = shop.getCoins();
		for (int i = 1; i < shop.getSceneriesNum(); i++) {
			shop.buy(shop.getSceneries().get(i).getX());
			assertTrue(shop.getCoins() < prevCoins);
			prevCoins = shop.getCoins();
			assertTrue(shop.getSceneries().get(i).isPurchased());
		}
	}
}

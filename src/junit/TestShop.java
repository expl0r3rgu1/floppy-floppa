package junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import main.controller.menu.shop.Shop;
import main.model.utilities.Constants;

public class TestShop {

	final private int ENOUGH_COINS = 1000;
	final private int NOT_ENOUGH_COINS = 0;

	@Test
	/**
	 * Checks if the Shop correctly reads info from the savings file. 
	 */
	public void fileReading() {
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
	public void buying() {
		this.createFile();
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

	private void createFile() {
		File savingsFile = new File(Constants.SAVINGS_FILE_PATH);

		try {
			savingsFile.createNewFile();
			FileWriter writer = new FileWriter(savingsFile);
			writer.write(Constants.SAVINGS_FILE_START_CONTENT);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

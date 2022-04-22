package main.menu;

import main.menu.shop.Shop;
import main.state_changers.CoinsIncrement;
import main.state_changers.CoinsReducer;

public class EOGMenu implements Menu {

	private Shop shop;
	private int previousCoins;
	private int newCoins;

	/**
	 * @param shop            Shop parameter is passed so that the constructor does
	 *                        not initializes a new one
	 * @param metersTravelled The meters that the Character traveled during the game
	 */
	public EOGMenu(Shop shop, int metersTravelled) {
		this.shop = shop;
		this.previousCoins = shop.getCoins();
		this.newCoins = 0;
	}

	/**
	 * @return the coins owned before the last game
	 */
	public int getPreviousCoins() {
		return previousCoins;
	}

	/**
	 * @return the coins owned after the last game
	 */
	public int getNewCoins() {
		return newCoins;
	}

	/**
	 * The method updates the owned coins considering the meters traveled during the
	 * last game, the CoinsReducer malus and the CoinsIncrement booster
	 * 
	 * @param meters The meters that the Character traveled during the game
	 */
	private void updateCoins(int meters) {
		newCoins = this.previousCoins + ((int) Math.floor(meters / 5)) - this.updateCoinsReduce()
				+ this.updateCoinsIncrease();

		if (newCoins < 0) {
			newCoins = 0;
		}
		shop.setCoins(newCoins);
	}

	/**
	 * Calculates how many coins need to be subtracted
	 * 
	 * @param times How many times the character hit the CoinsReducer malus
	 * @return the sum of coins that need to be subtracted
	 */
	private int updateCoinsReduce(int times) {
		int sum = 0;
		for (int i = 0; i < times; i++) {
			sum += coinsReducer.changeState();
		}
		return sum;
	}

	/**
	 * Calculates how many coins need to be subtracted
	 * 
	 * @param times How many times the character hit the CoinsReducer malus
	 * @return the sum of coins that need to be subtracted
	 */
	private int updateCoinsIncrease(int times) {
		int sum = 0;
		for (int i = 0; i < times; i++) {
			sum += coinsIncrement.changeState();
		}
		return sum;
	}
}

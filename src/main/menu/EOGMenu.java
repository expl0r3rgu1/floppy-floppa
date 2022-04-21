package main.menu;

import main.menu.shop.Shop;
import main.state_changers.CoinsIncrement;
import main.state_changers.CoinsReducer;

public class EOGMenu implements Menu {

	private Shop shop;
	private int previousCoins;
	private int newCoins;
	private CoinsReducer coinsReducer;
	private CoinsIncrement coinsIncrement;

	/**
	 * @param shop            Shop parameter is passed so that the constructor does
	 *                        not initializes a new one
	 * @param metersTravelled The meters that the Character traveled during the game
	 * @param reducerTimes    How many times the character hit the CoinsReducer
	 *                        malus
	 * @param incrementTimes  How many times the character hit the CoinsIncrement
	 *                        booster
	 */
	public EOGMenu(Shop shop, int metersTravelled, int reducerTimes, int incrementTimes) {
		this.shop = shop;
		this.previousCoins = shop.getCoins();
		this.newCoins = 0;
		this.updateCoins(metersTravelled, reducerTimes, incrementTimes);
		coinsReducer = new CoinsReducer(null, null);
		coinsIncrement = new CoinsIncrement(null, null);
	}

	/**
	 * @return the coins owned before the last game
	 */
	public int getPreviousCoins() {
		return previousCoins;
	}

	/**
	 * The method updates the owned coins considering the meters traveled during the
	 * last game, the CoinsReducer malus and the CoinsIncrement booster
	 * 
	 * @param meters         The meters that the Character traveled during the game
	 * @param reducerTimes   How many times the character hit the CoinsReducer malus
	 * @param incrementTimes How many times the character hit the CoinsIncrement
	 *                       booster
	 * @return the newly owned coins
	 */
	protected int updateCoins(int meters, int reducerTimes, int incrementTimes) {
		int newCoins = this.previousCoins + ((int) Math.floor(meters / 5) - this.updateCoinsReduce(reducerTimes)
				+ this.updateCoinsIncrease(incrementTimes));
		shop.setCoins(newCoins);
		this.previousCoins = newCoins;
		return newCoins;
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

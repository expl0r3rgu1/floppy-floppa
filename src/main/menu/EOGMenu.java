package main.menu;

import main.menu.shop.Shop;

public class EOGMenu implements Menu {

	private static int previousCoins;

	public EOGMenu() {
		EOGMenu.previousCoins = Shop.getCoins();
	}

	public static int getPreviousCoins() {
		return previousCoins;
	}

	//TODO add for
	public int updateCoins(int meters, int reducerTimes, int incrementTimes) {
		int newCoins = EOGMenu.previousCoins + ((int) Math.floor(meters / 5) - (CoinsReducer.loseCoins() * reducerTimes)
				+ (CoinsIncrement.gainCoins()) * incrementTimes);
		Shop.setCoins(newCoins);
		EOGMenu.previousCoins = newCoins;
		return newCoins;
	}
}

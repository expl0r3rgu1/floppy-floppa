package main.menu;

import main.menu.shop.Shop;

public class EOGMenu implements Menu {

	private static int previousCoins;

	public EOGMenu(int metersTravelled, int reducerTimes, int incrementTimes) {
		EOGMenu.previousCoins = Shop.getCoins();
		this.updateCoins(metersTravelled, reducerTimes, incrementTimes);
	}

	public static int getPreviousCoins() {
		return previousCoins;
	}

	private void updateCoins(int meters, int reducerTimes, int incrementTimes) {
		int newCoins = EOGMenu.previousCoins + ((int) Math.floor(meters / 5) - this.updateCoinsReduce(reducerTimes)
				+ this.updateCoinsIncrease(incrementTimes));
		Shop.setCoins(newCoins);
		EOGMenu.previousCoins = newCoins;
	}

	private int updateCoinsReduce(int times) {
		int sum;
		for (int i = 0; i < times; i++) {
			sum += CoinsReducer.loseCoins();
		}
		return sum;
	}

	private int updateCoinsIncrease(int times) {
		int sum;
		for (int i = 0; i < times; i++) {
			sum += CoinsIncrement.gainCoins();
		}
		return sum;
	}
}

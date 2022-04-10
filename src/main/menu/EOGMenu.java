package main.menu;

import main.menu.shop.Shop;

public class EOGMenu implements Menu {

	private int previousCoins;

	public EOGMenu(int metersTravelled, int reducerTimes, int incrementTimes) {
		this.previousCoins = Shop.getCoins();
		this.updateCoins(metersTravelled, reducerTimes, incrementTimes);
	}

	public int getPreviousCoins() {
		return previousCoins;
	}

	protected int updateCoins(int meters, int reducerTimes, int incrementTimes) {
		int newCoins = this.previousCoins + ((int) Math.floor(meters / 5) - this.updateCoinsReduce(reducerTimes)
				+ this.updateCoinsIncrease(incrementTimes));
		Shop.setCoins(newCoins);
		this.previousCoins = newCoins;
		return newCoins;
	}

	private int updateCoinsReduce(int times) {
		int sum;
		for (int i = 0; i < times; i++) {
			sum += CoinsReducer.changeState();
		}
		return sum;
	}

	private int updateCoinsIncrease(int times) {
		int sum;
		for (int i = 0; i < times; i++) {
			sum += CoinsIncrement.changeState();
		}
		return sum;
	}
}

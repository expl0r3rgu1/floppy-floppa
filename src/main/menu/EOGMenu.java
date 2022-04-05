package main.menu;

import main.menu.shop.Shop;

public class EOGMenu implements Menu {

	private int previousCoins;

	public EOGMenu() {
		this.previousCoins = Shop.getCoins();
	}

	public int getPreviousCoins() {
		return previousCoins;
	}

	public int updateCoins(int meters) {
		int newCoins = this.previousCoins + ((int) Math.floor(meters / 5) - CoinsReducer.loseCoins())
				+ CoinsIncrement.gainCoins();
		Shop.setCoins(newCoins);
		this.previousCoins = newCoins;
		return newCoins;
	}
}

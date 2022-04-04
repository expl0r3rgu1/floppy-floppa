package main.menu;

public class EOGMenu implements Menu {

	private int previousCoins;

	public EOGMenu() {
		this.previousCoins = Shop.getCoins();
	}

	public int getPreviousCoins() {
		return previousCoins;
	}

	public int updateCoins(int meters) {
		int newCoins = this.previousCoins + ((int) Math.floor(meters / 5) - coinsReducer.loseCoins());
		Shop.setCoins(newCoins);
		this.previousCoins = newCoins;
		return newCoins;
	}
}

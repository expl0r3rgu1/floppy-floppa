package main.menu;

public class EOGMenu implements Menu {

	public EOGMenu() {

	}

	public static int updateCoins(int meters) {
		int coinsWon = (int) Math.floor(meters / 5);

		return coinsWon;
	}
}

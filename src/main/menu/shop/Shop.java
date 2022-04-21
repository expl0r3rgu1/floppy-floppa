package main.menu.shop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.PricedBackground;
import main.utilities.PricedSkin;

/**
 * A class that keeps track of the items' current purchase status and that has
 * methods to purchase the items
 *
 */

public class Shop {

	private int skinsNum;
	private int sceneriesNum;
	private int coins;
	private List<String> skinInitialize = new ArrayList<>();
	private List<String> backgroundInitialize = new ArrayList<>();
	private List<Integer> prices = Arrays.asList(0, 50, 100, 200, 500);
	private List<PurchaseStatus<PricedSkin>> skins;
	private List<PurchaseStatus<PricedBackground>> sceneries;
	private File savingsFile;

	public Shop() {
		this.initializeStrings();
		this.coins = 0;

		skins = new ArrayList<>();
		sceneries = new ArrayList<>();

		this.savingsFile = new File(Constants.SAVINGS_FILE_PATH);

		try {
			this.getFileInfo();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the current coins amount
	 */
	public int getCoins() {
		return coins;
	}

	/**
	 * Sets the coins field
	 * 
	 * @param coins
	 */
	public void setCoins(int coins) {
		this.coins = coins;
	}

	/**
	 * @return the list of purchase statuses of skins
	 */
	public List<PurchaseStatus<PricedSkin>> getSkins() {
		return skins;
	}

	/**
	 * @return the list of purchase statuses of backgrounds
	 */
	public List<PurchaseStatus<PricedBackground>> getSceneries() {
		return sceneries;
	}

	/**
	 * @return the number of Skin objects
	 */
	public int getSkinsNum() {
		return skinsNum;
	}

	/**
	 * @return the number of Background objects
	 */
	public int getSceneriesNum() {
		return sceneriesNum;
	}

	/**
	 * Depending on the class of the parameter, the method passes, to another
	 * method, the parameter object together with the corresponding list of purchase
	 * statuses
	 * 
	 * @param o the object to be purchased
	 * @return true if the given object gets purchased, false otherwise
	 */
	public boolean buy(Object o) {
		if (o.getClass().equals(PricedSkin.class)) {
			return findAndBuySkins(o, this.skins);
		} else {
			return findAndBuySceneries(o, this.sceneries);
		}
	}

	/**
	 * Finds the Object o in the List list and, if the current coins are enough and
	 * the item hasn't been previously purchased, it purchases it
	 * 
	 * @param o    the object to be purchased
	 * @param list List of purchase statuses of PricedSkin items
	 * @return true if the given object gets purchased, false otherwise
	 */
	private boolean findAndBuySkins(Object o, List<PurchaseStatus<PricedSkin>> list) {
		boolean state = false;
		for (var status : list) {
			if (status.getX().equals(o)) {
				if (!status.isPurchased() && status.getX().getPrice() <= this.coins) {
					status.purchase();
					this.setCoins(this.coins - status.getX().getPrice());
					state = status.isPurchased();
				}
			}
		}
		return state;
	}

	/**
	 * Finds the Object o in the List list and, if the current coins are enough and
	 * the item hasn't been previously purchased, it purchases it
	 * 
	 * @param o                 the object to be purchased
	 * @param purchaseStatusList List of purchase statuses of PricedBackground items
	 * @return true if the given object gets purchased, false otherwise
	 */
	private boolean findAndBuySceneries(Object o, List<PurchaseStatus<PricedBackground>> purchaseStatusList) {
		boolean state = false;
		for (var status : purchaseStatusList) {
			if (status.getX().equals(o)) {
				if (!status.isPurchased() && status.getX().getPrice() <= this.coins) {
					status.purchase();
					this.setCoins(this.coins - status.getX().getPrice());
					state = status.isPurchased();
				}
			}
		}
		return state;
	}

	/**
	 * Through a Scanner, the method reads the savings file to get the coins amount,
	 * the initial purchase status of all skins and sceneries
	 * 
	 * @throws FileNotFoundException
	 */
	private void getFileInfo() throws FileNotFoundException {

		Scanner scanner = new Scanner(this.savingsFile);

		int counter = 0;
		while (scanner.hasNextLine()) {
			String nextScanner = scanner.next();
			if (counter == 0) {
				this.coins = Integer.parseInt(nextScanner);

			} else if (counter == 1) {
				this.getSkinsInfo(nextScanner, this.skins);

			} else if (counter == 2) {
				this.getScenerisInfo(nextScanner, this.sceneries);
				break;
			}
			counter++;
		}
		scanner.close();
	}

	/**
	 * Creates skinsNum PurchaseStatus of PricedSkin objects and initializes them,
	 * checks the line, word by word, to get the current PurchaseStatus
	 * 
	 * @param line one line of the savings file
	 * @param list List of purchase statuses of PricedSkin items
	 */
	private void getSkinsInfo(String line, List<PurchaseStatus<PricedSkin>> list) {
		String[] lineWords = line.split(",");

		for (int i = 0; i < skinsNum; i++) {
			PurchaseStatus<PricedSkin> purchaseStatus = new PurchaseStatus<PricedSkin>(
					new PricedSkin(skinInitialize.get(i), CommonMethods.getImageResource(skinInitialize.get(i)),
							CommonMethods.getPixelsFromPercentageHeight(10),
							CommonMethods.getPixelsFromPercentageHeight(10), prices.get(i)),
					false);

			if (lineWords[i].equals("1")) {
				purchaseStatus.purchase();
			}

			list.add(purchaseStatus);
		}
	}

	/**
	 * Creates sceneriesNum PurchaseStatus of PricedBackground objects and
	 * initializes them, checks the line, word by word, to get the current
	 * PurchaseStatus
	 * 
	 * @param line one line of the savings file
	 * @param list List of purchase statuses of PricedBackground items
	 */
	private void getScenerisInfo(String line, List<PurchaseStatus<PricedBackground>> list) {
		String[] lineWords = line.split(",");

		for (int i = 0; i < sceneriesNum; i++) {
			PurchaseStatus<PricedBackground> purchaseStatus = new PurchaseStatus<PricedBackground>(
					new PricedBackground(backgroundInitialize.get(i),
							CommonMethods.getImageResource(backgroundInitialize.get(i)), prices.get(i)),
					false);

			if (lineWords[i].equals("1")) {
				purchaseStatus.purchase();
			}

			list.add(purchaseStatus);
		}
	}

	/**
	 * A method that updates the savings file at the end of a game
	 * 
	 * @throws IOException
	 */
	public void fileUpdate() throws IOException {
		FileWriter shopFileWriter = new FileWriter(savingsFile, false);

		if (!skins.isEmpty() && !sceneries.isEmpty()) {
			shopFileWriter.append(this.coins + "\n");
			shopFileWriter.append(this.overwritePurchaseStatusLine(this.skins) + "\n");
			shopFileWriter.append(this.overwritePurchaseStatusLine(this.sceneries) + "\n");
		} else {
			shopFileWriter.write(Constants.SAVINGS_FILE_START_CONTENT);
		}

		shopFileWriter.close();
	}

	/**
	 * The method creates the new line of information for the savings file
	 * 
	 * @param <X>  The items type
	 * @param list List of purchase statuses of generic X items
	 * @return the line that will be overwritten over an old line to update the
	 *         savings file
	 */
	private <X> String overwritePurchaseStatusLine(List<PurchaseStatus<X>> list) {
		String line = "";
		for (var purchaseStatus : list) {
			if (purchaseStatus.isPurchased()) {
				if (line.isEmpty()) {
					line += "1";
				} else {
					line += ",1";
				}
			} else {
				if (line.isEmpty()) {
					line += "0";
				} else {
					line += ",0";
				}
			}
		}
		return line;
	}

	/**
	 * Used to clear data from the lists of Purchase Statuses.
	 */
	public void clearSavings() {
		this.skins.clear();
		this.sceneries.clear();
	}

	/**
	 * The method initializes two ArrayLists that keep the skins/sceneries' string
	 * names.
	 */
	private void initializeStrings() {
		skinInitialize.add("Floppa");
		skinInitialize.add("Sogga");
		skinInitialize.add("Capibara");
		skinInitialize.add("Quokka");
		skinInitialize.add("Buding");
		this.skinsNum = skinInitialize.size();

		backgroundInitialize.add("Classic");
		backgroundInitialize.add("Beach");
		backgroundInitialize.add("Woods");
		backgroundInitialize.add("Space");
		backgroundInitialize.add("NeonCity");
		this.sceneriesNum = backgroundInitialize.size();
	}
}

package main.menu.shop;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
	private Integer coins;
	private Map<String, Image> skinInitialize = new HashMap<>();
	private Map<String, Image> backgroundInitialize = new HashMap<>();
	private List<Integer> prices = Arrays.asList(0, 50, 100, 200, 500);
	private List<PurchaseStatus<PricedSkin>> skins;
	private List<PurchaseStatus<PricedBackground>> sceneries;
	private File savingFile;

	public Shop() {
		this.MapInitializing();

		skins = new ArrayList<>();
		sceneries = new ArrayList<>();

		this.savingFile = new File(Constants.SAVINGS_FILE_PATH);

		try {
			this.getFileInfo();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the current coins amount
	 */
	public Integer getCoins() {
		return coins;
	}

	/**
	 * Sets the coins field
	 * 
	 * @param coins
	 */
	public void setCoins(Integer coins) {
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
	protected boolean buy(Object o) {
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
	 * @param o    the object to be purchased
	 * @param list List of purchase statuses of PricedBackground items
	 * @return true if the given object gets purchased, false otherwise
	 */
	private boolean findAndBuySceneries(Object o, List<PurchaseStatus<PricedBackground>> list) {
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
	 * Through a Scanner, the method reads the savings file to get the coins amount,
	 * the initial purchase status of all skins and sceneries
	 * 
	 * @throws FileNotFoundException
	 */
	private void getFileInfo() throws FileNotFoundException {

		Scanner scanner = new Scanner(this.savingFile);

		int counter = 0;
		while (scanner.hasNextLine()) {
			Scanner nextscanner = new Scanner(scanner.nextLine());
			if (counter == 0) {
				while (nextscanner.hasNext()) {
					String word = nextscanner.next();
					this.coins = Integer.parseInt(word);
				}
			} else if (counter == 1) {
				this.getSkinsInfo(scanner, this.skins);

			} else if (counter == 2) {
				this.getScenerisInfo(scanner, this.sceneries);
				break;
			}
			counter++;
		}
		scanner.close();
	}

	/**
	 * Creates skinsNum PurchaseStatus of PricedSkin objects and initializes them,
	 * then through the scanner and another method, checks the actual purchase
	 * status of the PricedSkin objects
	 * 
	 * @param scanner one line of the savings file
	 * @param list    List of purchase statuses of PricedSkin items
	 */
	private void getSkinsInfo(Scanner scanner, List<PurchaseStatus<PricedSkin>> list) {
		Iterator<Entry<String, Image>> iterator = skinInitialize.entrySet().stream().iterator();

		for (int i = 0; i < skinsNum; i++) {
			var item = iterator.next();
			PurchaseStatus<PricedSkin> purchaseStatus = new PurchaseStatus<PricedSkin>(
					new PricedSkin(item.getKey(), item.getValue(), prices.get(i)), false);

			this.getIfPurchased(scanner, purchaseStatus);

			list.add(purchaseStatus);
		}
	}

	/**
	 * Creates sceneriesNum PurchaseStatus of PricedBackground objects and
	 * initializes them, then through the scanner and another method, checks the
	 * actual purchase status of the PricedBackground objects
	 * 
	 * @param scanner one line of the savings file
	 * @param list    List of purchase statuses of PricedBackground items
	 */
	private void getScenerisInfo(Scanner scanner, List<PurchaseStatus<PricedBackground>> list) {
		Iterator<Entry<String, Image>> iterator = backgroundInitialize.entrySet().stream().iterator();

		for (int i = 0; i < sceneriesNum; i++) {
			var item = iterator.next();
			PurchaseStatus<PricedBackground> purchaseStatus = new PurchaseStatus<PricedBackground>(
					new PricedBackground(item.getKey(), item.getValue(), prices.get(i)), false);

			this.getIfPurchased(scanner, purchaseStatus);

			list.add(purchaseStatus);
		}
	}

	/**
	 * A method that updates the savings file at the end of a game
	 * 
	 * @throws IOException
	 */
	public void fileUpdate() throws IOException {
		Path path = this.savingFile.toPath();
		List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));

		for (int i = 0; i < fileContent.size(); i++) {
			if (i == 0) {
				fileContent.set(i, this.coins.toString());
			} else if (i == 1) {
				String newLine = this.overwritePurchaseStatusLine(this.skins);
				fileContent.set(i, newLine);
			} else {
				String newLine = this.overwritePurchaseStatusLine(this.sceneries);
				fileContent.set(i, newLine);
				break;
			}
		}
		Files.write(path, fileContent, StandardCharsets.UTF_8);
	}

	/**
	 * The method creates the new line of information for the savings file
	 * 
	 * @param <X>  The items type
	 * @param list List of purchase statuses of generic X items
	 * @return the line that will be overwritten over an old line to update the
	 *         savings file
	 */
	@SuppressWarnings("null")
	private <X> String overwritePurchaseStatusLine(List<PurchaseStatus<X>> list) {
		String line = null;
		list.forEach(status -> {
			if (status.isPurchased()) {
				if (line.isEmpty()) {
					line.concat("1");
				} else {
					line.concat(",1");
				}
			} else {
				if (line.isEmpty()) {
					line.concat("0");
				} else {
					line.concat(",0");
				}
			}
		});
		return line;
	}

	/**
	 * Reads the line provided by the scanner word per word, if the word is a 1 it
	 * means the item has been purchased so the method saves this info through the
	 * PurchaseStatus parameter
	 * 
	 * @param <X>            The items type
	 * @param scanner        a line of the savings file
	 * @param purchaseStatus the purchase status whose info are needed
	 */
	private <X> void getIfPurchased(Scanner scanner, PurchaseStatus<X> purchaseStatus) {
		if (scanner.hasNext()) {
			String word = scanner.next();
			if (word.equals("1")) {
				purchaseStatus.purchase();
			} else if (word.equals(",")) {
				word = scanner.next();
				if (word.equals("1")) {
					purchaseStatus.purchase();
				}
			}
		}
	}

	/**
	 * The method initializes a map that keeps entries of skins/sceneries names and
	 * their corresponding Image names
	 */
	private void MapInitializing() {
		skinInitialize.put("Floppa", CommonMethods.getImageResource("Floppa"));
		skinInitialize.put("Sogga", CommonMethods.getImageResource("Sogga"));
		skinInitialize.put("Capibara", CommonMethods.getImageResource("Capibara"));
		skinInitialize.put("Quokka", CommonMethods.getImageResource("Quokka"));
		skinInitialize.put("Buding", CommonMethods.getImageResource("Buding"));
		this.skinsNum = skinInitialize.size();

		backgroundInitialize.put("Classic", CommonMethods.getImageResource("Classic"));
		backgroundInitialize.put("Beach", CommonMethods.getImageResource("Beach"));
		backgroundInitialize.put("Woods", CommonMethods.getImageResource("Woods"));
		backgroundInitialize.put("Space", CommonMethods.getImageResource("Space"));
		backgroundInitialize.put("NeonCity", CommonMethods.getImageResource("NeonCity"));
		this.sceneriesNum = backgroundInitialize.size();
	}
}

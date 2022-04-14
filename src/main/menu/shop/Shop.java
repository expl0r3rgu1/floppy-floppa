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

	public Integer getCoins() {
		return coins;
	}

	public void setCoins(Integer coins) {
		this.coins = coins;
	}

	public List<PurchaseStatus<PricedSkin>> getSkins() {
		return skins;
	}

	public List<PurchaseStatus<PricedBackground>> getSceneries() {
		return sceneries;
	}

	protected boolean buy(Object o) {
		if (o.getClass().equals(PricedSkin.class)) {
			return findAndBuySkins(o, this.skins);
		} else {
			return findAndBuySceneries(o, this.sceneries);
		}
	}

	public int getSkinsNum() {
		return skinsNum;
	}

	public int getSceneriesNum() {
		return sceneriesNum;
	}

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
				counter++;
				continue;
			} else if (counter == 1) {
				this.getSkinsInfo(scanner, this.skins);
				counter++;
				continue;
			} else if (counter == 2) {
				this.getScenerisInfo(scanner, this.sceneries);
			}
			break;
		}
		scanner.close();
	}

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

	@SuppressWarnings("null")
	private <X> String overwritePurchaseStatusLine(List<PurchaseStatus<X>> list) {
		String line = null;
		list.forEach(status -> {
			if (status.isPurchased()) {
				if (line.isEmpty()) {
					line.concat("1");
				} else {
					line.concat(" 1");
				}
			} else {
				if (line.isEmpty()) {
					line.concat("0");
				} else {
					line.concat(" 0");
				}
			}
		});
		return line;
	}

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

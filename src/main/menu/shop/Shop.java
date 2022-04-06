package main.menu.shop;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.infinite_map.PricedBackground;
import main.utilities.PricedSkin;

enum TYPE {
	SKIN,
	SCENERY
}

public class Shop {
	private static Integer coins;
	private static List<PurchaseStatus<PricedSkin>> skins;
	private static List<PurchaseStatus<PricedBackground>> sceneries;
	private File savingFile;

	public Shop() {
		skins = new ArrayList<>();
		sceneries = new ArrayList<>();
		this.savingFile = new File("file.txt");
		try {
			this.getFileInfo();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Integer getCoins() {
		return coins;
	}

	public void setCoins(Integer coins) {
		Shop.coins = coins;
	}

	public static List<PurchaseStatus<PricedSkin>> getSkins() {
		return skins;
	}

	public static List<PurchaseStatus<PricedBackground>> getSceneries() {
		return sceneries;
	}

	protected static boolean buy(Object o) {
		if (o.getClass().equals(PricedSkin.class)) {
			return findAndBuy(o, Shop.skins);
		} else {
			return findAndBuy(o, Shop.sceneries);
		}
	}

	private static <X> boolean findAndBuy(Object o, List<PurchaseStatus<X>> list) {
		for(var status : list) {
			if(status.getX().equals(o)) {
				if(!status.isPurchased() && status.getX().getPrice() <= Shop.coins) { //TODO
					return status.isPurchased();
				}
			}
		}
	}

	private void getFileInfo() throws FileNotFoundException {

		Scanner scanner = new Scanner(this.savingFile);

		int counter = 0;
		while (scanner.hasNextLine()) {
			Scanner nextscanner = new Scanner(scanner.nextLine());
			if (counter == 0) {
				while (nextscanner.hasNext()) {
					String word = nextscanner.next();
					Shop.coins = Integer.parseInt(word);
				}
				counter++;
				continue;
			} else if (counter == 1) {
				this.getFileInfoSupport(nextscanner, Shop.skins, PricedSkin);
				counter++;
				continue;
			} else if (counter == 2) {
				this.getFileInfoSupport(nextscanner, Shop.sceneries, PricedBackground);
			}
			break;
		}
		scanner.close();
	}

	private <X> void getFileInfoSupport(Scanner scanner, List<PurchaseStatus<X>> list, String type) {
		while (scanner.hasNext()) {
			PurchaseStatus<X> purchaseStatus = new PurchaseStatus<X>(null , false);
			String word = scanner.next();
			if (word.equals("1")) {
				purchaseStatus.purchase();
			}
			list.add(purchaseStatus);
		}
	}
	
	public void coinsUpdate(Integer coinsWon) {
		Shop.coins += coinsWon;
	}

	private void fileUpdate() throws IOException {
		Path path = this.savingFile.toPath();
		List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));

		for (int i = 0; i < fileContent.size(); i++) {
			if (i == 0) {
				fileContent.set(i, Shop.coins.toString());
			} else if (i == 1) {
				String newLine = this.overwritePurchaseStatusLine(Shop.skins);
				fileContent.set(i, newLine);
			} else {
				String newLine = this.overwritePurchaseStatusLine(Shop.sceneries);
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
}

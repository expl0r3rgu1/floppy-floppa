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
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import main.infinite_map.Background;
import main.utilities.Skin;

public class Shop {
	private Integer coins;
	private Set<PurchaseStatus<Skin>> skins;
	private Set<PurchaseStatus<Background>> sceneries;
	private File savingFile;

	public Shop() {
		skins = new HashSet<>();
		sceneries = new HashSet<>();
		this.savingFile = new File("file.txt");
		getFileInfo();
	}

	private void buy(Object o) {
		if (o.getClass().equals(Skin.class)) {
			findAndBuy(o, this.skins);
		} else {
			findAndBuy(o, this.sceneries);
		}
	}

	private <X> void findAndBuy(Object o, Set<PurchaseStatus<X>> set) {
		set.forEach(status -> {
			if (status.getX().equals(o)) {
				if (!status.isPurchesed() && status.getX().getPrice() <= this.coins) {
					status.purchase();
				}
			}
		});
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
				while (nextscanner.hasNext()) {
					PurchaseStatus<Skin> purchaseStatus = new PurchaseStatus<>();
					String word = nextscanner.next();
					if (word.equals("1")) {
						purchaseStatus.purchase();
					}
					this.skins.add(purchaseStatus);
				}
				counter++;
				continue;
			} else if (counter == 2) {
				while (nextscanner.hasNext()) {
					PurchaseStatus<Background> purchaseStatus = new PurchaseStatus<>();
					String word = nextscanner.next();
					if (word.equals("1")) {
						purchaseStatus.purchase();
					}
					this.sceneries.add(purchaseStatus);
				}
			}
			break;
		}
		scanner.close();
	}

	private void fileUpdate() {
		Path path = this.savingFile.toPath();
		List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));

		for (int i = 0; i < fileContent.size(); i++) {
			if (i == 0) {
				fileContent.set(i, this.coins.toString());
			} else if (i == 1) {
				String newLine = this.overwritePurchaseStatusLine(this.skins);
				fileContent.set(i, newLine);
			} else {
				String newLine = this.overwritePurchaseStatusLine(sceneries);
				fileContent.set(i, newLine);
				break;
			}
		}
		Files.write(path, fileContent, StandardCharsets.UTF_8);
	}
	
	private <X> String overwritePurchaseStatusLine(Set<X> set) {
		String line = null;
		set.forEach(status -> {
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
	
	public void show(Graphics2D canvas) {

	}
}

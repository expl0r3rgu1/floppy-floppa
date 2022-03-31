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

import main.infinite_map.Background;
import main.utilities.Skin;

public class Shop {
	private Integer coins;
	private List<PurchaseStatus<Skin>> skins;
	private List<PurchaseStatus<Background>> sceneries;
	private File savingFile;

	public Shop() {
		skins = new ArrayList<>();
		sceneries = new ArrayList<>();
		this.savingFile = new File("file.txt");
		getFileInfo();
	}

	public Integer getCoins() {
		return this.coins;
	}

	public List<PurchaseStatus<Skin>> getSkins() {
		return this.skins;
	}

	public List<PurchaseStatus<Background>> getSceneries() {
		return this.sceneries;
	}

	private void buy(Object o) {
		if (o.getClass().equals(Skin.class)) {
			findAndBuy(o, this.skins);
		} else {
			findAndBuy(o, this.sceneries);
		}
	}

	private <X> void findAndBuy(Object o, List<PurchaseStatus<X>> list) {
		list.forEach(status -> {
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
				this.getFileInfoSupport(nextscanner, this.skins);
				counter++;
				continue;
			} else if (counter == 2) {
				this.getFileInfoSupport(nextscanner, this.sceneries);
			}
			break;
		}
		scanner.close();
	}

	private <X> void getFileInfoSupport(Scanner scanner, List<PurchaseStatus<X>> list) {
		while (scanner.hasNext()) {
			PurchaseStatus<X> purchaseStatus = new PurchaseStatus<>();
			String word = scanner.next();
			if (word.equals("1")) {
				purchaseStatus.purchase();
			}
			list.add(purchaseStatus);
		}
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

	private <X> String overwritePurchaseStatusLine(List<X> list) {
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

	public void show(Graphics2D canvas) {

	}
}

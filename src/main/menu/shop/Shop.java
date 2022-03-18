package main.menu.shop;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import main.infinite_map.Background;
import main.utilities.Skin;

public class Shop {
	private Integer coins;
	private Set<PurchaseStatus<Skin>> skins;
	private Set<PurchaseStatus<Background>> sceneries;

	public Shop() {
		skins = new HashSet<>();
		sceneries = new HashSet<>();
		getFileInfo();
	}

	public void show(Graphics2D canvas) {
		
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
			if (status.getX.equals(o)) {
				if (!status.isPurchesed && status.getX.getPrice <= this.coins) {
					status.purchase();
				}
			}
		});
	}

	private void getFileInfo() {
		Scanner scanner = new Scanner("file.txt");

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
	}

}

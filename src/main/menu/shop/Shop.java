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

import javax.imageio.ImageIO;

import main.utilities.PricedBackground;
import main.utilities.PricedSkin;

public class Shop {
	private static Integer coins;
	private Map<String, Image> skinInitialize = new HashMap<>();
	private Map<String, Image> backgroundInitialize = new HashMap<>();
	private List<Integer> prices = Arrays.asList(0, 50, 100, 200, 500);
	private static List<PurchaseStatus<PricedSkin>> skins;
	private static List<PurchaseStatus<PricedBackground>> sceneries;
	private File savingFile;

	public Shop() {
		this.MapInitializing();

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

	public static void setCoins(Integer coins) {
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
			return findAndBuySkins(o, Shop.skins);
		} else {
			return findAndBuySceneries(o, Shop.sceneries);
		}
	}

	private static boolean findAndBuySkins(Object o, List<PurchaseStatus<PricedSkin>> list) {
		boolean state = false;
		for (var status : list) {
			if (status.getX().equals(o)) {
				if (!status.isPurchased() && status.getX().getPrice() <= Shop.coins) {
					status.purchase();
					Shop.setCoins(Shop.coins - status.getX().getPrice());
					state = status.isPurchased();
				}
			}
		}
		return state;
	}

	private static boolean findAndBuySceneries(Object o, List<PurchaseStatus<PricedBackground>> list) {
		boolean state = false;
		for (var status : list) {
			if (status.getX().equals(o)) {
				if (!status.isPurchased() && status.getX().getPrice() <= Shop.coins) {
					status.purchase();
					Shop.setCoins(Shop.coins - status.getX().getPrice());
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
					Shop.coins = Integer.parseInt(word);
				}
				counter++;
				continue;
			} else if (counter == 1) {
				this.getSkinsInfo(scanner, Shop.skins);
				counter++;
				continue;
			} else if (counter == 2) {
				this.getScenerisInfo(scanner, Shop.sceneries);
			}
			break;
		}
		scanner.close();
	}

	private void getSkinsInfo(Scanner scanner, List<PurchaseStatus<PricedSkin>> list) {
		while (scanner.hasNext()) {
			PurchaseStatus<PricedSkin> purchaseStatus = null;
			Iterator<Entry<String, Image>> iterator = skinInitialize.entrySet().stream().iterator();

			for (int i = 0; i < 5; i++) {
				var item = iterator.next();
				purchaseStatus = new PurchaseStatus<PricedSkin>(
						new PricedSkin(item.getKey(), item.getValue(), prices.get(i)), false);
			}

			String word = scanner.next();
			if (word.equals("1")) {
				purchaseStatus.purchase();
			}
			list.add(purchaseStatus);
		}

	}

	private void getScenerisInfo(Scanner scanner, List<PurchaseStatus<PricedBackground>> list) {
		while (scanner.hasNext()) {
			PurchaseStatus<PricedBackground> purchaseStatus = null;
			Iterator<Entry<String, Image>> iterator = backgroundInitialize.entrySet().stream().iterator();

			for (int i = 0; i < 5; i++) {
				var item = iterator.next();
				purchaseStatus = new PurchaseStatus<PricedBackground>(
						new PricedBackground(item.getKey(), item.getValue(), prices.get(i)), false);
			}

			String word = scanner.next();
			if (word.equals("1")) {
				purchaseStatus.purchase();
			}
			list.add(purchaseStatus);
		}
	}

	public void fileUpdate() throws IOException {
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

	private Image imageCreation(String name) {
		Image image = null;
		try {
			image = ImageIO.read(getClass().getResource("/resources/images/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	private void MapInitializing() {
		skinInitialize.put("Floppa", imageCreation("Floppa"));
		skinInitialize.put("Sogga", imageCreation("Sogga"));
		skinInitialize.put("Capibara", imageCreation("Capibara"));
		skinInitialize.put("Quokka", imageCreation("Quokka"));
		skinInitialize.put("Buding", imageCreation("Buding"));

		backgroundInitialize.put("Classic", imageCreation("Classic"));
		backgroundInitialize.put("Beach", imageCreation("Beach"));
		backgroundInitialize.put("Woods", imageCreation("Woods"));
		backgroundInitialize.put("Space", imageCreation("Space"));
		backgroundInitialize.put("NeonCity", imageCreation("NeonCity"));
	}
}

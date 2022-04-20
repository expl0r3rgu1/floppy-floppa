package main.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.infinite_map.ScrollingBackground;
import main.menu.shop.PurchaseStatus;
import main.menu.shop.Shop;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.GameSettings;
import main.utilities.GBCSimplified;
import main.utilities.GraphicJButton;
import main.utilities.GraphicJButtonWithObject;
import main.utilities.GraphicJLabel;
import main.utilities.PricedBackground;
import main.utilities.PricedSkin;
import main.character.Character;

public class SelectionPanel extends JPanel {

	private static final long serialVersionUID = -7631305128085484196L;
	private GridBagLayout grid = new GridBagLayout();
	private Character character;
	private ScrollingBackground scenario;
	private GameSettings settings;
	private MainMenu mainMenu;
	private Shop shop;
	private Image background;
	private int numSkins;
	private int numBackgrounds;
	private boolean equip = false;
	private boolean bought = false;
	public static final int dimension = (Constants.SCREEN_SIZE.getHeight()) / 12;
	private List<PurchaseStatus<PricedSkin>> skinList;
	private List<PurchaseStatus<PricedBackground>> backgroundList;
	private ArrayList<String> labelNames = new ArrayList<>(Arrays.asList("Floppa", "Sogga", "Capibara", "Quokka",
			"Buding", "Classic", "Beach", "Woods", "Space", "NeonCity"));

	public SelectionPanel(MainMenu mainMenu, GameSettings settings) {

		shop = new Shop();
		this.mainMenu = mainMenu;
		this.settings = settings;
		this.setLayout(grid);

		CommonMethods.getImageResource("Background");

		this.setPreferredSize(Constants.SCREEN_SIZE);
		this.setOpaque(false);

		numSkins = shop.getSkinsNum();
		numBackgrounds = shop.getSceneriesNum();
		skinList = shop.getSkins();
		backgroundList = shop.getSceneries();
		this.character = character;
		this.scenario = scenario;

		this.placeComponents();
	}

	private void placeComponents() {

		for (int i = 0; i < numBackgrounds + numSkins; i++) {
			if (i == 1) {

				GraphicJLabel skins = new GraphicJLabel("SKINS", Color.decode("#FFDD62"), Color.decode("#FF971A"),
						"fipps.otf", (float) Constants.SCREEN_SIZE.getWidth() / 100);
				this.add(skins, new GBCSimplified(0, i, (CommonMethods.getPixelsFromPercentage(2)), 0,
						new Insets((CommonMethods.getPixelsFromPercentage(1)), 0, 0, 0)));

			} else if (i == 5) {

				GraphicJLabel backgrounds = new GraphicJLabel("BACKGROUNDS", Color.decode("#FFDD62"),
						Color.decode("#FF971A"), "fipps.otf", (float) Constants.SCREEN_SIZE.getWidth() / 100);
				this.add(backgrounds, new GBCSimplified(0, i, (CommonMethods.getPixelsFromPercentage(2)), 0,
						new Insets((CommonMethods.getPixelsFromPercentage(3)), 0, 0, 0)));

			} else if (i == numBackgrounds + numSkins - 1) {

				JButton backMenu = generateButton("MENU", "fipps.otf");
				this.add(backMenu, new GBCSimplified(4, i, 0, 0, new Insets((CommonMethods.getPixelsFromPercentage(2)),
						0, (CommonMethods.getPixelsFromPercentage(1)), 0)));
				backMenu.addActionListener(e -> {
					mainMenu.showCard(Constants.PANEL.MENU);
				});

			} else if (i == 1 || i == 5) {

				i = this.getLNameImage(i);

			} else if (i == 3 || i == 7) {

				this.getSelectButton(i);

			}
		}
	}

	private int getLNameImage(int i) {
		for (int j = 0; j < numSkins; j++) {
			GraphicJLabel label = new GraphicJLabel((i == 2 ? labelNames.get(j) : labelNames.get(j + 5)),
					Color.decode("#77DD77"), Color.decode("#007542"), "fipps.otf",
					(float) Constants.SCREEN_SIZE.getWidth() / 120);
			this.add(label,
					new GBCSimplified(j, i, 0, 0, new Insets((CommonMethods.getPixelsFromPercentage(2)), 0, 0, 0)));
			this.add(this.imageCreation((i == 1 ? labelNames.get(j) : labelNames.get(j + 5))),
					new GBCSimplified(j, i + 1, 0, 0, new Insets((CommonMethods.getPixelsFromPercentage(2)), 0, 0, 0)));
		}
		return i + 1;
	}

	private void getSelectButton(int i) {
		for (int j = 0; j < numSkins; j++) {
			JButton selectButton = generateButton("SELECT", "fipps.otf");
			bought = (i == 4 ? skinList.get(j).isPurchased() : backgroundList.get(j).isPurchased());
			bought = false;
			selectButton.setEnabled(bought);
			final int index = j;
			selectButton.addActionListener(e -> {
				getImageSkin(i, index);
			});
			this.add(selectButton,
					new GBCSimplified(j, i, (CommonMethods.getPixelsFromPercentage(3)), 0,
							new Insets((CommonMethods.getPixelsFromPercentage(1)),
									(CommonMethods.getPixelsFromPercentage(5)), 0,
									(CommonMethods.getPixelsFromPercentage(5)))));
		}
	}

	private void getImageSkin(int i, int j) {

		if (i == 4) {

			Skin skin = new Skin(labelNames.get(j), CommonMethods.getImageResource(labelNames.get(j)), dimension,
					dimension);
			settings.setSkin(skin);

		} else {
			ScrollingBackground scenario = new ScrollingBackground(labelNames.get(j + 5),
					CommonMethods.getImageResource(labelNames.get(j + 5)));
			settings.setScrollingBackground(scenario);
		}

	}

	private JButton generateButton(String name, String font) {
		JButton jb = new JButton(name);

		if (name.equals("MENU")) {
			jb.setFont(CommonMethods.getFontResource(font).deriveFont((float) Constants.SCREEN_SIZE.getWidth() / 100));
			jb.setBackground(Color.decode("#FFDD62"));
			jb.setBorder(BorderFactory.createLineBorder(Color.decode("#FF971A"), 4, true));
		} else {
			jb.setFont(CommonMethods.getFontResource(font).deriveFont((float) Constants.SCREEN_SIZE.getWidth() / 120));
			jb.setBackground(Color.decode("#FDFD96"));
			jb.setBorder(BorderFactory.createLineBorder(Color.decode("#FFDD62"), 4, true));

		}
		return jb;
	}

	private JLabel imageCreation(String fileName) {
		JLabel label = null;
		Image image = CommonMethods.getImageResource(fileName);
		ImageIcon imageIcon = new ImageIcon(this.scale(image,
				new Dimension((CommonMethods.getPixelsFromPercentage(8)), (CommonMethods.getPixelsFromPercentage(8)))));
		label = new JLabel(imageIcon);
		return label;
	}

	private Image scale(Image image, Dimension dim) {
		return image.getScaledInstance((int) dim.getWidth(), (int) dim.getHeight(), Image.SCALE_DEFAULT);
	}

}

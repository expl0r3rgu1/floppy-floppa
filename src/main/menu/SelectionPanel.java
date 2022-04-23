package main.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.infinite_map.ScrollingBackground;
import main.menu.shop.PurchaseStatus;
import main.menu.shop.Shop;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.GameSettings;
import main.utilities.GraphicJButton;
import main.utilities.GBCSimplified;
import main.utilities.GraphicJLabel;
import main.utilities.PricedBackground;
import main.utilities.PricedSkin;
import main.utilities.Skin;

/**
 * SelectionPanel is a class that creates the JPanel to select a new icon for
 * the Character or a new Background
 */
public class SelectionPanel extends JPanel {

	private static final long serialVersionUID = -7631305128085484196L;
	private final GridBagLayout grid;
	private final GameSettings settings;
	private final MainMenu mainMenu;
	private final Shop shop;
	private final int numSkins;
	private final int numBackgrounds;
	private boolean bought = false;
	private final List<PurchaseStatus<PricedSkin>> skinList;
	private final List<PurchaseStatus<PricedBackground>> backgroundList;
	private final ArrayList<String> labelNames = new ArrayList<>(Arrays.asList("Floppa", "Sogga", "Capibara", "Quokka",
			"Buding", "Classic", "Beach", "Woods", "Space", "NeonCity"));

	/**
	 * @param mainMenu - used to show cardlayouts
	 * @param settings - to change the Skin of the Character or the Background of
	 *                 the game
	 */
	public SelectionPanel(MainMenu mainMenu, GameSettings settings) {

		this.mainMenu = mainMenu;
		this.settings = settings;
		shop = new Shop();
		grid = new GridBagLayout();
		this.setLayout(grid);

		CommonMethods.getImageResource("Background");

		this.setPreferredSize(Constants.SCREEN_SIZE);
		this.setOpaque(false);

		numSkins = shop.getSkinsNum();
		numBackgrounds = shop.getSceneriesNum();
		skinList = shop.getSkins();
		backgroundList = shop.getSceneries();

		this.placeComponents();
	}

	/**
	 * placeComponents is a method to create and place all the components of the
	 * JPanel
	 */
	private void placeComponents() {

		for (int i = 0; i < numBackgrounds + numSkins; i++) {
			if (i == 0) {

				GraphicJLabel skins = new GraphicJLabel("SKINS", Color.decode("#FFDD62"), Color.decode("#FF971A"),
						"fipps", (float) Constants.SCREEN_SIZE.getWidth() / 100);
				this.add(skins, new GBCSimplified(0, i, (CommonMethods.getPixelsFromPercentageWidth(2)), 0,
						new Insets((CommonMethods.getPixelsFromPercentageHeight(1)), 0, 0, 0)));

			} else if (i == 4) {

				GraphicJLabel backgrounds = new GraphicJLabel("BACKGROUNDS", Color.decode("#FFDD62"),
						Color.decode("#FF971A"), "fipps", (float) Constants.SCREEN_SIZE.getWidth() / 100);
				this.add(backgrounds, new GBCSimplified(0, i, (CommonMethods.getPixelsFromPercentageWidth(2)), 0,
						new Insets((CommonMethods.getPixelsFromPercentageHeight(3)), 0, 0, 0)));

			} else if (i == numBackgrounds + numSkins - 1) {

				GraphicJButton backMenu = new GraphicJButton("MENU", "fipps", 100, "#FFDD62", "#FF971A");
				this.add(backMenu,
						new GBCSimplified(4, i, 0, 0, new Insets((CommonMethods.getPixelsFromPercentageHeight(2)), 0,
								(CommonMethods.getPixelsFromPercentageHeight(1)), 0)));
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

	/**
	 * getLNameImage is a method that thanks to i, using an ArrayList to get the
	 * names and a for it creates on two lines a firstly a JLabel of the names of
	 * the various Skins and Backgrounds, using GraphicJLabel, then it creates the
	 * images from calling the method getJLabelImage
	 * 
	 * @param i - int to get the Skin or the Background by looking at the line
	 * @return new int line to skip the ones newly made
	 */
	private int getLNameImage(int i) {
		for (int j = 0; j < numSkins; j++) {
			GraphicJLabel label = new GraphicJLabel((i == 1 ? labelNames.get(j) : labelNames.get(j + 5)),
					Color.decode("#77DD77"), Color.decode("#007542"), "fipps",
					(float) Constants.SCREEN_SIZE.getWidth() / 120);
			this.add(label, new GBCSimplified(j, i, 0, 0,
					new Insets((CommonMethods.getPixelsFromPercentageHeight(2)), 0, 0, 0)));
			this.add(CommonMethods.getJLabelImage((i == 1 ? labelNames.get(j) : labelNames.get(j + 5)), 8), new GBCSimplified(j,
					i + 1, 0, 0, new Insets((CommonMethods.getPixelsFromPercentageHeight(2)), 0, 0, 0)));
		}
		return i + 1;
	}

	/**
	 * getSelectButton is a method that creates a JButton, using GraphicJButton, for
	 * every Skin or Background, then by reading in the skinList and the
	 * backgroundList, it can be checked if the object is been bought by the player,
	 * and if it bought the button will be enabled and then can be equiped by the
	 * player
	 * 
	 * @param i - int to get the Skin or the Background by looking at the line
	 */
	private void getSelectButton(int i) {
		for (int j = 0; j < numSkins; j++) {
			final int index = j;
			GraphicJButton selectButton = new GraphicJButton("SELECT", "fipps", 120, "#FDFD96", "#FFDD62");

			bought = (i == 3 ? skinList.get(j).isPurchased() : backgroundList.get(j).isPurchased());
			selectButton.setEnabled(bought);

			selectButton.addActionListener(e -> {
				getSelectedIcon(i, index);
			});

			this.add(selectButton,
					new GBCSimplified(j, i, (CommonMethods.getPixelsFromPercentageWidth(3)), 0,
							new Insets((CommonMethods.getPixelsFromPercentageHeight(1)),
									(CommonMethods.getPixelsFromPercentageHeight(5)), 0,
									(CommonMethods.getPixelsFromPercentageHeight(5)))));
		}
	}

	/**
	 * getSelectedIcon is a method that equips the new Skin or the new Background by
	 * using settings
	 * 
	 * @param i - int to get the Skin or the Background by looking at the line
	 * @param j - int to get from the ArrayList labelName the name of the icon
	 */
	private void getSelectedIcon(int i, int j) {

		if (i == 3) {

			Skin skin = new Skin(labelNames.get(j), CommonMethods.getImageResource(labelNames.get(j)),
					Constants.SKIN_DIMENSION, Constants.SKIN_DIMENSION);
			settings.setSkin(skin);

		} else {
			ScrollingBackground scenario = new ScrollingBackground(labelNames.get(j + 5),
					CommonMethods.getImageResource(labelNames.get(j + 5)));
			settings.setScrollingBackground(scenario);
		}

	}

}
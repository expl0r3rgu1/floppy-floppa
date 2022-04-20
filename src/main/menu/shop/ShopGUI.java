package main.menu.shop;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.menu.MainMenu;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.GBCSimplified;
import main.utilities.GraphicJButton;
import main.utilities.GraphicJButtonWithObject;
import main.utilities.GraphicJLabel;

/**
 * A class that shows Shop
 *
 */

public class ShopGUI extends JPanel {

	private static final long serialVersionUID = -7631305128085484196L;
	private int numSkins;
	private int numBackgrounds;
	private MainMenu mainMenu;
	private Shop shop;
	private ArrayList<String> labelNames = new ArrayList<>(Arrays.asList("Floppa", "Sogga", "Capibara", "Quokka",
			"Buding", "Classic", "Beach", "Woods", "Space", "NeonCity"));
	private ArrayList<String> prices = new ArrayList<>(
			Arrays.asList("0", "50", "100", "200", "500", "0", "50", "100", "200", "500"));

	/**
	 * @param mainMenu needed to correctly show card layouts
	 */
	public ShopGUI(MainMenu mainMenu) {

		shop = new Shop();
		this.mainMenu = mainMenu;
		this.setLayout(new GridBagLayout());
		this.setOpaque(false);

		numSkins = shop.getSkinsNum();
		numBackgrounds = shop.getSceneriesNum();

		CommonMethods.getImageResource("Background");

		this.placeGUIComponents();
	}

	/**
	 * @return The Shop associated with the GUI
	 */
	public Shop getShop() {
		return shop;
	}

	/**
	 * Returns a scaled version of the image parameter
	 * 
	 * @param image the image to scale
	 * @param dim   the Dimension to which to scale the image
	 * @return a scaled version of the image parameter
	 */
	private Image scale(Image image, Dimension dim) {
		return image.getScaledInstance((int) dim.getWidth(), (int) dim.getHeight(), Image.SCALE_DEFAULT);
	}

	/**
	 * The method reads an image file and creates the corresponding Image object
	 * which gets also scaled
	 * 
	 * @param fileName
	 * @return a JLabel containing the newly created Image
	 */
	private JLabel imageCreation(String fileName) {
		JLabel label = null;
		Image image = CommonMethods.getImageResource(fileName);
		ImageIcon imageIcon = new ImageIcon(this.scale(image, new Dimension(
				(CommonMethods.getPixelsFromPercentageHeight(8)), (CommonMethods.getPixelsFromPercentageHeight(8)))));
		label = new JLabel(imageIcon);
		return label;
	}

	/**
	 * Main class method, it correctly creates and placed the various GUI components
	 */
	private void placeGUIComponents() {

		for (int i = 0; i < numBackgrounds + numSkins; i++) {
			if (i == 0) {

				GraphicJLabel coins = new GraphicJLabel(shop.getCoins() + "", Color.decode("#FFDD62"),
						Color.decode("#FF971A"), "Arial", Font.BOLD);
				this.add(coins, new GBCSimplified(4, i, 0, 0, new Insets(0, 0, 0, 0)));

			} else if (i == 1) {

				GraphicJLabel skins = new GraphicJLabel("SKINS", Color.decode("#FFDD62"), Color.decode("#FF971A"),
						"Arial", Font.BOLD);
				skins.setPreferredSize(new Dimension((CommonMethods.getPixelsFromPercentageWidth(10)),
						(CommonMethods.getPixelsFromPercentageHeight(5))));
				this.add(skins,
						new GBCSimplified(0, i, (CommonMethods.getPixelsFromPercentageHeight(2)), 0,
								new Insets((CommonMethods.getPixelsFromPercentageHeight(1)), 0,
										(CommonMethods.getPixelsFromPercentageHeight(2)), 0)));
			} else if (i == 5) {

				GraphicJLabel backgrounds = new GraphicJLabel("BACKGROUNDS", Color.decode("#FFDD62"),
						Color.decode("#FF971A"), "Arial", Font.BOLD);
				backgrounds.setPreferredSize(new Dimension((CommonMethods.getPixelsFromPercentageWidth(10)),
						(CommonMethods.getPixelsFromPercentageHeight(5))));
				this.add(backgrounds,
						new GBCSimplified(0, i, (CommonMethods.getPixelsFromPercentageWidth(2)), 0,
								new Insets((CommonMethods.getPixelsFromPercentageHeight(3)), 0,
										(CommonMethods.getPixelsFromPercentageHeight(1)), 0)));

			} else if (i == numBackgrounds + numSkins - 1) {

				GraphicJButton backMenu = new GraphicJButton("MENU", Color.decode("#FFDD62"), Color.decode("#FF971A"),
						"Arial", Font.BOLD);
				this.add(backMenu, new GBCSimplified(4, i, 0, 0,
						new Insets((CommonMethods.getPixelsFromPercentageHeight(3)), 0, 0, 0)));
				backMenu.addActionListener(e -> {
					mainMenu.showCard(Constants.PANEL.MENU);
				});

			} else if (i == 2 || i == 6) {
				i = this.placeGUIComponentsSupport(i);
			}
		}
	}

	/**
	 * Considering the grid as a matrix, the method creates and places labels and
	 * buttons either for PricedSkin items or PricedBackground items depending on
	 * the given argument i
	 * 
	 * @param i Index used to correctly place components in the GridBagLayout
	 * @return the index to correctly placed the next items
	 */
	private int placeGUIComponentsSupport(int i) {
		for (int j = 0; j < numSkins; j++) {
			GraphicJLabel label = new GraphicJLabel(
					(i == 2 ? labelNames.get(j) + " : " + prices.get(j)
							: labelNames.get(j + 5) + " : " + prices.get(j + 5)),
					Color.decode("#77DD77"), Color.decode("#007542"), "Arial", Font.PLAIN);

			this.add(label, new GBCSimplified(j, i, 0, 0,
					new Insets((CommonMethods.getPixelsFromPercentageHeight(2)), 0, 0, 0)));

			this.add(this.imageCreation((i == 2 ? labelNames.get(j) : labelNames.get(j + 5))), new GBCSimplified(j,
					i + 1, 0, 0, new Insets((CommonMethods.getPixelsFromPercentageHeight(2)), 0, 0, 0)));

			GraphicJButtonWithObject buyButton = new GraphicJButtonWithObject("BUY", Color.decode("#FDFD96"),
					Color.decode("#FFDD62"), "Arial", Font.PLAIN,
					(i == 2 ? shop.getSkins().get(j).getX() : shop.getSceneries().get(j).getX()));

			if (i == 2) {
				buyButton.setEnabled(!shop.getSkins().get(j).isPurchased());
			} else {
				buyButton.setEnabled(!shop.getSceneries().get(j).isPurchased());
			}

			buyButton.addActionListener(e -> {
				GraphicJButtonWithObject button = (GraphicJButtonWithObject) e.getSource();
				boolean bought = shop.buy(button.getObject());
				buyButton.setEnabled(!bought);
			});

			this.add(buyButton,
					new GBCSimplified(j, i + 2, (CommonMethods.getPixelsFromPercentageWidth(3)), 0,
							new Insets((CommonMethods.getPixelsFromPercentageHeight(2)),
									(CommonMethods.getPixelsFromPercentageWidth(5)), 0,
									(CommonMethods.getPixelsFromPercentageWidth(5)))));
		}
		return i + 2;
	}
}
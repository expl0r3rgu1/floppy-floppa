package main.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

import main.menu.shop.PurchaseStatus;
import main.menu.shop.Shop;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.GBCSimplified;
import main.utilities.GraphicJButton;
import main.utilities.GraphicJButtonWithObject;
import main.utilities.GraphicJLabel;
import main.utilities.PricedSkin;

public class SkinSelectionPanel extends JPanel {

	private static final long serialVersionUID = -7631305128085484196L;
	private GridBagLayout grid = new GridBagLayout();
	private Image background;
	private int numSkins;
	private int numBackgrounds;
	private boolean equip = false;
	private MainMenu mainMenu;
	private Shop shop;
	private List<PurchaseStatus<PricedSkin>> list;
	private ArrayList<String> labelNames = new ArrayList<>(Arrays.asList("Floppa", "Sogga", "Capibara", "Quokka",
			"Buding", "Classic", "Beach", "Woods", "Space", "NeonCity"));

	public SkinSelectionPanel(MainMenu mainMenu) {

		shop = new Shop();
		this.mainMenu = mainMenu;
		this.setLayout(grid);

		CommonMethods.getImageResource("Background");

		this.setPreferredSize(Constants.SCREEN_SIZE);
		this.setOpaque(false);

		numSkins = shop.getSkinsNum();
		numBackgrounds = shop.getSceneriesNum();
		
		list = shop.getSkins();
		list.get(0).isPurchased()//se è true allora è stato comprato
		
		this.placeLabels();

		this.setVisible(true);
	}

	private void placeLabels() {

		for (int i = 0; i < numBackgrounds + numSkins; i++) {
			if (i == 1) {

				GraphicJLabel skins = new GraphicJLabel("SKINS", Color.decode("#FFDD62"), Color.decode("#FF971A"),
						"Arial", CommonMethods.getPixelsFromPercentage(10));
				this.add(skins, new GBCSimplified(0, i, (CommonMethods.getPixelsFromPercentage(2)), 0, new Insets(
						(CommonMethods.getPixelsFromPercentage(1)), 0, (CommonMethods.getPixelsFromPercentage(2)), 0)));

			} else if (i == 5) {

				GraphicJLabel backgrounds = new GraphicJLabel("BACKGROUNDS", Color.decode("#FFDD62"),
						Color.decode("#FF971A"), "Arial", Font.BOLD);
				backgrounds.setPreferredSize(new Dimension((CommonMethods.getPixelsFromPercentage(10)),
						(CommonMethods.getPixelsFromPercentage(5))));
				this.add(backgrounds, new GBCSimplified(0, i, (CommonMethods.getPixelsFromPercentage(2)), 0, new Insets(
						(CommonMethods.getPixelsFromPercentage(3)), 0, (CommonMethods.getPixelsFromPercentage(1)), 0)));

			} else if (i == numBackgrounds + numSkins - 1) {

				GraphicJButton backMenu = new GraphicJButton("MENU", Color.decode("#FFDD62"), Color.decode("#FF971A"),
						"Arial", Font.BOLD);
				this.add(backMenu,
						new GBCSimplified(4, i, 0, 0, new Insets((CommonMethods.getPixelsFromPercentage(3)), 0, 0, 0)));

			}
		}
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

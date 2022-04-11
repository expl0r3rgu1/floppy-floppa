package main.menu.shop;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.menu.MainMenu;
import main.utilities.Constants;
import main.utilities.GraphicJButton;
import main.utilities.GraphicJButtonWithObject;
import main.utilities.GraphicJLabel;

public class ShopGUI extends JPanel {

	private static final long serialVersionUID = -7631305128085484196L;
	private Image background;
	private int numSkins;
	private int numBackgrounds;
	private boolean bought = false;
	private MainMenu mainMenu;
	private Shop shop;
	private ArrayList<String> labelNames = new ArrayList<>(Arrays.asList("Floppa", "Sogga", "Capibara", "Quokka",
			"Buding", "Classic", "Beach", "Woods", "Space", "NeonCity"));
	private ArrayList<String> prices = new ArrayList<>(
			Arrays.asList("0", "50", "100", "200", "500", "0", "50", "100", "200", "500"));

	public ShopGUI(MainMenu mainMenu) {

		shop = new Shop();
		this.mainMenu = mainMenu;
		this.setLayout(new GridBagLayout());

		numSkins = shop.getSkinsNum();
		numBackgrounds = shop.getSceneriesNum();

		try {
			background = ImageIO.read(getClass().getResource("/resources/images/Background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.placeGUIComponents();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.scale(background, Constants.SCREEN_SIZE), 0, 0, null);
	}

	private Image scale(Image image, Dimension dim) {
		return image.getScaledInstance((int) dim.getWidth(), (int) dim.getHeight(), Image.SCALE_DEFAULT);
	}

	private JLabel imageCreation(String fileName) {
		JLabel label = null;
		try {
			Image image = ImageIO.read(getClass().getResource("/resources/images/" + fileName));
			ImageIcon imageIcon = new ImageIcon(this.scale(image,
					new Dimension((int) (Constants.SCREEN_SIZE.getWidth() * 8 / 100), (int) (Constants.SCREEN_SIZE.getWidth() * 8 / 100))));
			label = new JLabel(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return label;
	}

	private void placeGUIComponents() {

		for (int i = 0; i < numBackgrounds + numSkins; i++) {
			if (i == 0) {

				GraphicJLabel coins = new GraphicJLabel(shop.getCoins() + "", Color.decode("#FFDD62"),
						Color.decode("#FF971A"), "Arial", Font.BOLD);
				this.add(coins, setDimensionObject(4, i, 0, 0, new Insets(0, 0, 0, 0)));

			} else if (i == 1) {

				GraphicJLabel skins = new GraphicJLabel("SKINS", Color.decode("#FFDD62"), Color.decode("#FF971A"),
						"Arial", Font.BOLD);
				skins.setPreferredSize(
						new Dimension((int) Constants.SCREEN_SIZE.getWidth() * 10 / 100, (int) Constants.SCREEN_SIZE.getHeight() * 5 / 100));
				this.add(skins, setDimensionObject(0, i, (int) Constants.SCREEN_SIZE.getWidth() * 2 / 100, 0,
						new Insets((int) Constants.SCREEN_SIZE.getWidth() * 1 / 100, 0, (int) Constants.SCREEN_SIZE.getWidth() * 2 / 100, 0)));

			} else if (i == 5) {

				GraphicJLabel backgrounds = new GraphicJLabel("BACKGROUNDS", Color.decode("#FFDD62"),
						Color.decode("#FF971A"), "Arial", Font.BOLD);
				backgrounds.setPreferredSize(
						new Dimension((int) Constants.SCREEN_SIZE.getWidth() * 10 / 100, (int) Constants.SCREEN_SIZE.getHeight() * 5 / 100));
				this.add(backgrounds, setDimensionObject(0, i, (int) Constants.SCREEN_SIZE.getWidth() * 2 / 100, 0,
						new Insets((int) Constants.SCREEN_SIZE.getWidth() * 3 / 100, 0, (int) Constants.SCREEN_SIZE.getWidth() * 1 / 100, 0)));

			} else if (i == numBackgrounds + numSkins - 1) {

				GraphicJButton backMenu = new GraphicJButton("MENU", Color.decode("#FFDD62"), Color.decode("#FF971A"),
						"Arial", Font.BOLD);
				this.add(backMenu,
						setDimensionObject(4, i, 0, 0, new Insets((int) Constants.SCREEN_SIZE.getWidth() * 3 / 100, 0, 0, 0)));
				backMenu.addActionListener(e -> {
					mainMenu.showCard("MENU");
				});

			} else if (i == 2 || i == 6) {
				i = this.placeGUIComponentsSupport(i);
			}
		}
	}

	private int placeGUIComponentsSupport(int i) {
		for (int j = 0; j < numSkins; j++) {
			GraphicJLabel label = new GraphicJLabel(
					(i == 2 ? labelNames.get(j) + " : " + prices.get(j)
							: labelNames.get(j + 5) + " : " + prices.get(j + 5)),
					Color.decode("#77DD77"), Color.decode("#007542"), "Arial", Font.PLAIN);
			this.add(label, setDimensionObject(j, i, 0, 0, new Insets((int) Constants.SCREEN_SIZE.getWidth() * 2 / 100, 0, 0, 0)));
			this.add(this.imageCreation((i == 2 ? labelNames.get(j) : labelNames.get(j + 5)) + ".png"),
					setDimensionObject(j, i + 1, 0, 0, new Insets((int) Constants.SCREEN_SIZE.getWidth() * 2 / 100, 0, 0, 0)));
			GraphicJButtonWithObject buyButton = new GraphicJButtonWithObject("BUY", Color.decode("#FDFD96"),
					Color.decode("#FFDD62"), "Arial", Font.PLAIN,
					(i == 2 ? shop.getSkins().get(j).getX() : shop.getSceneries().get(j).getX()));
			buyButton.addActionListener(e -> {
				bought = shop.buy(buyButton.getObject());
				buyButton.setEnabled(!bought);
			});
			this.add(buyButton,
					setDimensionObject(j, i + 2, (int) Constants.SCREEN_SIZE.getWidth() * 3 / 100, 0,
							new Insets((int) Constants.SCREEN_SIZE.getWidth() * 2 / 100, (int) Constants.SCREEN_SIZE.getWidth() * 5 / 100, 0,
									(int) Constants.SCREEN_SIZE.getWidth() * 5 / 100)));
		}
		return i + 2;
	}

	private GridBagConstraints setDimensionObject(int gridx, int gridy, int ipadx, int ipady, Insets i) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = gridx;
		c.gridy = gridy;
		c.ipadx = ipadx;
		c.ipady = ipady;
		c.insets = i;
		return c;
	}
}
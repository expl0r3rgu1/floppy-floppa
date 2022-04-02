package main.menu.shop;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShopGUI extends JPanel {

	private static final long serialVersionUID = -7631305128085484196L;
	private static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private Image background;
	private int numSkins;
	private int numBackgrounds;
	private ArrayList<String> labelNames = new ArrayList<>(Arrays.asList("Floppa", "Sogga", "Capibara", "Quokka",
			"Buding", "Vintage", "Beach", "Woods", "Space", "NeonCity"));
	private ArrayList<String> prices = new ArrayList<>(
			Arrays.asList("0", "50", "100", "200", "500", "0", "50", "100", "200", "500"));

	public ShopGUI() {
		this.setLayout(new GridBagLayout());

		numSkins = Shop.getSkins().size();
		numBackgrounds = Shop.getSceneries().size();

		try {
			background = ImageIO.read(getClass().getResource("/resources/images/shopBackground.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.placeGUIComponents();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.scale(background, SIZE), 0, 0, this);
	}

	private Image scale(Image image, Dimension dim) {
		return image.getScaledInstance((int) dim.getWidth(), (int) dim.getHeight(), Image.SCALE_DEFAULT);
	}

	private JLabel imageCreation(String fileName) {
		JLabel label = null;
		try {
			Image image = ImageIO.read(getClass().getResource("/test/" + fileName));
			ImageIcon imageIcon = new ImageIcon(this.scale(image,
					new Dimension((int) (SIZE.getWidth() * 8 / 100), (int) (SIZE.getWidth() * 8 / 100))));
			label = new JLabel(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return label;
	}

	private void placeGUIComponents() {

		for (int i = 0; i < numBackgrounds + numSkins; i++) {
			if (i == 0) {

				CustomJLabel coins = new CustomJLabel(Shop.getCoins() + "", Color.decode("#FFDD62"),
						Color.decode("#FF971A"), "Arial", Font.BOLD);
				this.placeCustomLabels(i, /* Shop.getCoins() + "" */ "COINS: 100", "#FFDD62", "#FF971A", 4, i, 0, 0,
						0.9, 0);

			} else if (i == 1) {

				CustomJLabel skins = this.placeCustomLabels(i, "SKINS", "#FFDD62", "#FF971A", 0, i,
						(int) SIZE.getWidth() * 2 / 100, 0, 1, 1);
				skins.setPreferredSize(
						new Dimension((int) SIZE.getWidth() * 10 / 100, (int) SIZE.getHeight() * 5 / 100));

			} else if (i == 5) {

				CustomJLabel backgrounds = this.placeCustomLabels(i, "BACKGROUNDS", "#FFDD62", "#FF971A", 0, i,
						(int) SIZE.getWidth() * 2 / 100, 0, 1, 1);
				backgrounds.setPreferredSize(
						new Dimension((int) SIZE.getWidth() * 10 / 100, (int) SIZE.getHeight() * 5 / 100));

			} else if (i == numBackgrounds + numSkins - 1) {

				CustomJButton backMenu = new CustomJButton("MENU", null, Color.decode("#FFDD62"),
						Color.decode("#FF971A"), "Arial", Font.BOLD);
				this.add(backMenu, setDimensionObject(4, i, 0, 0, 1, 1));
				backMenu.addActionListener(e -> {
//					frame.cardLayout.show(frame.panel, "menu");
					System.exit(0);
				});

			} else if (i == 2 || i == 6) {
				i = this.placeGUIComponentsSupport(i);
			}
		}
	}

	private int placeGUIComponentsSupport(int i) {
		for (int j = 0; j < numSkins; j++) {
			Object o = new Object(); // non mettere in ShopGUI effettiva
			CustomJLabel label = new CustomJLabel(
					(i == 2 ? labelNames.get(j) + " : " + prices.get(j)
							: labelNames.get(j + 5) + " : " + prices.get(j + 5)),
					Color.decode("#77DD77"), Color.decode("#007542"), "Arial", Font.PLAIN);
			this.add(label, setDimensionObject(j, i, 0, 0, 1, 1));
			this.add(this.imageCreation((i == 2 ? labelNames.get(j) : labelNames.get(j + 5)) + ".png"),
					setDimensionObject(j, i + 1, 0, 0, 1, 1));
			CustomJButton buyButton = new CustomJButton("BUY",
					(i == 2 ? Shop.getSkins().get(j).getX() : Shop.getSceneries().get(j).getX()),
					Color.decode("#FDFD96"), Color.decode("#FFDD62"), "Arial", Font.PLAIN);
			buyButton.addActionListener(e -> {
				Shop.buy(buyButton.getObject());
			});
			this.add(buyButton, setDimensionObject(j, i + 2, (int) SIZE.getWidth() * 3 / 100, 0, 1, 1));
		}
		return i + 2;
	}

	private CustomJLabel placeCustomLabels(int i, String name, String backgroundColor, String BorderColor, int gridx,
			int gridy, int ipadx, int ipady, double weightx, double weighty) {
		CustomJLabel customLabel = new CustomJLabel(name, Color.decode(backgroundColor), Color.decode(BorderColor),
				"Arial", Font.BOLD);
		this.add(customLabel, setDimensionObject(gridx, gridy, ipadx, ipady, weightx, weighty));
		return customLabel;
	}

	private GridBagConstraints setDimensionObject(int gridx, int gridy, int ipadx, int ipady, double weightx,
			double weighty) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = gridx;
		c.gridy = gridy;
		c.ipadx = ipadx;
		c.ipady = ipady;
		c.weightx = weightx;
		c.weighty = weighty;
		return c;
	}
}
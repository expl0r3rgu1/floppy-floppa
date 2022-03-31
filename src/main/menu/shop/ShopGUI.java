package main.menu.shop;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.activation.ActivationGroupID;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.print.attribute.HashAttributeSet;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ShopGUI extends JPanel {

	private static final long serialVersionUID = -7631305128085484196L;
	private static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private Image background;
	private GridBagLayout grid = new GridBagLayout();
	private GridBagConstraints gbc = new GridBagConstraints();
	private int numSkins;
	private int numBackgrounds;

	public ShopGUI() {
		this.setLayout(grid);

		try {
			background = ImageIO.read(getClass().getResource("/resources/images/shopBackground.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		numSkins = Shop.getSkins().size();
		numBackgrounds = Shop.getSceneries().size();

		for (int i = 0; i < numSkins; i++) {
			JLabel label = new JLabel("Skin n. " + i);
			BuyButton buyButton = new BuyButton("BUY", Shop.getSkins().get(i).getX());
			this.add(buyButton);
			this.labelSetting(label, true, Color.decode("#77DD77"));
			this.buttonSetting(buyButton, Color.decode("#FDFD96"));
			this.gridSettingLabel(i * 2, 11, 2, 2, label);
			this.gridSettingButton(i * 2, 13, 2, 1, buyButton);
		}

		for (int i = 0; i < numBackgrounds; i++) {
			JLabel label = new JLabel("Background n. " + i);
			BuyButton buyButton = new BuyButton("BUY", Shop.getSceneries().get(i).getX());
			this.add(buyButton);
			this.labelSetting(label, true, Color.decode("#77DD77"));
			this.buttonSetting(buyButton, Color.decode("#FDFD96"));
			this.gridSettingLabel(i * 2, 18, 2, 2, label);
			this.gridSettingButton(i * 2, 20, 2, 1, buyButton);
		}

		ActionListener al = e -> {
			var button = (BuyButton) e.getSource();
			Shop.buy(button.getObject());
		};

		JLabel coins = new JLabel(Shop.getCoins() + "");
		JLabel skinTitle = new JLabel("Skins");
		JLabel sceneriesTitle = new JLabel("Backgrounds");

		this.labelSetting(coins, true, Color.decode("#FFDD62"));
		this.labelSetting(skinTitle, true, Color.decode("#FFDD62"));
		this.labelSetting(sceneriesTitle, true, Color.decode("#FFDD62"));

		this.gridSettingLabel(20, 0, 3, 3, coins);
		this.gridSettingLabel(0, 8, 2, 1, skinTitle);
		this.gridSettingLabel(0, 15, 2, 1, sceneriesTitle);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(scale(background), 0, 0, this);
	}

	private Image scale(Image image) {
		return image.getScaledInstance((int) SIZE.getWidth(), (int) SIZE.getHeight(), Image.SCALE_DEFAULT);
	}

	private void gridSettingLabel(int x, int y, int width, int height, JLabel label) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		this.add(label, gbc);
	}

	private void gridSettingButton(int x, int y, int width, int height, JButton button) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		this.add(button, gbc);
	}

	private void labelSetting(JLabel label, Boolean opaqueness, Color backgroundColor) {
		label.setOpaque(opaqueness);
		label.setBackground(backgroundColor);
	}

	private void buttonSetting(JButton button, Color backgroundColor) {
		button.setBackground(backgroundColor);
	}
}

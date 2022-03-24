package main.menu.shop;

import java.awt.*;
import java.io.IOException;
import java.rmi.activation.ActivationGroupID;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.print.attribute.HashAttributeSet;
import javax.swing.BorderFactory;
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
		
//		numSkins = Shop.getSkins().size();
//		numBackgrounds = Shop.getSceneries().size();
		numSkins = 5;
		numBackgrounds = 5;
		
		for(int i = 0; i < numSkins; i++) {
			JLabel image = new JLabel("Skin n. " + i);
			this.labelSetting(image, true, Color.green);
			this.gridSetting(i*2, 3, 2, 2, image);
		}
		
		for(int i = 0; i < numBackgrounds; i++) {
			JLabel scenery = new JLabel("Background n. " + i);
			this.labelSetting(scenery, true, Color.green);
			this.gridSetting(i*2, 7, 2, 2, scenery);
		}

		JLabel coins = new JLabel(/* Shop.getCoins() + "" */ "0");
		JLabel skinTitle = new JLabel("Skins");
		JLabel sceneriesTitle = new JLabel("Backgrounds");
		
		this.labelSetting(coins, true, Color.yellow);
		this.labelSetting(skinTitle, true, Color.yellow);
		this.labelSetting(sceneriesTitle, true, Color.yellow);

		this.gridSetting(4, 0, 1, 1, coins);
		this.gridSetting(0, 1, 2, 1, skinTitle);
		this.gridSetting(0, 5, 2, 1, sceneriesTitle);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(scale(background), 0, 0, this);
	}

	private Image scale(Image image) {
		return image.getScaledInstance((int) SIZE.getWidth(), (int) SIZE.getHeight(), Image.SCALE_DEFAULT);
	}
	
	private void gridSetting(int x, int y, int width, int height, JLabel label) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		this.add(label, gbc);
	}
	
	private void labelSetting(JLabel label,Boolean opaqueness, Color backgroundColor) {
		label.setOpaque(opaqueness);
		label.setBackground(backgroundColor);
	}
}

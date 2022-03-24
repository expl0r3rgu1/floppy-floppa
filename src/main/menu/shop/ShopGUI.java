package main.menu.shop;

import java.awt.*;
import java.io.IOException;
import java.rmi.activation.ActivationGroupID;

import javax.imageio.ImageIO;
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

	public ShopGUI() {
		this.setLayout(grid);

		try {
			background = ImageIO.read(getClass().getResource("/resources/images/shopBackground.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel coins = new JLabel(/* Shop.getCoins() + "" */ "0");
		JLabel skinTitle = new JLabel("Skins");
		JLabel sceneriesTitle = new JLabel("Backgrounds");
		coins.setOpaque(true);
		skinTitle.setOpaque(true);
		sceneriesTitle.setOpaque(true);
		coins.setBackground(Color.yellow);
		skinTitle.setBackground(Color.yellow);
		sceneriesTitle.setBackground(Color.yellow);

		this.gridSetting(2, 0, 1, 1, coins);

		this.gridSetting(0, 1, 2, 1, skinTitle);
		
		this.gridSetting(0, 2, 2, 1, sceneriesTitle);
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
}

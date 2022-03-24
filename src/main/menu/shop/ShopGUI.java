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
	private GridBagLayout gLayout = new GridBagLayout();

	public ShopGUI() {
		this.setLayout(grid);
		
		try {
			background = ImageIO.read(getClass().getResource("/resources/images/shopBackground.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JLabel coins = new JLabel(/*Shop.getCoins() + ""*/ "0");
		JLabel skinTitle = new JLabel("Skins");
//		coins.setBorder(BorderFactory.createLineBorder(Color.black));
		
		gbc.gridx = 1;
	    gbc.gridy = 0;
	    this.add(coins, gbc);
		
		gbc.gridx = 0;
	    gbc.gridy = 0;
	    this.add(skinTitle, gbc);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(scale(background), 0, 0, this);
	}

	private Image scale(Image image) {
		return image.getScaledInstance((int) SIZE.getWidth(), (int) SIZE.getHeight(), Image.SCALE_DEFAULT);
	}
}

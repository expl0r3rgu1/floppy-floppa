package main.menu.shop;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ShopGUI extends JPanel {

	private static final long serialVersionUID = -7631305128085484196L;
	private static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private Image background;

	public ShopGUI() {

		try {
			background = ImageIO.read(getClass().getResource("/resources/images/shopBackground.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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

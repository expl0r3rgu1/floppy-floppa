package main.menu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class EOGMenuGUI extends JPanel{
	
	private static final long serialVersionUID = -6834009314888626973L;
	private static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private Image background;
	
	public EOGMenuGUI() {
		this.setLayout(new GridBagLayout());

		try {
			background = ImageIO.read(getClass().getResource("/resources/images/Background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.scale(background, SIZE), 0, 0, null);
	}
	
	private Image scale(Image image, Dimension dim) {
		return image.getScaledInstance((int) dim.getWidth(), (int) dim.getHeight(), Image.SCALE_DEFAULT);
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

package main.menu;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
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
			background = ImageIO.read(getClass().getResource("/test/Background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

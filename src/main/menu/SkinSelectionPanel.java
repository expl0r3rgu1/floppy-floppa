package main.menu;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import main.utilities.Constants;

public class SkinSelectionPanel extends JPanel{
	
	private static final long serialVersionUID = -7631305128085484196L;
	private GridBagLayout grid = new GridBagLayout();
	private Image background;
	private MainMenu mainMenu;
	
	public SkinSelectionPanel(MainMenu mainMenu) {
			
		this.mainMenu = mainMenu;
		this.setLayout(grid);

		try {
			background = ImageIO.read(getClass().getResource("/resources/images/Background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setPreferredSize(Constants.SCREEN_SIZE);
		this.setOpaque(false);

		addTitle();

		addPlayButton();

		addButton("Leaderboard", 1, 1, 7, 5, 0, 0, 2, 0);
//		addButton("Clear Saves", 3, 1, 7, 5, 0, 0, 2, 0);
//		addButton("Shop", 3, 3, 7, 5, 2, 0, 0, 0);
//		addButton("Tutorial", 1, 3, 7, 5, 2, 0, 0, 0);
//		addButton("Quit", 2, 4, 6, 4, 1, 0, 3, 0);
		// addButton("", 3, 1, 7, 5, 0, 0, 2, 0);

//		addImage("Floppa.png", 1, 2, 0, 0, 0, 0, 0, 2);
//		addImage("Bingus.png", 3, 2, 0, 0, 0, 2, 0, 0);

		this.setVisible(true);
	}
	
	private void addButton(String name, int gridx, int gridy, int ipadx, int ipady, int top, int left, int bottom,
			int right) {
		String actionListenerName = name.toUpperCase();

		this.add(generateButton(name, actionListenerName, "fipps.otf"),
				setDimensionObj(gridx, gridy, (int) Constants.SCREEN_SIZE.getWidth() * ipadx / 100, (int) Constants.SCREEN_SIZE.getHeight() * ipady / 100,
						new Insets((int) Constants.SCREEN_SIZE.getWidth() * top / 100, (int) Constants.SCREEN_SIZE.getWidth() * left / 100,
								(int) Constants.SCREEN_SIZE.getWidth() * bottom / 100, (int) Constants.SCREEN_SIZE.getWidth() * right / 100)));
	}

}

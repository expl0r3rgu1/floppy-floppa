package main.menu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.menu.MainMenu;
import main.utilities.GraphicJLabel;
import main.utilities.Constants;
import main.utilities.CommonMethods;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.CardLayout;

/**
 * Menu class implements the panel for the menu
 **/
public class MenuPanel extends JPanel {

	private static final long serialVersionUID = -7631305128085484196L;
	private GridBagLayout grid = new GridBagLayout();
	private MainMenu mainMenu;

	/**
	 * 
	 * @param used to show cardlayouts
	 * 
	 **/
	public MenuPanel(MainMenu mainMenu) {

		this.mainMenu = mainMenu;
		this.setLayout(grid);

		this.setPreferredSize(Constants.SCREEN_SIZE);
		this.setOpaque(false);

		addTitle();

		addPlayButton();

		addButton("Leaderboard", 1, 1, 7, 5, 0, 0, 2, 0);
		addButton("Clear Saves", 3, 1, 7, 5, 0, 0, 2, 0);
		addButton("Shop", 3, 3, 7, 5, 2, 0, 0, 0);
		addButton("Tutorial", 1, 3, 7, 5, 2, 0, 0, 0);
		addButton("Quit", 2, 4, 6, 4, 1, 0, 3, 0);
		addButton("Select", 2, 3, 7, 5, 0, 0, 0, 0);

		addImage("Floppa.png", 1, 2, 0, 0, 0, 0, 0, 2);
		addImage("Bingus.png", 3, 2, 0, 0, 0, 2, 0, 0);

		this.setVisible(true);
	}

	/*
	 * addTitle is a class method that creates and adds the title label of the menu
	 */
	private void addTitle() {
		GraphicJLabel title = new GraphicJLabel("Floppy Floppa", Color.decode("#8EDCFB"), Color.decode("#3288FE"),
				"pixel.TTF", (float) Constants.SCREEN_SIZE.getWidth() / 25);

		title.setOpaque(false);

		this.add(title, setDimensionObj(2, 0, 0, 0, new Insets((int) Constants.SCREEN_SIZE.getWidth() * 3 / 100, 0,
				(int) Constants.SCREEN_SIZE.getWidth() * 3 / 100, 0)));
	}

	/*
	 * addPlayButton is a class method that creates and adds the play button
	 */
	private void addPlayButton() {
		this.add(generateButton("Play", "PLAY", "fipps.otf"),
				setDimensionObj(2, 2, (int) Constants.SCREEN_SIZE.getWidth() * 15 / 100,
						(int) Constants.SCREEN_SIZE.getHeight() * 10 / 100, new Insets(0, 0, 0, 0)));
	}

	/*
	 * addButton is a class method that creates and adds the various buttons of the
	 * menu
	 * 
	 * @param name - The text of the JButton
	 * 
	 * @param gridx - int for setDimensionObj
	 * 
	 * @param gridy - int for setDimensionObj
	 * 
	 * @param ipadx - int for setDimensionObj
	 * 
	 * @param ipady - int for setDimensionObj
	 * 
	 * @param top - int inset from the top for setDimensionObj
	 * 
	 * @param left - int inset from the left for setDimensionObj
	 * 
	 * @param bottom - int inset from the bottom for setDimensionObj
	 * 
	 * @param right - int inset from the right for setDimensionObj
	 */
	private void addButton(String name, int gridx, int gridy, int ipadx, int ipady, int top, int left, int bottom,
			int right) {
		String actionListenerName = name.toUpperCase();

		this.add(generateButton(name, actionListenerName, "fipps.otf"),
				setDimensionObj(gridx, gridy, (int) Constants.SCREEN_SIZE.getWidth() * ipadx / 100,
						(int) Constants.SCREEN_SIZE.getHeight() * ipady / 100,
						new Insets((int) Constants.SCREEN_SIZE.getWidth() * top / 100,
								(int) Constants.SCREEN_SIZE.getWidth() * left / 100,
								(int) Constants.SCREEN_SIZE.getWidth() * bottom / 100,
								(int) Constants.SCREEN_SIZE.getWidth() * right / 100)));
	}

	/*
	 * class method that creates and adds the images show in the menu
	 * 
	 * @param name - The name for getImageResource
	 * 
	 * @param gridx - int for setDimensionObj
	 * 
	 * @param gridy - int for setDimensionObj
	 * 
	 * @param ipadx - int for setDimensionObj
	 * 
	 * @param ipady - int for setDimensionObj
	 * 
	 * @param top - int inset from the top for setDimensionObj
	 * 
	 * @param left - int inset from the left for setDimensionObj
	 * 
	 * @param bottom - int inset from the bottom for setDimensionObj
	 * 
	 * @param right - int inset from the right for setDimensionObj
	 */
	private void addImage(String name, int gridx, int gridy, int ipadx, int ipady, int top, int left, int bottom,
			int right) {
		this.add(this.getImageResource(name),
				setDimensionObj(gridx, gridy, (int) Constants.SCREEN_SIZE.getWidth() * ipadx / 100,
						(int) Constants.SCREEN_SIZE.getHeight() * ipady / 100,
						new Insets((int) Constants.SCREEN_SIZE.getWidth() * top / 100,
								(int) Constants.SCREEN_SIZE.getWidth() * left / 100,
								(int) Constants.SCREEN_SIZE.getWidth() * bottom / 100,
								(int) Constants.SCREEN_SIZE.getWidth() * right / 100)));
	}

	/**
	 * generateButton is a method that creates a JButton and it sets its font and
	 * color, for its background and corner
	 * 
	 * @param name               - The text of the JButton
	 * @param actionListenerName - The string to pass to the adHocActionListener
	 *                           method
	 * @param font               - The name of the font needed by the text of the
	 *                           JButton
	 * @return JButton to add to the MenuPanel
	 */
	private JButton generateButton(String name, String actionListenerName, String font) {
		JButton jb = new JButton(name);

		if (name.equals("Play")) {
			jb.setFont(CommonMethods.getFontResource(font).deriveFont((float) Constants.SCREEN_SIZE.getWidth() / 90));
			jb.setBackground(Color.decode("#77DD77"));
			jb.setBorder(BorderFactory.createLineBorder(Color.decode("#007542"), 4, true));
		} else {
			if (name.equals("Quit")) {
				jb.setFont(
						CommonMethods.getFontResource(font).deriveFont((float) Constants.SCREEN_SIZE.getWidth() / 110));
				jb.setBackground(Color.decode("#FF0000"));
				jb.setBorder(BorderFactory.createLineBorder(Color.decode("#8B0000"), 4, true));
			} else {
				jb.setFont(
						CommonMethods.getFontResource(font).deriveFont((float) Constants.SCREEN_SIZE.getWidth() / 120));
				jb.setBackground(Color.decode("#FFDD62"));
				jb.setBorder(BorderFactory.createLineBorder(Color.decode("#FF971A"), 4, true));
			}
		}
		jb.addActionListener(adHocActionListener(actionListenerName));
		return jb;
	}

	private GridBagConstraints setDimensionObj(int gridx, int gridy, int ipadx, int ipady, Insets i) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.ipadx = ipadx;
		gbc.ipady = ipady;
		gbc.insets = i;
		return gbc;
	}

	/**
	 * adHocActionListener method includes the actionListener of all JButtons in the
	 * menu, depending by the given String panelName
	 * 
	 * @param panelName Used to open the jpanel needed by the player
	 * @return ActionListener needed
	 */
	private ActionListener adHocActionListener(String panelName) {

		if (panelName.equals("PLAY")) {
			return e -> {
				MainMenu.cardLayout.show(this.mainMenu, panelName);
			};
		} else if (panelName.equals("QUIT")) {
			return e -> {
				System.exit(0);
			};
		} else if (panelName.equals("CLEAR SAVINGS")) {
			return e -> {
				mainMenu.clearSavings();
			};
		} else {
			return e -> {
				MainMenu.cardLayout.show(this.mainMenu, panelName);
			};
		}
	}

	/**
	 * The method reads an image file and creates the corresponding Image object
	 * which gets also scaled
	 * 
	 * @param fileName
	 * @return a JLabel containing the newly created Image
	 */
	private JLabel getImageResource(String fileName) {
		JLabel label = null;
		try {
			Image image = ImageIO.read(getClass().getResource("/resources/images/" + fileName));
			ImageIcon imageIcon = new ImageIcon(
					this.scaleImage(image, new Dimension((int) (Constants.SCREEN_SIZE.getWidth() * 20 / 100),
							(int) (Constants.SCREEN_SIZE.getWidth() * 20 / 100))));
			label = new JLabel(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return label;
	}

	/**
	 * Returns a scaled version of the image parameter
	 * 
	 * @param image the image to scale
	 * @param dim   the Dimension to which to scale the image
	 * @return a scaled version of the image parameter
	 */
	private Image scaleImage(Image image, Dimension dim) {
		return image.getScaledInstance((int) dim.getWidth(), (int) dim.getHeight(), Image.SCALE_DEFAULT);
	}

}
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

public class MenuPanel extends JPanel{
	
	private static final long serialVersionUID = -7631305128085484196L;
	private GridBagLayout grid = new GridBagLayout();
	private Image background;
	private MainMenu mainMenu;
	
	public MenuPanel(MainMenu mainMenu) {
		
		this.mainMenu = mainMenu;
		this.setLayout(grid);

		try {
			background = ImageIO.read(getClass().getResource("/resources/images/Background.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setPreferredSize(Constants.SCREEN_SIZE);
		this.setOpaque(false);

		addTitle();

		addPlayButton();

		addButton("Leaderboard", 1, 1, 7, 5, 0, 0, 2, 0);
		addButton("Clear Saves", 3, 1, 7, 5, 0, 0, 2, 0);
		addButton("Shop", 3, 3, 7, 5, 2, 0, 0, 0);
		addButton("Tutorial", 1, 3, 7, 5, 2, 0, 0, 0);
		addButton("Quit", 2, 4, 6, 4, 1, 0, 3, 0);
		// addButton("", 3, 1, 7, 5, 0, 0, 2, 0);

		addImage("Floppa.png", 1, 2, 0, 0, 0, 0, 0, 2);
		addImage("Bingus.png", 3, 2, 0, 0, 0, 2, 0, 0);

		this.setVisible(true);
	}
	
	private void addTitle() {
		GraphicJLabel title = new GraphicJLabel("Floppy Floppa", Color.decode("#8EDCFB"), Color.decode("#3288FE"),
				"pixel.TTF", (float) Constants.SCREEN_SIZE.getWidth() / 25);

		title.setOpaque(false);

		this.add(title, setDimensionObj(2, 0, 0, 0,
				new Insets((int) Constants.SCREEN_SIZE.getWidth() * 3 / 100, 0, (int) Constants.SCREEN_SIZE.getWidth() * 3 / 100, 0)));
	}

	private void addPlayButton() {
		this.add(generateButton("Play", "PLAY", "fipps.otf"), setDimensionObj(2, 2, (int) Constants.SCREEN_SIZE.getWidth() * 15 / 100,
				(int) Constants.SCREEN_SIZE.getHeight() * 10 / 100, new Insets(0, 0, 0, 0)));
	}

	private void addButton(String name, int gridx, int gridy, int ipadx, int ipady, int top, int left, int bottom,
			int right) {
		String actionListenerName = name.toUpperCase();

		this.add(generateButton(name, actionListenerName, "fipps.otf"),
				setDimensionObj(gridx, gridy, (int) Constants.SCREEN_SIZE.getWidth() * ipadx / 100, (int) Constants.SCREEN_SIZE.getHeight() * ipady / 100,
						new Insets((int) Constants.SCREEN_SIZE.getWidth() * top / 100, (int) Constants.SCREEN_SIZE.getWidth() * left / 100,
								(int) Constants.SCREEN_SIZE.getWidth() * bottom / 100, (int) Constants.SCREEN_SIZE.getWidth() * right / 100)));
	}
	
	private void addImage(String name, int gridx, int gridy, int ipadx, int ipady, int top, int left, int bottom,
			int right) {
		this.add(this.getImageResource(name),
				setDimensionObj(gridx, gridy, (int) Constants.SCREEN_SIZE.getWidth() * ipadx / 100, (int) Constants.SCREEN_SIZE.getHeight() * ipady / 100,
						new Insets((int) Constants.SCREEN_SIZE.getWidth() * top / 100, (int) Constants.SCREEN_SIZE.getWidth() * left / 100,
								(int) Constants.SCREEN_SIZE.getWidth() * bottom / 100, (int) Constants.SCREEN_SIZE.getWidth() * right / 100)));
	}
	
	private JButton generateButton(String name, String actionListenerName, String font) {
		JButton jb = new JButton(name);

		if (name.equals("Play")) {
			jb.setFont(CommonMethods.getFontResource(font).deriveFont((float) Constants.SCREEN_SIZE.getWidth() / 90));
			jb.setBackground(Color.decode("#77DD77"));
			jb.setBorder(BorderFactory.createLineBorder(Color.decode("#007542"), 4, true));
		} else {
			if (name.equals("Quit")) {
				jb.setFont(CommonMethods.getFontResource(font).deriveFont((float) Constants.SCREEN_SIZE.getWidth() / 110));
				jb.setBackground(Color.decode("#FF0000"));
				jb.setBorder(BorderFactory.createLineBorder(Color.decode("#8B0000"), 4, true));
			} else {
				jb.setFont(CommonMethods.getFontResource(font).deriveFont((float) Constants.SCREEN_SIZE.getWidth() / 120));
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
	
	private ActionListener adHocActionListener(String panelName) {

		if (panelName.equals("PLAY")) {
			return e -> {
				MainMenu.cardLayout.show(this.mainMenu, panelName);
			};
		} else if (panelName.equals("QUIT")) {
			return e -> {
				System.exit(0);
			};
		}
		else {
			return e -> {
				MainMenu.cardLayout.show(this.mainMenu, panelName);
			};
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D canvas = (Graphics2D) g;
		canvas.drawImage(background, 0, 0, (int) Constants.SCREEN_SIZE.getWidth(), (int) Constants.SCREEN_SIZE.getHeight(), null);
	}
	
	private JLabel getImageResource(String fileName) {
		JLabel label = null;
		try {
			Image image = ImageIO.read(getClass().getResource("/resources/images/" + fileName));
			ImageIcon imageIcon = new ImageIcon(this.scaleImage(image,
					new Dimension((int) (Constants.SCREEN_SIZE.getWidth() * 20 / 100), (int) (Constants.SCREEN_SIZE.getWidth() * 20 / 100))));
			label = new JLabel(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return label;
	}
	
	private Image scaleImage(Image image, Dimension dim) {
		return image.getScaledInstance((int) dim.getWidth(), (int) dim.getHeight(), Image.SCALE_DEFAULT);
	}
	
}
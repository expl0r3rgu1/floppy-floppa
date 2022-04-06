package main.menu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.menu.MainMenu;
import main.utilities.Stages;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.CardLayout;

public class MenuPanel extends JPanel{
	
	private static Stages condition = Stages.MENU;
	
	private static final long serialVersionUID = -7631305128085484196L;
	public static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private GridBagLayout grid = new GridBagLayout();
	private Image background;
	private MainMenu mainMenu;
	
	public MenuPanel(MainMenu mainMenu) {
		
		this.mainMenu = mainMenu;
		this.setLayout(grid);

		try {
			background = ImageIO.read(getClass().getResource("/test/Background.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setPreferredSize(SIZE);
		// menu.setBackground(new Color(0,0,0,0)); mette lo sfondo trasparente
		this.setOpaque(false);
		
		JLabel title = new JLabel("Floppy Floppa");
		title.setFont(new Font("Arial", Font.PLAIN, (int) SIZE.getWidth() / 20));
		this.add(title, setDimensionObj(3, 0, new Insets(0, 0, (int) SIZE.getWidth() * 4 / 100, 0)));
		this.add(generateButton("Play", "PLAY"), setDimensionObj(3, 2, new Insets(0, 0, 0, 0)));
		this.add(generateButton("Leaderboard", "LEADERBOARD"), setDimensionObj(2, 1, new Insets((int) SIZE.getWidth() * 2 / 100, 0, 0, 0)));
		this.add(generateButton("Clear Saves", "CLEAR"), setDimensionObj(4, 1,new Insets((int) SIZE.getWidth() * 2 / 100, 0, 0, 0)));
		this.add(generateButton("Shop", "SHOP"), setDimensionObj(4, 3, new Insets((int) SIZE.getWidth() * 2 / 100, 0, 0, 0)));
		this.add(generateButton("Quit", "QUIT"), setDimensionObj(3, 4, new Insets((int) SIZE.getWidth() * 2 / 100, 0, (int) SIZE.getWidth() * 2 / 100, 0)));
		this.add(generateButton("Tutorial", "TUTORIAL"), setDimensionObj(2, 3, new Insets((int) SIZE.getWidth() * 2 / 100, 0, 0, 0)));
		this.add(this.imageCreation("Floppa.png"),setDimensionObj( 1, 2,new Insets(0, 0, 0, (int) SIZE.getWidth() * 2 / 100)));
		this.add(this.imageCreation("Bingus.png"), setDimensionObj( 5, 2,new Insets(0, (int) SIZE.getWidth() * 2 / 100, 0, 0)));
		this.setVisible(true);
	}
	
	private JButton generateButton(String name, String actionListenerName) {
		JButton jb = new JButton(name);
		jb.setFont(new Font("Arial", Font.PLAIN, (int) SIZE.getWidth() / 60));
		if(name == "Play") {
			jb.setPreferredSize(new Dimension((int)SIZE.getWidth() * 15/100, (int) SIZE.getHeight() * 10/ 100));
			jb.setBackground(Color.decode("#77DD77"));
			jb.setBorder(BorderFactory.createLineBorder(Color.decode("#007542"), 4, true));
		}else {
			jb.setPreferredSize(new Dimension((int)SIZE.getWidth() * 11/100, (int) SIZE.getHeight() * 5/ 100));
			if(name == "Quit") {
				jb.setBackground(Color.decode("#FF0000"));
				jb.setBorder(BorderFactory.createLineBorder(Color.decode("#8B0000"), 4, true));
			}else {
				jb.setBackground(Color.decode("#FFDD62"));
				jb.setBorder(BorderFactory.createLineBorder(Color.decode("#FF971A"), 4, true));
			}
		}
		jb.addActionListener(adHocActionListener(actionListenerName));
		return jb;
	}
	
	private GridBagConstraints setDimensionObj(int gridx, int gridy, Insets i) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=gridx;
		gbc.gridy = gridy;
		gbc.insets = i;
		return gbc;
	}
	
	private ActionListener adHocActionListener(String panelName) {

		if (panelName == "PLAY") {
			return e -> {
				condition = Stages.GAME;
				MainMenu.cardLayout.show(this.mainMenu, panelName);
			};
		}else if(panelName == "QUIT"){
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
		canvas.drawImage(scale(background), 0, 0, (int) SIZE.getWidth(), (int) SIZE.getHeight(), null);
	}
	
	private JLabel imageCreation(String fileName) {
		JLabel label = null;
		try {
			Image image = ImageIO.read(getClass().getResource("/test/" + fileName));
			ImageIcon imageIcon = new ImageIcon(this.scaleImage(image,
					new Dimension((int) (SIZE.getWidth() * 20 / 100), (int) (SIZE.getWidth() * 20 / 100))));
			label = new JLabel(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return label;
	}
	
	private Image scale(Image image) {
		return image.getScaledInstance((int) SIZE.getWidth(), (int) SIZE.getHeight(), Image.SCALE_DEFAULT);
	}
	
	private Image scaleImage(Image image, Dimension dim) {
		return image.getScaledInstance((int) dim.getWidth(), (int) dim.getHeight(), Image.SCALE_DEFAULT);
	}
	
}
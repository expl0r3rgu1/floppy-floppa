package main.menu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
	public static final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	private GridBagLayout grid = new GridBagLayout();
	private Image background;
	
	public MenuPanel() {
		this.setLayout(grid);

		try {
			background = ImageIO.read(getClass().getResource("/test/Background.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setPreferredSize(size);
		// menu.setBackground(new Color(0,0,0,0)); mette lo sfondo trasparente
		this.setOpaque(false);
		
		JLabel title = new JLabel("Floppy Floppa");
		title.setFont(new Font("Arial", Font.PLAIN, (int) size.getWidth() / 20));
		this.add(title, setDimensionObj(0, 0.6));
		this.add(generateButton("Play", "PLAY"), setDimensionObj(1, 0.2));
		this.add(generateButton("Leaderboard", "LEADERBOARD"), setDimensionObj(3, 0.3));
		this.add(generateButton("Shop", "SHOP"), setDimensionObj(2, 0.3));
		this.add(generateButton("Quit", "QUIT"), setDimensionQuit(0, 6, 0.3));
		this.add(generateButton("Tutorial", "TUTORIAL"), setDimensionObj(5, 0.3));
		this.add(generateButton("Clear", "CLEAR"), setDimensionObj(4, 0.3));
		this.setVisible(true);
	}
	
	private JButton generateButton(String name, String actionListenerName) {
		JButton jb = new JButton(name);
		jb.setFont(new Font("Arial", Font.PLAIN, (int) size.getWidth() / 60));
		if(name == "Play") {
			jb.setPreferredSize(new Dimension((int)size.getWidth() * 15/100, (int) size.getHeight() * 10/ 100));
			jb.setBackground(Color.decode("#77DD77"));
			jb.setBorder(BorderFactory.createLineBorder(Color.decode("#007542"), 4, true));
		}else {
			jb.setPreferredSize(new Dimension((int)size.getWidth() * 11/100, (int) size.getHeight() * 5/ 100));
			jb.setBackground(Color.decode("#FFDD62"));
			jb.setBorder(BorderFactory.createLineBorder(Color.decode("#FF971A"), 4, true));
		}
		jb.addActionListener(adHocActionListener(actionListenerName));
		return jb;
	}
	
	private GridBagConstraints setDimensionObj(int gridy, double weighty) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = gridy;
		gbc.weighty = weighty;
		return gbc;
	}
	
	private GridBagConstraints setDimensionQuit(int gridx, int gridy, double weighty) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = gridy;
		gbc.weighty = weighty;
		gbc.gridx=gridx;
		return gbc;
	}
	
	private ActionListener adHocActionListener(String panelName) {

		if (panelName == "PLAY") {
			return e -> {
				condition = Stages.GAME;
				MainMenu.cardLayout.show(this, panelName);
			};
		}else if(panelName == "QUIT"){
			return e -> {
				System.exit(0);
			};
		}
		else {
			return e -> {
				MainMenu.cardLayout.show(this, panelName);
			};
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D canvas = (Graphics2D) g;
		canvas.drawImage(scale(background), 0, 0, (int) size.getWidth(), (int) size.getHeight(), null);
	}
	
	private Image scale(Image image) {
		return image.getScaledInstance((int) size.getWidth(), (int) size.getHeight(), Image.SCALE_DEFAULT);
	}
}
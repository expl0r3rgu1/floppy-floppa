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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import test.Stages;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.CardLayout;

public class MainMenu extends JPanel implements Menu {

	//private static Stages condition = Stages.MENU;
	MenuPanel menu = new MenuPanel();

	public MainMenu() {

		this.setLayout(new CardLayout());
		
		this.add("MENU", menu);
		// this.add("PLAY", play);
		// this.add("LEADERBOARD", leaderboard);
		// this.add("CLEAR", clear);
		// this.add("SHOP", shop);
		// this.add("QUIT", quit);
		// this.add("TUTORIAL", tutorial);

	}

}
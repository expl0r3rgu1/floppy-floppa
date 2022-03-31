package main.menu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.utilities.Stages;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.CardLayout;

public class MainMenu extends JPanel {

	private static Stages condition = Stages.MENU;
	private static final long serialVersionUID = -7631305128085484196L;
	public static final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	JPanel menu = new JPanel();
	private Image background;

	public MainMenu() {

		try { 
			background = ImageIO.read(getClass().getResource("/resources/images/shopBackground.jpg"));
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
		
		menu.setPreferredSize(size);
		this.setLayout(new CardLayout());
		// this.add("MENU", menu);
		//this.add("PLAY", play);
		// this.add("LEADERBOARD", leaderboard);
		// this.add("CLEAR", clear);
		// this.add("SHOP", shop);
		// this.add("QUIT", quit);
		// this.add("TUTORIAL", tutorial);

		JButton play = new JButton("Play");
		play.setBounds(50, 100, 80, 30);
		play.setBackground(Color.white);
		play.addActionListener(adHocActionListener("PLAY"));
		
		JButton leaderboard = new JButton("Leaderboard");
		leaderboard.setBounds(50, 100, 80, 30);
		leaderboard.setBackground(Color.white);
		leaderboard.addActionListener(adHocActionListener("LEADERBOARD"));
		
		JButton clear = new JButton("Clear");
		clear.setBounds(50, 100, 80, 30);
		clear.setBackground(Color.white);
		clear.addActionListener(adHocActionListener("CLEAR"));
		
		JButton shop = new JButton("Shop");
		shop.setBounds(50, 100, 80, 30);
		shop.setBackground(Color.white);
		shop.addActionListener(adHocActionListener("SHOP"));
		
		JButton quit = new JButton("Quit");
		shop.setBounds(50, 100, 80, 30);
		shop.setBackground(Color.white);
		quit.addActionListener(adHocActionListener("QUIT"));
		
		JButton tutorials = new JButton("Tutorial");
		tutorials.setBounds(50, 100, 80, 30);
		tutorials.setBackground(Color.white);
		tutorials.addActionListener(adHocActionListener("TUTORIAL"));

	}

	public void render(Graphics a) {

		Graphics2D graph = (Graphics2D) a;

		Font font1 = new Font("arial", Font.BOLD, 50);
		a.setFont(font1);
		a.setColor(Color.black);
		a.drawString("Floppy Floppa", (int) (size.getWidth() / 2), 100);

		Font font2 = new Font("arial", Font.BOLD, 50);
		graph.setFont(font2);

	}
	
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g); 
	    g.drawImage(scale(background), 0, 0, menu);
	}

	private ActionListener adHocActionListener(String panelName) {
		
		if(panelName == "PLAY") {
			return e -> {
				condition = Stages.GAME;
				CardLayout cl = (CardLayout) (this.getLayout());
				cl.show(this, panelName);
			};
		}else {
			return e -> {
				CardLayout cl = (CardLayout) (this.getLayout());
				cl.show(this, panelName);
			};
		}
	}

	private Image scale(Image image) {
		return image.getScaledInstance((int) size.getWidth(), (int) size.getHeight(), Image.SCALE_DEFAULT);
	}

}
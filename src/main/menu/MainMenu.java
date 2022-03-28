package main.menu;

import java.awt.Graphics;
import java.awt.Graphics2D;
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
import java.awt.Rectangle;

public class MainMenu implements ActionListener{
	
	private static Stages condition=Stages.MENU;
	
	private static final long serialVersionUID = -7631305128085484196L;
	public static final Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
	//private Image background;
	
	public MainMenu() {
		
		JPanel menu = new JPanel();
		menu.setBounds(40,80,200,200);    
		menu.setBackground(background); 
		/*try {
			background = ImageIO.read(getClass().getResource("/resources/images/shopBackground.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		JButton play = new JButton("Play");
		play.setBounds(50,100,80,30);    
		play.setBackground(Color.white);  
		play.addActionListener(this);
		JButton leaderboard = new JButton("Leaderboard");
		leaderboard.setBounds(50,100,80,30);    
		leaderboard.setBackground(Color.white);  
		leaderboard.addActionListener(this); 
		JButton clear = new JButton("Clear");
		clear.setBounds(50,100,80,30);    
		clear.setBackground(Color.white);  
		clear.addActionListener(this);
		JButton shop = new JButton("Shop");
		shop.setBounds(50,100,80,30);    
		shop.setBackground(Color.white);  
		shop.addActionListener(this);
		JButton quit = new JButton("Quit");
		shop.setBounds(50,100,80,30);    
		shop.setBackground(Color.white);  
		quit.addActionListener(this);
		JButton tutorials = new JButton("Tutorial");
		tutorials.setBounds(50,100,80,30);    
		tutorials.setBackground(Color.white);  
		tutorials.addActionListener(this);
		
	}
	
	public void render(Graphics a) {
		
		Graphics2D graph = (Graphics2D) g;
		
		Font font1 = new Font("arial", Font.BOLD, 50);
		a.setFont(font1);
		a.setColor(Color.black);
		a.drawString("Floppy Floppa", (int) (size.getWidth()/2) , 100);
		
		Font font2 = new Font("arial", Font.BOLD, 50);
		graph.setFont(font2);
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
	    if (obj == button1) {
	      index--;
	    } else if (obj == button2) {
	      index++;
	    }
	    if (index > 2)
	      index = 0;
	    else if (index < 0) {
	      index = 2;
	    }
		
	}
	
	private Image scale(Image image) {
		return image.getScaledInstance((int) size.getWidth(), (int) size.getHeight(), Image.SCALE_DEFAULT);
	}
	
}

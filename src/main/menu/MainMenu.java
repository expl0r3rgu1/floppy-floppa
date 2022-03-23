package main.menu;

import java.awt.Graphics2D;

import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Rectangle;

public class MainMenu {
	
	private enum STATES{
			MENU, GAME
	};
	
	private STATES condition=STATES.MENU;
	public static final Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
	//JPanel buttons;
	private Rectangle play = new Rectangle((int)(size.width/2 + 100),150,100,50);
	private Rectangle leaderboard = new Rectangle((int)(size.width/2 + 100),150,100,50);
	private Rectangle clear = new Rectangle((int)(size.width/2 + 100),150,100,50);
	private Rectangle shop = new Rectangle((int)(size.width/2 + 100),150,100,50);
	private Rectangle quit = new Rectangle((int)(size.width/2 + 100),150,100,50);
	private Rectangle tutorials = new Rectangle((int)(size.width/2 + 100),150,100,50);
	
	public void render(Graphics2D a) {
		
		Font font = new Font("arial", Font.BOLD, 50);
		a.setFont(font);
		a.setColor(Color.white);
		a.drawString("Floppy Floppa", (int) (size.getWidth()/2) , 100);
		
		a.draw(buttons);
	}
}

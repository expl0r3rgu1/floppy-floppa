package main.game_engine;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import main.menu.MainMenu;
import main.utilities.GameSettings;

public class MainFrame extends JFrame {
	private GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	private final MainMenu mainMenu;
	private final GameSettings gameSettings;

	public MainFrame() {
		super("Floppy-Floppa");
		
		this.gameSettings = new GameSettings();
		this.mainMenu = new MainMenu(this.gameSettings);
		
		this.add(mainMenu);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		device.setFullScreenWindow(this);
		this.setVisible(true);
	}

}

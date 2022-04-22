package main.game_engine;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import main.menu.MainMenu;
import main.utilities.GameSettings;

/**
 * The JFrame that contains the whole game in fullscreen
 */
public class MainFrame extends JFrame {
	private static final long serialVersionUID = -5008443485850327687L;
	private GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private final MainMenu mainMenu;

	public MainFrame() {
		super("Floppy-Floppa");

		this.mainMenu = new MainMenu();

		this.add(mainMenu);

		if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
			playSoundtrack();
		}

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		device.setFullScreenWindow(this);
		this.setVisible(true);
	}

	/**
	 * Generates a new Thread that plays the soundtrack of the game
	 */
	public static synchronized void playSoundtrack() {
		File audioFile = new File(MainFrame.class.getResource("/resources/sounds/soundtrack.wav").getFile());

		new Thread(new Runnable() {

			@Override
			public void run() {
				Clip clip = null;
				try {
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
					clip = AudioSystem.getClip();
					clip.open(audioStream);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}

				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}).start();
	}
}

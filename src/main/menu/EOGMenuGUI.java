package main.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.GBCSimplified;
import main.utilities.GraphicJButton;
import main.utilities.GraphicJLabel;

/**
 * The End Of Game class implements the panel shown at the end of a game
 */

public class EOGMenuGUI extends JPanel {

	private static final long serialVersionUID = -6834009314888626973L;
	private Image background;
	private MainMenu mainMenu;
	private EOGMenu eogMenu;
	private int meters;
	private int malusTimes;
	private int boosterTImes;

	/**
	 * 
	 * @param mainMenu        Used to show card layouts
	 * @param metersTravelled The meters that the Character traveled during the game
	 * @param reducerTimes    How many times the character hit the CoinsReducer
	 *                        malus
	 * @param incrementTimes  How many times the character hit the CoinsIncrement
	 *                        booster
	 */
	public EOGMenuGUI(MainMenu mainMenu, int metersTravelled, int reducerTimes, int incrementTimes) {
		this.eogMenu = new EOGMenu(metersTravelled, reducerTimes, incrementTimes);
		this.mainMenu = mainMenu;
		this.meters = metersTravelled;
		this.malusTimes = reducerTimes;
		this.boosterTImes = incrementTimes;

		this.setLayout(new GridBagLayout());

		background = CommonMethods.getImageResource("Background");

		this.placeGUIComponents();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D canvas = (Graphics2D) g;

		canvas.drawImage(background, 0, 0, (int) Constants.SCREEN_SIZE.getWidth(),
				(int) Constants.SCREEN_SIZE.getHeight(), null);
	}

	/**
	 * Main class method, it correctly creates and placed the various GUI components
	 */
	private void placeGUIComponents() {
		for (int i = 0; i < 3; i++) {
			if (i == 0) {
				GraphicJLabel EOGtitle = new GraphicJLabel("G A M E    O V E R", Color.decode("#FF675F"),
						Color.decode("#FF392E"), "pixel.TTF", 50f);
				this.add(EOGtitle,
						new GBCSimplified(1, i, CommonMethods.getPixelsFromPercentage(10),
								CommonMethods.getPixelsFromPercentage(5),
								new Insets(CommonMethods.getPixelsFromPercentage(2), 0, 0, 0)));

			} else if (i == 1) {
				GraphicJLabel coins = new GraphicJLabel(
						"<html><center> Old coins: &emsp &emsp New coins: </center> <br> <center> "
								+ eogMenu.getPreviousCoins() + "&emsp &emsp &emsp "
								+ eogMenu.updateCoins(meters, malusTimes, boosterTImes) + " 1348 </center> </html>",
						Color.decode("#77DD77"), Color.decode("#007542"), "fipps.otf", 25f);
				this.add(coins, new GBCSimplified(1, i, CommonMethods.getPixelsFromPercentage(25),
						CommonMethods.getPixelsFromPercentage(15), new Insets(CommonMethods.getPixelsFromPercentage(4),
								0, CommonMethods.getPixelsFromPercentage(4), 0)));

				GraphicJButton menuButton = new GraphicJButton("MENU", Color.decode("#FFDD62"), Color.decode("#FF971A"),
						"Arial", Font.BOLD);
				this.add(menuButton,
						new GBCSimplified(2, i, CommonMethods.getPixelsFromPercentage(3),
								CommonMethods.getPixelsFromPercentage(2),
								new Insets(0, CommonMethods.getPixelsFromPercentage(5), 0, 0)));
				menuButton.addActionListener(e -> {
					mainMenu.showCard(Constants.PANEL.MENU);
				});

				GraphicJButton shopButton = new GraphicJButton("SHOP", Color.decode("#FFDD62"), Color.decode("#FF971A"),
						"Arial", Font.BOLD);
				this.add(shopButton,
						new GBCSimplified(0, i, CommonMethods.getPixelsFromPercentage(3),
								CommonMethods.getPixelsFromPercentage(2),
								new Insets(0, 0, 0, CommonMethods.getPixelsFromPercentage(5))));
				shopButton.addActionListener(e -> {
					mainMenu.showCard(Constants.PANEL.SHOP);
				});

			} else if (i == 2) {
				GraphicJButton leaderboardButton = new GraphicJButton("LEADERBOARD", Color.decode("#FFDD62"),
						Color.decode("#FF971A"), "Arial", Font.BOLD);
				this.add(leaderboardButton, new GBCSimplified(1, i, CommonMethods.getPixelsFromPercentage(3),
						CommonMethods.getPixelsFromPercentage(2), new Insets(CommonMethods.getPixelsFromPercentage(2),
								0, CommonMethods.getPixelsFromPercentage(2), 0)));
				leaderboardButton.addActionListener(e -> {
					mainMenu.showCard(Constants.PANEL.LEADERBOARD);
				});
			}
		}
	}
}

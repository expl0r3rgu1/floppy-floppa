package main.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

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

	/**
	 * 
	 * @param mainMenu        Used to show card layouts
	 * @param metersTravelled The meters that the Character traveled during the game
	 * 
	 */
	public EOGMenuGUI(MainMenu mainMenu, int metersTravelled) {
		this.eogMenu = new EOGMenu(mainMenu.getShop(), metersTravelled);
		this.mainMenu = mainMenu;

		this.setLayout(new GridBagLayout());

		background = CommonMethods.getImageResource("Background");

		this.placeGUIComponents();

	}

	/**
	 * {@inheritDoc}
	 */
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
						Color.decode("#FF392E"), "pixel", 50f);
				this.add(EOGtitle,
						new GBCSimplified(1, i, CommonMethods.getPixelsFromPercentageWidth(10),
								CommonMethods.getPixelsFromPercentageHeight(5),
								new Insets(CommonMethods.getPixelsFromPercentageHeight(2), 0, 0, 0)));

			} else if (i == 1) {
				GraphicJLabel coins = new GraphicJLabel(
						"<html><center> Old coins: &emsp &emsp New coins: </center> <br> <center> "
								+ eogMenu.getPreviousCoins() + "&emsp &emsp &emsp " + eogMenu.getNewCoins()
								+ "</center> </html>",
						Color.decode("#77DD77"), Color.decode("#007542"), "fipps", 25f);
				this.add(coins,
						new GBCSimplified(1, i, CommonMethods.getPixelsFromPercentageWidth(25),
								CommonMethods.getPixelsFromPercentageHeight(15),
								new Insets(CommonMethods.getPixelsFromPercentageHeight(4), 0,
										CommonMethods.getPixelsFromPercentageWidth(4), 0)));

				GraphicJButton menuButton = new GraphicJButton("MENU", Color.decode("#FFDD62"), Color.decode("#FF971A"),
						"Arial", Font.BOLD);
				this.add(menuButton,
						new GBCSimplified(2, i, CommonMethods.getPixelsFromPercentageWidth(3),
								CommonMethods.getPixelsFromPercentageHeight(2),
								new Insets(0, CommonMethods.getPixelsFromPercentageWidth(5), 0, 0)));
				menuButton.addActionListener(e -> {
					mainMenu.showCard(Constants.PANEL.MENU);
				});

				GraphicJButton shopButton = new GraphicJButton("SHOP", Color.decode("#FFDD62"), Color.decode("#FF971A"),
						"Arial", Font.BOLD);
				this.add(shopButton,
						new GBCSimplified(0, i, CommonMethods.getPixelsFromPercentageWidth(3),
								CommonMethods.getPixelsFromPercentageHeight(2),
								new Insets(0, 0, 0, CommonMethods.getPixelsFromPercentageWidth(5))));
				shopButton.addActionListener(e -> {
					mainMenu.showCard(Constants.PANEL.SHOP);
				});

			} else if (i == 2) {
				GraphicJButton leaderboardButton = new GraphicJButton("LEADERBOARD", Color.decode("#FFDD62"),
						Color.decode("#FF971A"), "Arial", Font.BOLD);
				this.add(leaderboardButton,
						new GBCSimplified(1, i, CommonMethods.getPixelsFromPercentageWidth(3),
								CommonMethods.getPixelsFromPercentageHeight(2),
								new Insets(CommonMethods.getPixelsFromPercentageHeight(2), 0,
										CommonMethods.getPixelsFromPercentageHeight(2), 0)));
				leaderboardButton.addActionListener(e -> {
					mainMenu.showCard(Constants.PANEL.LEADERBOARD);
				});
			}
		}
	}
}

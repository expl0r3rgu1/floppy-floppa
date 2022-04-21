package main.menu;

import javax.swing.JPanel;

import main.game_engine.PlayPanel;
import main.menu.leaderboard.Leaderboard;
import main.menu.leaderboard.LeaderboardPanel;
import main.menu.shop.Shop;
import main.menu.shop.ShopGUI;
import main.menu.tutorial.TutorialPanel;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Constants.PANEL;
import main.utilities.GameSettings;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * MainMenu is the container for CardLayout
 */
public class MainMenu extends JPanel implements Menu {

	public static final CardLayout cardLayout = new CardLayout();
	private final MenuPanel menuPanel;
	private final Leaderboard leaderboard;
	private LeaderboardPanel leaderboardPanel;
	private PlayPanel playPanel;
	private final Shop shop;
	private ShopGUI shopGUI;
	private final GameSettings gameSettings;
	private final TutorialPanel tutorial;
	private final SelectionPanel selectionPanel;

	/**
	 * @param gameSettings - GameSettings
	 */
	public MainMenu(GameSettings gameSettings) {

		File savingsFile = new File(Constants.SAVINGS_FILE_PATH);
		if (!savingsFile.exists()) {
			// showCard(PANEL.TUTORIAL);
			try {
				savingsFile.createNewFile();
				FileWriter savingsFileWriter = new FileWriter(savingsFile);
				savingsFileWriter.write(Constants.SAVINGS_FILE_START_CONTENT);
				savingsFileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		gameSettings = new GameSettings();
		menuPanel = new MenuPanel(this);
		leaderboard = new Leaderboard();
		leaderboardPanel = new LeaderboardPanel(this, leaderboard);
		playPanel = new PlayPanel(this, gameSettings);
		shop = new Shop();
		shopGUI = new ShopGUI(this, shop);
		tutorial = new TutorialPanel(this);
		
		this.setLayout(cardLayout);

		this.add("MENU", menuPanel);
		this.add(Constants.PANEL.PLAY.name(), playPanel);
		this.add(Constants.PANEL.LEADERBOARD.name(), leaderboardPanel);
		this.add(Constants.PANEL.SHOP.name(), shopGUI);
		this.add(Constants.PANEL.TUTORIAL.name(), tutorial);
		this.add(Constants.PANEL.SELECT.name(), selectionPanel);

	}

	/**
	 * paintComponent is a method to put the background in the JPanel
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D canvas = (Graphics2D) g;
		canvas.drawImage(CommonMethods.getImageResource("Background"), 0, 0, (int) Constants.SCREEN_SIZE.getWidth(),
				(int) Constants.SCREEN_SIZE.getHeight(), null);
	}

	/**
	 * showCard is a method that when called shows the JPanel panel
	 * 
	 * @param panel - is the name of the JPanel that needs to be shown
	 */
	public void showCard(Constants.PANEL panel) {
		if (panel.equals(PANEL.SHOP)) {
			shopGUI = new ShopGUI(this, shop);
			this.add(PANEL.SHOP.name(), shopGUI);
		} else if (panel.equals(PANEL.LEADERBOARD)) {
			leaderboardPanel = new LeaderboardPanel(this, leaderboard);
			this.add(PANEL.LEADERBOARD.name(), leaderboardPanel);
		} else if (panel.equals(PANEL.PLAY)) {
			playPanel = new PlayPanel(this, gameSettings);
			this.add(PANEL.PLAY.name(), playPanel);
		}

		cardLayout.show(this, panel.name());
	}

	/**
	 * updateSavings is a method that writes on a file the data of the players
	 */
	public void updateSavings() {
		try {
			shopGUI.getShop().fileUpdate();
			leaderboardPanel.getLeaderboard().writeToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * clearSavings is a method that deletes all the data from the savingsFile
	 */
	public void clearSavings() {
		File savingsFile = new File(Constants.SAVINGS_FILE_PATH);
		try {
			FileWriter savingsFileWriter = new FileWriter(savingsFile);
			savingsFileWriter.write(Constants.SAVINGS_FILE_START_CONTENT);
			savingsFileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		leaderboard.clearLeaderboard();
		shop.clearSavings();
	}
	
	public Shop getShop() {
		return this.shop;
	}
	
	public Leaderboard getLeaderboard() {
		return this.leaderboard;
	}

}
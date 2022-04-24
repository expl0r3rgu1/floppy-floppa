package main.controller.menu;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JPanel;

import main.controller.menu.leaderboard.Leaderboard;
import main.controller.menu.shop.Shop;
import main.model.utilities.CommonMethods;
import main.model.utilities.Constants;
import main.model.utilities.Constants.PANEL;
import main.model.utilities.GameSettings;
import main.view.game_engine.PlayPanel;
import main.view.leaderboard.LeaderboardPanel;
import main.view.menu.MenuPanel;
import main.view.menu.SelectionPanel;
import main.view.shop.ShopGUI;
import main.view.tutorial.TutorialPanel;

/**
 * MainMenu is the container for CardLayout
 */
public class MainMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2807311016014427749L;
	private final CardLayout cardLayout;
	private final MenuPanel menuPanel;
	private final Leaderboard leaderboard;
	private LeaderboardPanel leaderboardPanel;
	private PlayPanel playPanel;
	private final Shop shop;
	private ShopGUI shopGUI;
	private final GameSettings gameSettings;
	private final TutorialPanel tutorialPanel;
	private SelectionPanel selectionPanel;

	/**
	 * @param gameSettings - GameSettings
	 */
	public MainMenu() {

		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		menuPanel = new MenuPanel(this);
		tutorialPanel = new TutorialPanel(this);

		this.add(PANEL.MENU.name(), menuPanel);
		this.add(PANEL.TUTORIAL.name(), tutorialPanel);

		File savingsFile = new File(Constants.SAVINGS_FILE_PATH);
		if (!savingsFile.exists()) {
			showCard(PANEL.TUTORIAL);
			try {
				savingsFile.createNewFile();
				FileWriter savingsFileWriter = new FileWriter(savingsFile);
				savingsFileWriter.write(Constants.SAVINGS_FILE_START_CONTENT);
				savingsFileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		this.gameSettings = new GameSettings();
		leaderboard = new Leaderboard();
		leaderboardPanel = new LeaderboardPanel(this, leaderboard);
		playPanel = new PlayPanel(this, gameSettings);
		shop = new Shop();
		shopGUI = new ShopGUI(this, shop);
		selectionPanel = new SelectionPanel(this, gameSettings);

		this.add(PANEL.PLAY.name(), playPanel);
		this.add(PANEL.LEADERBOARD.name(), leaderboardPanel);
		this.add(PANEL.SHOP.name(), shopGUI);
		this.add(PANEL.SELECT.name(), selectionPanel);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paintComponent(Graphics g) {
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
		} else if (panel.equals(PANEL.SELECT)) {
			selectionPanel = new SelectionPanel(this, gameSettings);
			this.add(PANEL.SELECT.name(), selectionPanel);
		}

		cardLayout.show(this, panel.name());
	}

	/**
	 * updateSavings is a method that writes on a file the data of the players
	 */
	public void updateSavings() {
		try {
			this.shop.fileUpdate();
			this.leaderboard.writeToFile();
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
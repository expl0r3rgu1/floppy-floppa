package main.menu;

import javax.swing.JPanel;

import main.utilities.Constants;

import java.awt.CardLayout;

public class MainMenu extends JPanel implements Menu {

	public static final CardLayout cardLayout = new CardLayout();
	private final MenuPanel menuPanel;
	private final LeaderboardPanel leaderboardPanel;
	private final PlayPanel playPanel;
	private final ShopGUI shopGUI;
	private final EOGMenuGUI EOGMenuGUI;
	private final Tutorial tutorial;
	private final selectionPanel selectionPanel;

	public MainMenu() {

		menuPanel = new MenuPanel(this);
		leaderboardPanel = new LeaderboardPanel(this);
		playPanel = new PlayPanel(gameSettings);
		shopGUI = new ShopGUI(this);
		EOGMenuGUI = new EOGMenuGUI(this);
		tutorial = new Tutorial(this);
		
		File savings = new File(Constants.SAVINGS_FILE_PATH);
		if(!savingsFile.exists()) {
			showCard(PANEL.TUTORIAL);
			try {
				savingsFile.createNewFile();
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			FileWriter savingsFileWriter = new FileWriter(savingsFile);
			savingsFileWriter.write(Constants.SAVINGS_FILE_START_CONTENT);
			savingsFileWriter.close();
		}
		
		this.setLayout(cardLayout);
		
		this.add("MENU", menuPanel);
		this.add(Constants.PANEL.PLAY.name(), playPanel);
		this.add(Constants.PANEL.LEADERBOARD.name(), leaderboardPanel);
		this.add(Constants.PANEL.EOGMENU.name(), EOGMenuGUI);
	    this.add(Constants.PANEL.SHOP.name(), shopGUI);
		this.add(Constants.PANEL.TUTORIAL.name(), tutorial);
		this.add(Constants.PANEL.SELECT.name(), selectionPanel);

	}
	
	public void showCard(Constants.PANEL panel) {
		cardLayout.show(this, panel.name());
	}

}
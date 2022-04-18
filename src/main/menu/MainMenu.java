package main.menu;

import javax.swing.JPanel;

import main.utilities.Constants;

import java.awt.CardLayout;

public class MainMenu extends JPanel implements Menu {

	public static final CardLayout cardLayout = new CardLayout();

	public MainMenu() {

		MenuPanel menu = new MenuPanel(this);
		
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
		
		this.add("MENU", menu);
		this.add("PLAY", Constants.PANEL.PLAY);
		this.add("LEADERBOARD", Constants.PANEL.LEADERBOARD);
		this.add("EOGMENU", Constants.PANEL.EOGMENU);
	    this.add("SHOP", Constants.PANEL.SHOP);
		this.add("TUTORIAL", Constants.PANEL.TUTORIAL);
		this.add("SELECT", Constants.PANEL.SELECT);

	}
	
	public void showCard(Constants.PANEL panel) {
		cardLayout.show(this, panel.name());
	}

}
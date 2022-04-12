package main.menu;

import javax.swing.JPanel;
import java.awt.CardLayout;

public class MainMenu extends JPanel implements Menu {

	public static final CardLayout cardLayout = new CardLayout();

	public MainMenu() {

		MenuPanel menu = new MenuPanel(this);
		this.setLayout(cardLayout);
		
		this.add("MENU", menu);
		// this.add("PLAY", play);
		// this.add("LEADERBOARD", leaderboard);
		// this.add("CLEAR", clear);
		// this.add("SHOP", shop);
		// this.add("QUIT", quit);
		// this.add("TUTORIAL", tutorial);

	}
	
	public void showCard(String cardName) {
		CardLayout cl = (CardLayout) (this.getLayout());
		cl.show(this, cardName);
	}

}
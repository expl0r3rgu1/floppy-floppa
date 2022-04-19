package main.menu.tutorial;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import main.menu.MainMenu;
import main.utilities.Constants;

public class TutorialPanel extends JPanel{
	private static final long serialVersionUID = 3621436339823195193L;
	private MainMenu mainMenu;
	
	public TutorialPanel(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
		this.setPreferredSize(Constants.SCREEN_SIZE);
		this.setOpaque(false);
		this.setLayout(new GridBagLayout());
	}
	
}

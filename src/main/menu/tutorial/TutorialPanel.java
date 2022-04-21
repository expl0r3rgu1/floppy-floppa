package main.menu.tutorial;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.menu.MainMenu;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.GBCSimplified;
import main.utilities.GraphicJButton;

public class TutorialPanel extends JPanel {
	private static final long serialVersionUID = 3621436339823195193L;
	private MainMenu mainMenu;

	public TutorialPanel(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
		this.setPreferredSize(Constants.SCREEN_SIZE);
		this.setOpaque(false);
		this.setLayout(new GridBagLayout());
		
		this.addTitleLabel();
		this.addTutorialLabel();
		this.addMenuButton();
	}

	private void addTitleLabel() {
		JLabel titleLabel = new JLabel("TUTORIAL");
		titleLabel.setFont(CommonMethods.getFontResource("pixel.TTF").deriveFont(50f));

		this.add(titleLabel, new GBCSimplified(0, 0, 0, CommonMethods.getPixelsFromPercentageHeight(1)));
	}

	private void addTutorialLabel() {
		ImageIcon tutorialIcon = CommonMethods.getGifResource("tutorial");
		tutorialIcon.setImage(tutorialIcon.getImage().getScaledInstance(
				(int) Constants.SCREEN_SIZE.getWidth() - CommonMethods.getPixelsFromPercentageWidth(15),
				(int) Constants.SCREEN_SIZE.getHeight() - CommonMethods.getPixelsFromPercentageHeight(20),
				Image.SCALE_DEFAULT));
		JLabel tutorialLabel = new JLabel(tutorialIcon);

		this.add(tutorialLabel, new GBCSimplified(0, 1, 0, 0));
	}

	private void addMenuButton() {
		GraphicJButton menuButton = new GraphicJButton("MENU", Color.decode("#FFDD62"), Color.decode("#FF971A"),
				"Arial", Font.BOLD);

		menuButton.addActionListener(e -> {
			mainMenu.showCard(Constants.PANEL.MENU);
		});

		this.add(menuButton, new GBCSimplified(0, 2, CommonMethods.getPixelsFromPercentageWidth(25),
				CommonMethods.getPixelsFromPercentageHeight(1)));
	}
}

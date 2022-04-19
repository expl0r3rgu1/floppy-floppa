package main.menu.tutorial;

import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.menu.MainMenu;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.GBCSimplified;

public class TutorialPanel extends JPanel {
	private static final long serialVersionUID = 3621436339823195193L;
	private MainMenu mainMenu;

	public TutorialPanel(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
		this.setPreferredSize(Constants.SCREEN_SIZE);
		this.setOpaque(false);
		this.setLayout(new GridBagLayout());
	}

	private void addTitleLabel() {
		JLabel titleLabel = new JLabel("TUTORIAL");
		titleLabel.setFont(CommonMethods.getFontResource("pixel.TTF").deriveFont(50f));

		this.add(titleLabel, new GBCSimplified(0, 0, 0, CommonMethods.getPixelsFromPercentage(1)));
	}

	private void addTutorialLabel() {
		ImageIcon tutorialIcon = CommonMethods.getGifResource("tutorial");
		tutorialIcon.setImage(tutorialIcon.getImage().getScaledInstance(
				(int) Constants.SCREEN_SIZE.getWidth() - CommonMethods.getPixelsFromPercentage(10),
				(int) Constants.SCREEN_SIZE.getHeight() - CommonMethods.getPixelsFromPercentage(10),
				Image.SCALE_DEFAULT));
		JLabel tutorialLabel = new JLabel(tutorialIcon);
		
		this.add(tutorialLabel, new GBCSimplified(0, 1, 0, 0));
	}
}

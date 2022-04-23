package main.menu.tutorial;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.menu.MainMenu;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Constants.PANEL;
import main.utilities.GBCSimplified;
import main.utilities.GraphicJButton;

/**
 * A class that shows the initial tutorial, this class extends the java class
 * JPanel
 */
public class TutorialPanel extends JPanel {
	private static final long serialVersionUID = 3621436339823195193L;
	private final MainMenu mainMenu;

	/**
	 * @param mainMenu it is necessary to use correctly the card layout
	 */
	public TutorialPanel(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
		this.setPreferredSize(Constants.SCREEN_SIZE);
		this.setOpaque(false);
		this.setLayout(new GridBagLayout());

		this.addTitleLabel();
		this.addTutorialLabel();
		this.addMenuButton();
	}

	/**
	 * Creates and adds the label that contains the title of this panel
	 */
	private void addTitleLabel() {
		JLabel titleLabel = new JLabel(PANEL.TUTORIAL.name());
		titleLabel.setFont(CommonMethods.getFontResource("pixel").deriveFont(50f));

		this.add(titleLabel, new GBCSimplified(0, 0, 0, CommonMethods.getPixelsFromPercentageHeight(1)));
	}

	/**
	 * Creates and adds the label that contains the initial tutorial
	 */
	private void addTutorialLabel() {
		ImageIcon tutorialIcon = CommonMethods.getGifResource("tutorial");
		tutorialIcon.setImage(tutorialIcon.getImage().getScaledInstance(
				(int) Constants.SCREEN_SIZE.getWidth() - CommonMethods.getPixelsFromPercentageWidth(15),
				(int) Constants.SCREEN_SIZE.getHeight() - CommonMethods.getPixelsFromPercentageHeight(20),
				Image.SCALE_DEFAULT));
		JLabel tutorialLabel = new JLabel(tutorialIcon);

		this.add(tutorialLabel, new GBCSimplified(0, 1, 0, 0));
	}

	/**
	 * Creates and adds the button to go back to the main menu
	 */
	private void addMenuButton() {
		GraphicJButton menuButton = new GraphicJButton(PANEL.MENU.name(), Color.decode("#FFDD62"),
				Color.decode("#FF971A"), "Arial", Font.BOLD);

		menuButton.addActionListener(e -> {
			mainMenu.showCard(PANEL.MENU);
		});

		this.add(menuButton,
				new GBCSimplified(0, 2, CommonMethods.getPixelsFromPercentageWidth(25),
						CommonMethods.getPixelsFromPercentageHeight(1),
						new Insets(CommonMethods.getPixelsFromPercentageHeight(1), 0, 0, 0)));
	}
}

package main.menu.leaderboard;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

import main.menu.MainMenu;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Constants.PANEL;
import main.utilities.GBCSimplified;
import main.utilities.GraphicJButton;

/**
 * The JPanel encharged to show the Leaderboard
 */
public class LeaderboardPanel extends JPanel {
	private static final long serialVersionUID = -2850654943551437120L;
	private final Leaderboard leaderboard;
	private final MainMenu mainMenu;

	/**
	 * Initializes the view of the JPanel
	 * 
	 * @param mainMenu    The instance of the MainMenu used to call
	 *                    MainMenu.showCard(PANEL panel)
	 * @param leaderboard The Leaderboard to get contents from
	 */
	public LeaderboardPanel(MainMenu mainMenu, Leaderboard leaderboard) {
		this.setPreferredSize(Constants.SCREEN_SIZE);
		this.setLayout(new GridBagLayout());
		this.setOpaque(false);

		this.mainMenu = mainMenu;
		this.leaderboard = leaderboard;

		// TITLE
		addTitle();

		// SCROLLABLE PANE
		addScrollablePane();

		// MENU BUTTON
		addMenuButton();
	}

	// GRAPHIC COMPONENTS

	private void addTitle() {
		JLabel title = new JLabel(PANEL.LEADERBOARD.name(), SwingConstants.CENTER);
		title.setFont(CommonMethods.getFontResource("pixel").deriveFont(50f));

		this.add(title, new GBCSimplified(0, 0, 0, 0,
				new Insets(0, 0, CommonMethods.getPixelsFromPercentageHeight(2), 0), GridBagConstraints.PAGE_START));
	}

	private JPanel getScorePanel() {
		JPanel scorePanel = new JPanel(new GridLayout(leaderboard.getLeaderboard().size(), 1));
		scorePanel.setOpaque(false);

		int placeNumber = 1;
		for (var player : leaderboard.getLeaderboard()) {
			JLabel score = new JLabel(
					placeNumber + " - " + player.getNickname() + " - " + player.getPersonalBest() + " m",
					SwingConstants.CENTER);
			score.setFont(CommonMethods.getFontResource("fipps").deriveFont(20f));
			score.setForeground(Color.lightGray);
			scorePanel.add(score);

			placeNumber++;
		}

		return scorePanel;
	}

	private void addScrollablePane() {
		JScrollPane scrollablePane = new JScrollPane(getScorePanel());
		scrollablePane.setOpaque(false);
		scrollablePane.getViewport().setBackground(new Color(0, 0, 0, .5f));
		scrollablePane.getViewport().setOpaque(true);
		scrollablePane.setBorder(BorderFactory.createEmptyBorder());
		scrollablePane.getVerticalScrollBar().setUnitIncrement(15);
		// SCROLLBAR CUSTOMIZATION
		scrollablePane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				super.configureScrollBarColors();

				this.thumbColor = Color.darkGray;
				this.trackColor = Color.gray;
			}
		});

		this.add(scrollablePane, new GBCSimplified(0, 1, CommonMethods.getPixelsFromPercentageWidth(70),
				CommonMethods.getPixelsFromPercentageHeight(80), new Insets(0, 0, 0, 0), GridBagConstraints.CENTER));
	}

	private void addMenuButton() {
		GraphicJButton menuButton = new GraphicJButton(PANEL.MENU.name(), Color.decode("#FFDD62"),
				Color.decode("#FF971A"),
				"Arial", Font.PLAIN);

		menuButton.addActionListener(e -> {
			mainMenu.showCard(Constants.PANEL.MENU);
		});

		this.add(menuButton,
				new GBCSimplified(0, 2, 0, CommonMethods.getPixelsFromPercentageHeight(2),
						new Insets(CommonMethods.getPixelsFromPercentageHeight(2),
								CommonMethods.getPixelsFromPercentageWidth(30), 0,
								CommonMethods.getPixelsFromPercentageWidth(30)),
						GridBagConstraints.PAGE_END));
	}
}

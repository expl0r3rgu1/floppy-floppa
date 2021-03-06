package main.view.game_engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import main.model.leaderboard.Player;
import main.model.utilities.CommonMethods;
import main.model.utilities.Constants;
import main.model.utilities.GBCSimplified;
import main.model.utilities.GameSettings;
import main.model.utilities.GraphicJButton;
import main.model.utilities.GraphicJLabel;

/**
 * JPanel that is prompted when starting a game. It lets the user choose their
 * nickname that will then be displayed in LeaderboardPanel
 */
public class NicknamePanel extends JPanel {
	private static final long serialVersionUID = -609955722187373250L;

	/**
	 * 
	 * @param playPanel    the only existing instance of PlayPanel used to call
	 *                     PlayPanel.dismissNicknamePanel()
	 * @param gameSettings the only existing instance of GameSettings used to call
	 *                     GameSettings.setPlayer(Player player)
	 */
	public NicknamePanel(PlayPanel playPanel, GameSettings gameSettings) {
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(CommonMethods.getPixelsFromPercentageWidth(33),
				CommonMethods.getPixelsFromPercentageHeight(20)));
		this.setBackground(Color.decode("#80CEE1"));
		this.setOpaque(true);

		// COMPONENTS TO ENTER THE NICKNAME
		GraphicJLabel title = new GraphicJLabel("Enter your nickname", new Color(0, 0, 0, 0), new Color(0, 0, 0, 0),
				"Arial", Font.BOLD);
		title.setOpaque(false);

		JTextField nicknameField = new JTextField("");
		nicknameField.setFont(new Font("Arial", Font.BOLD, (int) Constants.SCREEN_SIZE.getWidth() / 70));

		GraphicJButton okButton = new GraphicJButton("OK", Color.decode("#FFDD62"), Color.decode("#FF971A"), "Arial",
				Font.PLAIN);

		// Adding ActionListener to the button
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (nicknameField.getText().equals("")) {
					gameSettings.setPlayer(new Player("guest", 0));
				} else {
					gameSettings.setPlayer(new Player(nicknameField.getText(), 0));
				}

				playPanel.dismissNicknamePanel();
			}
		});

		// ADDING THE COMPONENTS TO THE JPANEL
		this.add(title, new GBCSimplified(0, 0, CommonMethods.getPixelsFromPercentageWidth(8),
				CommonMethods.getPixelsFromPercentageHeight(1), GridBagConstraints.CENTER));
		this.add(nicknameField, new GBCSimplified(0, 1, CommonMethods.getPixelsFromPercentageWidth(17),
				CommonMethods.getPixelsFromPercentageHeight(2), GridBagConstraints.CENTER));
		this.add(okButton, new GBCSimplified(0, 2, CommonMethods.getPixelsFromPercentageWidth(3),
				CommonMethods.getPixelsFromPercentageHeight(1),
				new Insets(CommonMethods.getPixelsFromPercentageHeight(2), 0, 0, 0), GridBagConstraints.CENTER));

	}
}

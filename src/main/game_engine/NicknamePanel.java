package main.game_engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import main.utilities.CommonMethods;
import main.utilities.GraphicJLabel;

public class NicknamePanel extends JPanel {
	public NicknamePanel(PlayPanel playPanel, GameSettings gameSettings) {
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(
				new Dimension(CommonMethods.getPixelsFromPercentage(33), CommonMethods.getPixelsFromPercentage(20)));
		this.setBackground(Color.decode("#80CEE1"));
		this.setOpaque(true);

		// COMPONENTS TO ENTER THE NICKNAME
		GraphicJLabel title = new GraphicJLabel("Enter your nickname", new Color(0, 0, 0, 0), new Color(0, 0, 0, 0),
				"Arial", Font.BOLD);
		title.setOpaque(false);
	}
}

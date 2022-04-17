package main.game_engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import main.utilities.CommonMethods;

public class NicknamePanel extends JPanel {
	public NicknamePanel(PlayPanel playPanel, GameSettings gameSettings) {
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(
				new Dimension(CommonMethods.getPixelsFromPercentage(33), CommonMethods.getPixelsFromPercentage(20)));
		this.setBackground(Color.decode("#80CEE1"));
		this.setOpaque(true);
	}
}

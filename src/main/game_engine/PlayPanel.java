package main.game_engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import main.infinite_map.Map;
import main.utilities.Constants;
import main.utilities.GameSettings;

public class PlayPanel extends JPanel implements ActionListener {
	private Timer refreshRate;
	private Map map;

	public PlayPanel(GameSettings gameSettings) {
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(Constants.SCREEN_SIZE);

	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D canvas = (Graphics2D) g;
		map.animate(canvas);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}

package main.game_engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import main.infinite_map.Map;

public class PlayPanel extends JPanel implements ActionListener{
	private Timer refreshRate;
	private Map map;
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D canvas = (Graphics2D) g;
		map.animate(canvas);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}

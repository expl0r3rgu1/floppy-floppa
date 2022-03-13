package main.infinite_map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TimedFixedObstacleGenerator implements ActionListener {
	private Timer timer;
	private double fixedObstacleSpeed = 0.004; // Every 2.375 seconds a fixedObstacle spawns
	private Map map;

	public TimedFixedObstacleGenerator(Map map) {
		this.map = map;
		this.timer = new Timer((int) (1000 / (map.speed * fixedObstacleSpeed)), this);
		this.timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.map.addFixedObstacle();
	}

}

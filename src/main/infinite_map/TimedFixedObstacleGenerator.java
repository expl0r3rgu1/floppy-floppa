package main.infinite_map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TimedFixedObstacleGenerator extends TimedObstacleGenerator {
	private static final double fixedObstacleSpeed = 0.004; // Every 2.375 seconds a fixedObstacle spawns

	public TimedFixedObstacleGenerator(Map map) {
		super(map, fixedObstacleSpeed);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.getMap().addFixedObstacle();
	}

}

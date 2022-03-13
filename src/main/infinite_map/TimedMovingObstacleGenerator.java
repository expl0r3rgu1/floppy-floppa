package main.infinite_map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TimedMovingObstacleGenerator extends TimedObstacleGenerator {

	public TimedMovingObstacleGenerator(Map map) {
		super(map, 0.001);// Every 10 seconds a movingObstacle spawns
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.getMap().addMovingObstacle();
	}
}

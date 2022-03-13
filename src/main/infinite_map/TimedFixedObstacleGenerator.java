package main.infinite_map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TimedFixedObstacleGenerator extends TimedObstacleGenerator {

	public TimedFixedObstacleGenerator(Map map) {
		super(map, 0.004); // Every 2.375 seconds a fixedObstacle spawns
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.getMap().addFixedObstacle();
	}

}

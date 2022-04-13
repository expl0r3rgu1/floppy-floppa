package main.infinite_map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.utilities.Constants;

public class TimedFixedObstacleGenerator extends TimedObstacleGenerator {

	public TimedFixedObstacleGenerator(Map map) {
		super(map, Constants.FIXED_OBSTACLE_SPEED);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.getMap().addFixedObstacle(); // Spawns a FixedObstacle
	}

}

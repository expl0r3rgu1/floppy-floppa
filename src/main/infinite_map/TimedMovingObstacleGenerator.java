package main.infinite_map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.utilities.Constants;

public class TimedMovingObstacleGenerator extends TimedObstacleGenerator {

	public TimedMovingObstacleGenerator(Map map) {
		super(map, Constants.MOVING_OBSTACLE_SPEED);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.getMap().addMovingObstacle(); // Spawns a MovingObstacle
	}
}

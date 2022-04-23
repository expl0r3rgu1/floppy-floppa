package main.infinite_map;

import java.awt.event.ActionEvent;

import main.utilities.Constants;

/**
 * TimedEntityGenerator customized for FixedObstacles
 */
public class TimedFixedObstacleGenerator extends TimedObstacleGenerator {

	/**
	 * Initializes the Generator with a speed defined by
	 * Constants.FIXED_OBSTACLE_SPEED
	 * 
	 * @param map The instance of the infinite Map
	 */
	public TimedFixedObstacleGenerator(Map map) {
		super(map, Constants.FIXED_OBSTACLE_SPEED);
	}

	/**
	 * Adds a FixedObstacle to the map
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.getMap().addFixedObstacle(); // Spawns a FixedObstacle
	}
}

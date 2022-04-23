package main.infinite_map;

import java.awt.event.ActionEvent;

import main.utilities.Constants;

/**
 * TimedEntityGenerator customized for MovingObstacle
 */
public class TimedMovingObstacleGenerator extends TimedObstacleGenerator {

	/**
	 * Initializes the Generator with a speed defined by
	 * Constants.MOVING_OBSTACLE_SPEED
	 * 
	 * @param map The instance of the infinite Map
	 */
	public TimedMovingObstacleGenerator(Map map) {
		super(map, Constants.MOVING_OBSTACLE_SPEED);
	}

	/**
	 * Adds a MovingObstacle to the map
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.getMap().addMovingObstacle(); // Spawns a MovingObstacle
	}
}

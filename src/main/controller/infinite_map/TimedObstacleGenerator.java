package main.controller.infinite_map;

/**
 * TimedEntityGenerator customized for Obstacle (FixedObstacle or
 * MovingObstacle)
 */
public abstract class TimedObstacleGenerator extends TimedEntityGenerator {

	/**
	 * @param map           The instance of the infinite Map
	 * @param obstacleSpeed The Obstacle spawning speed
	 */
	public TimedObstacleGenerator(Map map, double obstacleSpeed) {
		super(map, obstacleSpeed);
	}
}
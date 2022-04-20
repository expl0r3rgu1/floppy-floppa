package main.obstacles;

import main.utilities.Position;
import main.utilities.Skin;

/**
 * An interface for a Factory of Obstacle
 */
public interface ObstacleFactory {

	/**
	 * fixedObstacleFactory is a method that creates a factory using position and
	 * skin to generate a FixedObstacle
	 * 
	 * @param position - the position for a FixedObstacle
	 * @param skin     - the skin for a FixedObstacle
	 * @return FixedObstacle
	 */
	public FixedObstacle fixedObstacleFactory(Position position, Skin skin);

	/**
	 * movingObstacleFactory is a method that creates a factory using position and
	 * skin to generate a MovingObstacle
	 * 
	 * @param position - the position for a MovingObstacle
	 * @param skin     - the skin for a MovingObstacle
	 * @return MovingObstacle
	 */
	public MovingObstacle movingObstacleFactory(Position position, Skin skin);

}

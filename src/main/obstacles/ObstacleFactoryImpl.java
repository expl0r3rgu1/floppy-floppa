package main.obstacles;

import main.utilities.Position;
import main.utilities.Skin;

/**
 * ObstacleFactoryImpl is a class that implements the interface ObstacleFactory
 */
public class ObstacleFactoryImpl implements ObstacleFactory {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FixedObstacle fixedObstacleFactory(Position position, Skin skin) {
		return new FixedObstacle(position, skin);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MovingObstacle movingObstacleFactory(Position position, Skin skin) {
		return new MovingObstacle(position, skin);
	}

}
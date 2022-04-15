package main.obstacles;

import main.utilities.Position;
import main.utilities.Skin;

public interface ObstacleFactory {
	
	public FixedObstacle fixedObstacleFactory(Position position, Skin skin);
	
	public MovingObstacle movingObstacleFactory(Position position, Skin skin);
	
}

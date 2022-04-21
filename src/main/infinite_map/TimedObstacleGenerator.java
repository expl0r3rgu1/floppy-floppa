package main.infinite_map;

public abstract class TimedObstacleGenerator extends TimedEntityGenerator {

	public TimedObstacleGenerator(Map map, double obstacleSpeed) {
		super(map, obstacleSpeed);
	}
}
package main.infinite_map;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import main.obstacles.FixedObstacle;
import main.obstacles.MovingObstacle;
import main.obstacles.ObstacleFactory;
import main.obstacles.ObstacleFactoryImpl;
import main.utilities.Constants;
import main.utilities.Position;

public class Map {
	// Background and FixedObstacle based on selected scenario
	private ScrollingBackground scrollingBackground;

	private FixedObstacle fixedObstacle;
	private List<MovingObstacle> movingObstacles; // Every kind of MovingObstacle available to spawn

	private Set<FixedObstacle> paintedFixedObstacles; // Set of FixedObstacle that need to be or are being painted
	private Set<MovingObstacle> paintedMovingObstacles; // Set of MovingObstacle that need to be or are being painted

	private TimedFixedObstacleGenerator timedFixedObstacleGenerator; // Timer that manages when to spawn a FixedObstacle
	private TimedMovingObstacleGenerator timedMovingObstacleGenerator; // Timer that manages when to spawn a
																		// MovingObstacle
	private final ObstacleFactory obstacleFactory;

	public Map(ScrollingBackground scrollingBackground, FixedObstacle fixedObstacle,
			List<MovingObstacle> movingObstacles) {
		this.scrollingBackground = scrollingBackground;
		this.fixedObstacle = fixedObstacle;
		this.movingObstacles = movingObstacles;
		this.timedFixedObstacleGenerator = new TimedFixedObstacleGenerator(this);
		this.timedMovingObstacleGenerator = new TimedMovingObstacleGenerator(this);
		this.obstacleFactory = new ObstacleFactoryImpl();
	}

	public ScrollingBackground getScrollingBackground() {
		return scrollingBackground;
	}

	public void setScrollingBackground(ScrollingBackground scrollingBackground) {
		this.scrollingBackground = scrollingBackground;
	}

	public FixedObstacle getFixedObstacle() {
		return fixedObstacle;
	}

	public void setFixedObstacle(FixedObstacle fixedObstacle) {
		this.fixedObstacle = fixedObstacle;
	}

	public List<MovingObstacle> getMovingObstacles() {
		return movingObstacles;
	}

	public void setMovingObstacles(List<MovingObstacle> movingObstacles) {
		this.movingObstacles = movingObstacles;
	}

	// Method that spawns a FixedObstacle cloned from this.fixedObstacle
	protected void addFixedObstacle() {
		this.paintedFixedObstacles
				.add(obstacleFactory.fixedObstacleFactory(
						new Position((int) Constants.SCREEN_SIZE.getWidth(),
								getRandomNumber((int) (Constants.SCREEN_SIZE.getHeight() * 0.25),
										(int) (Constants.SCREEN_SIZE.getHeight() * 0.75))),
						this.fixedObstacle.getSkin()));
	}

	// Utility method to generate random integer in range (consider moving it)
	private int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	// Method that spawns a random MovingObstacle
	protected void addMovingObstacle() {
		int randomMovingObstacleIndex = getRandomNumber(0, this.movingObstacles.size() - 1);

		this.paintedMovingObstacles.add(
				(MovingObstacle) Obstacle.factoryObstacle(this.movingObstacles.get(randomMovingObstacleIndex).getSkin(),
						new Position((int) Constants.SCREEN_SIZE.getWidth(),
								getRandomNumber((int) (Constants.SCREEN_SIZE.getHeight() * 0.25),
										(int) (Constants.SCREEN_SIZE.getHeight() * 0.75)))));
	}

	public void animate(Graphics2D canvas) {
		this.scrollingBackground.animate(canvas);

		// Removing obstacles off-screen from set
		this.paintedFixedObstacles
				.removeIf(obstacle -> obstacle.getPosition().getX() + obstacle.getSkin().getImage().getWidth(null) < 0);
		this.paintedMovingObstacles
				.removeIf(obstacle -> obstacle.getPosition().getX() + obstacle.getSkin().getImage().getWidth(null) < 0);

		// Repainting on-screen obstacles
		this.paintedFixedObstacles.forEach(obstacle -> obstacle.animate(canvas));
		this.paintedMovingObstacles.forEach(obstacle -> obstacle.animate(canvas));
	}
}
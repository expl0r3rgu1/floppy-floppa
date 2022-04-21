package main.infinite_map;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import main.obstacles.FixedObstacle;
import main.obstacles.MovingObstacle;
import main.obstacles.ObstacleFactory;
import main.obstacles.ObstacleFactoryImpl;
import main.utilities.Constants;
import main.utilities.Position;

public class Map {
	// Background and FixedObstacle based on selected scenario
	private final ScrollingBackground scrollingBackground;

	private final FixedObstacle fixedObstacle;
	private final List<MovingObstacle> movingObstacles; // Every kind of MovingObstacle available to spawn

	private final List<FixedObstacle> paintedFixedObstacles; // Set of FixedObstacle that need to be or are being
																// painted
	private final List<MovingObstacle> paintedMovingObstacles; // Set of MovingObstacle that need to be or are being
																// painted

	private final TimedFixedObstacleGenerator timedFixedObstacleGenerator; // Timer that manages when to spawn a
																			// FixedObstacle
	private final TimedMovingObstacleGenerator timedMovingObstacleGenerator; // Timer that manages when to spawn a
	// MovingObstacle

	public Map(ScrollingBackground scrollingBackground, FixedObstacle fixedObstacle,
			List<MovingObstacle> movingObstacles) {
		this.scrollingBackground = scrollingBackground;
		this.fixedObstacle = fixedObstacle;
		this.movingObstacles = movingObstacles;
		this.paintedFixedObstacles = new ArrayList<>();
		this.paintedMovingObstacles = new ArrayList<>();
		this.timedFixedObstacleGenerator = new TimedFixedObstacleGenerator(this);
		this.timedMovingObstacleGenerator = new TimedMovingObstacleGenerator(this);
	}

	public ScrollingBackground getScrollingBackground() {
		return scrollingBackground;
	}

	public FixedObstacle getFixedObstacle() {
		return fixedObstacle;
	}

	public List<MovingObstacle> getMovingObstacles() {
		return movingObstacles;
	}

	public void startTimer() {
		timedFixedObstacleGenerator.getTimer().start();
		timedMovingObstacleGenerator.getTimer().start();
	}

	// Method that spawns a FixedObstacle cloned from this.fixedObstacle
	protected void addFixedObstacle() {
		this.paintedFixedObstacles
				.add(Constants.OBSTACLE_FACTORY.fixedObstacleFactory(
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
		int randomMovingObstacleIndex = getRandomNumber(0, this.movingObstacles.size());

		this.paintedMovingObstacles.add(Constants.OBSTACLE_FACTORY.movingObstacleFactory(
				new Position((int) Constants.SCREEN_SIZE.getWidth(),
						getRandomNumber((int) (Constants.SCREEN_SIZE.getHeight() * 0.25),
								(int) (Constants.SCREEN_SIZE.getHeight() * 0.75))),
				this.movingObstacles.get(randomMovingObstacleIndex).getSkin()));
	}

	public void animate(Graphics2D canvas) {
		this.scrollingBackground.animate(canvas);

		// Repainting on-screen obstacles
		this.paintedFixedObstacles.forEach(obstacle -> obstacle.animate(canvas));
		this.paintedMovingObstacles.forEach(obstacle -> obstacle.animate(canvas));

		// Removing obstacles off-screen from set
		this.paintedFixedObstacles
				.removeIf(obstacle -> obstacle.getPosition().getX() + obstacle.getSkin().getWidth() < 0);
		this.paintedMovingObstacles
				.removeIf(obstacle -> obstacle.getPosition().getX() + obstacle.getSkin().getWidth() < 0);
	}
}
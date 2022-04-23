package main.infinite_map;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import main.obstacles.FixedObstacle;
import main.obstacles.MovingObstacle;
import main.state_changers.Booster;
import main.state_changers.Malus;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Position;

/**
 * The Infinite Scrollable Map of the game
 */
public class Map {
	private final ScrollingBackground scrollingBackground;
	private final List<FixedObstacle> paintedFixedObstacles; // Set of FixedObstacle that need to be or are being
																// painted
	private final List<MovingObstacle> paintedMovingObstacles; // Set of MovingObstacle that need to be or are being
																// painted
	private final List<Malus> paintedMalus;
	private final List<Booster> paintedBoosters;

	private final TimedFixedObstacleGenerator timedFixedObstacleGenerator; // Timer that manages when to spawn a
																			// FixedObstacle
	private final TimedMovingObstacleGenerator timedMovingObstacleGenerator; // Timer that manages when to spawn a
	// MovingObstacle
	private final TimedStateChangerGenerator timedStateChangerGenerator; // Timer that manages when to spawn a random
																			// StateChanger

	/**
	 * @param scrollingBackground The ScrollingBackground to be shown in the Map
	 */
	public Map(ScrollingBackground scrollingBackground) {
		this.scrollingBackground = scrollingBackground;
		this.paintedFixedObstacles = new ArrayList<>();
		this.paintedMovingObstacles = new ArrayList<>();
		this.paintedMalus = new ArrayList<>();
		this.paintedBoosters = new ArrayList<>();
		this.timedFixedObstacleGenerator = new TimedFixedObstacleGenerator(this);
		this.timedMovingObstacleGenerator = new TimedMovingObstacleGenerator(this);
		this.timedStateChangerGenerator = new TimedStateChangerGenerator(this);
	}

	/**
	 * @return The list of currently animated FixedObstacle instances
	 */
	public List<FixedObstacle> getPaintedFixedObstacles() {
		return paintedFixedObstacles;
	}

	/**
	 * @return The list of currently animated MovingObstacle instances
	 */
	public List<MovingObstacle> getPaintedMovingObstacles() {
		return paintedMovingObstacles;
	}

	/**
	 * @return The list of currently animated Malus instances
	 */
	public List<Malus> getPaintedMalus() {
		return paintedMalus;
	}

	/**
	 * @return The list of currently animated Booster instances
	 */
	public List<Booster> getPaintedBoosters() {
		return paintedBoosters;
	}

	/**
	 * Starts the spawning timers
	 */
	public void startTimer() {
		timedFixedObstacleGenerator.getTimer().start();
		timedMovingObstacleGenerator.getTimer().start();
		timedStateChangerGenerator.getTimer().start();
	}

	/**
	 * Stops the spawning timers
	 */
	public void stopTimer() {
		timedFixedObstacleGenerator.getTimer().stop();
		timedMovingObstacleGenerator.getTimer().stop();
		timedStateChangerGenerator.getTimer().stop();
	}

	// Method that spawns a FixedObstacle cloned from this.fixedObstacle
	protected void addFixedObstacle() {
		this.paintedFixedObstacles.add(Constants.OBSTACLE_FACTORY.fixedObstacleFactory(getRandomPosition(),
				Constants.FIXED_OBSTACLE.getSkin()));
	}

	// Method that spawns a random MovingObstacle
	protected void addMovingObstacle() {
		int randomMovingObstacleIndex = CommonMethods.getRandomNumber(0, Constants.MOVING_OBSTACLES.size());

		this.paintedMovingObstacles.add(Constants.OBSTACLE_FACTORY.movingObstacleFactory(getRandomPosition(),
				Constants.MOVING_OBSTACLES.get(randomMovingObstacleIndex).getSkin()));
	}

	// Method that spawns a random StateChanger
	protected void addStateChanger() {
		boolean randomStateChanger = (CommonMethods.getRandomNumber(0, 2) == 0) ? false : true;

		if (randomStateChanger) {
			this.paintedMalus.add(Constants.STATE_CHANGER_FACTORY.malusFactory(getRandomPosition()));
		} else {
			this.paintedBoosters.add(Constants.STATE_CHANGER_FACTORY.boosterFactory(getRandomPosition()));
		}
	}

	private Position getRandomPosition() {
		return new Position((int) Constants.SCREEN_SIZE.getWidth(), CommonMethods.getRandomNumber(
				CommonMethods.getPixelsFromPercentageHeight(25), CommonMethods.getPixelsFromPercentageHeight(75)));
	}

	/**
	 * Animates the ScrollingBackground and the lists of: FixedObstacle,
	 * MovingObstacle, Malus, Booster.
	 * it also removes the entities from their respective lists when they go
	 * offScreen
	 * 
	 * @param canvas A Graphics2D canvas to animate the entities onto
	 */
	public void animate(Graphics2D canvas) {
		this.scrollingBackground.animate(canvas);

		// Repainting on-screen entities
		this.paintedFixedObstacles.forEach(obstacle -> obstacle.animate(canvas));
		this.paintedMovingObstacles.forEach(obstacle -> obstacle.animate(canvas));
		this.paintedMalus.forEach(malus -> malus.animate(canvas));
		this.paintedBoosters.forEach(booster -> booster.animate(canvas));

		// Removing off-screen entities from lists
		this.paintedFixedObstacles
				.removeIf(obstacle -> obstacle.getPosition().getX() + obstacle.getSkin().getWidth() < 0);
		this.paintedMovingObstacles
				.removeIf(obstacle -> obstacle.getPosition().getX() + obstacle.getSkin().getWidth() < 0);
		this.paintedMalus.removeIf(malus -> malus.getPosition().getX() + malus.getSkin().getWidth() < 0);
		this.paintedBoosters.removeIf(booster -> booster.getPosition().getX() + booster.getSkin().getWidth() < 0);
	}
}

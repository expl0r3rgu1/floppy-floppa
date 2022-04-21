package main.infinite_map;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import main.obstacles.FixedObstacle;
import main.obstacles.MovingObstacle;
import main.obstacles.ObstacleFactory;
import main.obstacles.ObstacleFactoryImpl;
import main.state_changers.Booster;
import main.state_changers.Malus;
import main.utilities.CommonMethods;
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
	private final List<Malus> paintedMalus;
	private final List<Booster> paintedBoosters;

	private final TimedFixedObstacleGenerator timedFixedObstacleGenerator; // Timer that manages when to spawn a
																			// FixedObstacle
	private final TimedMovingObstacleGenerator timedMovingObstacleGenerator; // Timer that manages when to spawn a
	// MovingObstacle
	private final TimedStateChangerGenerator timedStateChangerGenerator;

	public Map(ScrollingBackground scrollingBackground, FixedObstacle fixedObstacle,
			List<MovingObstacle> movingObstacles) {
		this.scrollingBackground = scrollingBackground;
		this.fixedObstacle = fixedObstacle;
		this.movingObstacles = movingObstacles;
		this.paintedFixedObstacles = new ArrayList<>();
		this.paintedMovingObstacles = new ArrayList<>();
		this.paintedMalus = new ArrayList<>();
		this.paintedBoosters = new ArrayList<>();
		this.timedFixedObstacleGenerator = new TimedFixedObstacleGenerator(this);
		this.timedMovingObstacleGenerator = new TimedMovingObstacleGenerator(this);
		this.timedStateChangerGenerator = new TimedStateChangerGenerator(this);
	}

	public ScrollingBackground getScrollingBackground() {
		return scrollingBackground;
	}

	public List<FixedObstacle> getPaintedFixedObstacles() {
		return paintedFixedObstacles;
	}

	public List<MovingObstacle> getPaintedMovingObstacles() {
		return paintedMovingObstacles;
	}

	public List<Malus> getPaintedMalus() {
		return paintedMalus;
	}

	public List<Booster> getPaintedBoosters() {
		return paintedBoosters;
	}

	public void startTimer() {
		timedFixedObstacleGenerator.getTimer().start();
		timedMovingObstacleGenerator.getTimer().start();
		timedStateChangerGenerator.getTimer().start();
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
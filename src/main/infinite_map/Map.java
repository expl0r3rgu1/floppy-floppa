package main.infinite_map;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import main.obstacles.FixedObstacle;
import main.obstacles.MovingObstacle;
import main.obstacles.Obstacle;

public class Map {
	private ScrollingBackground scrollingBackground;
	private FixedObstacle fixedObstacle;
	private List<MovingObstacle> movingObstacles;
	private Set<FixedObstacle> paintedFixedObstacles;
	private Set<MovingObstacle> paintedMovingObstacles;
	private TimedFixedObstacleGenerator timedFixedObstacleGenerator;
	private TimedMovingObstacleGenerator timedMovingObstacleGenerator;
	protected int speed = 100;

	public Map(ScrollingBackground scrollingBackground, FixedObstacle fixedObstacle,
			List<MovingObstacle> movingObstacles) {
		this.scrollingBackground = scrollingBackground;
		this.fixedObstacle = fixedObstacle;
		this.movingObstacles = movingObstacles;
		this.timedFixedObstacleGenerator = new TimedFixedObstacleGenerator(this);
		this.timedMovingObstacleGenerator = new TimedMovingObstacleGenerator(this);
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

	protected void addFixedObstacle() {
		this.paintedFixedObstacles
				.add(Obstacle.factoryObstacle(this.fixedObstacle.getSkin(), this.fixedObstacle.getPoisition()));
	}

	private int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	protected void addMovingObstacle() {
		int randomMovingObstacleIndex = getRandomNumber(0, this.movingObstacles.size() - 1);

		this.paintedMovingObstacles
				.add(Obstacle.factoryObstacle(this.movingObstacles.get(randomMovingObstacleIndex).getSkin(),
						this.movingObstacles.get(randomMovingObstacleIndex).getPosition()));
	}

	public void animate(Graphics2D canvas) {
		this.scrollingBackground.animate(canvas);
		
		//Removing obstacles off-screen from set
		this.paintedFixedObstacles.removeIf(obstacle -> obstacle.getPosition().getX() + obstacle.getSkin().getImage().getWidth(null) < 0);
		this.paintedMovingObstacles.removeIf(obstacle -> obstacle.getPosition().getX() + obstacle.getSkin().getImage().getWidth(null) < 0);
		
		//Repainting on-screen obstacles
		this.paintedFixedObstacles.forEach(obstacle -> obstacle.animate(canvas));
		this.paintedMovingObstacles.forEach(obstacle -> obstacle.animate(canvas));
	}
}
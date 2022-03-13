package main.infinite_map;

import java.awt.Graphics2D;
import java.util.Set;

import main.obstacles.FixedObstacle;
import main.obstacles.MovingObstacle;

public class Map {
	private ScrollingBackground scrollingBackground;
	private FixedObstacle fixedObstacle;
	private Set<MovingObstacle> movingObstacles;
	private Set<FixedObstacle> paintedFixedObstacles;
	private Set<MovingObstacle> paintedMovingObstacles;
	private int speed = 100;

	public Map(ScrollingBackground scrollingBackground, FixedObstacle fixedObstacle,
			Set<MovingObstacle> movingObstacles) {
		this.scrollingBackground = scrollingBackground;
		this.fixedObstacle = fixedObstacle;
		this.movingObstacles = movingObstacles;
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

	public Set<MovingObstacle> getMovingObstacles() {
		return movingObstacles;
	}

	public void setMovingObstacles(Set<MovingObstacle> movingObstacles) {
		this.movingObstacles = movingObstacles;
	}

	public void animate(Graphics2D canvas) {
		this.scrollingBackground.animate(canvas);
	}
}
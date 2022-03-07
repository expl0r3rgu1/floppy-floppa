package main.infinite_map;

import java.awt.Graphics2D;
import java.util.Set;

import main.obstacles.FixedObstacle;
import main.obstacles.MovingObstacle;

public class Map {
	private Background background;
	private FixedObstacle fixedObstacle;
	private Set<MovingObstacle> movingObstacles;
	
	public Map(Background background, FixedObstacle fixedObstacle, Set<MovingObstacle> movingObstacles) {
		super();
		this.background = background;
		this.fixedObstacle = fixedObstacle;
		this.movingObstacles = movingObstacles;
	}

	public Background getBackground() {
		return background;
	}

	public void setBackground(Background background) {
		this.background = background;
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
		
	}
}
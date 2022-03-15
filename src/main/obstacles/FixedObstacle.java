package main.obstacles;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import main.utilities.Skin;

public class FixedObstacle extends Obstacle{
	
	public FixedObstacle(Position position, Skin skin) {
		super();
		this.position=position;
		this.skin=skin;
		this.x=(Toolkit.getDefaultToolkit().getScreenSize().width)+1;
		this.y=(Toolkit.getDefaultToolkit().getScreenSize().height);
	}
	
	private FixedObstacle spawnFixedObstacle() {
		
	}
	
	public void animate(Graphics2D a) {
		
	}
	
}

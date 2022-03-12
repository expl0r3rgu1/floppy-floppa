package main.obstacles;

import main.utilities.Movable;
import main.utilities.Position;
import main.utilities.Skin;
import java.awt.Graphics2D;

public abstract class Obstacle extends Movable{
	private Skin skin;
	
	private Obstacle(Position position, Skin skin) {
		super(position);
		this.skin=skin;
	}
	
	public static Obstacle factoryObstacle(Skin skin, Position position) {
		return new Obstacle(position, skin) {};
	}
	
	public Skin getSkin() {
		return this.skin;
	}
	
	public void setSkin(Skin skin) {
		this.skin=skin;
	}
	
	public void animate(Graphics2D a) {
		
	}
}

package main.obstacles;

import main.utilities.Movable;
import main.utilities.Position;
import main.utilities.Skin;
import java.awt.Graphics2D;

public class Obstacle{
	private Skin skin;
	private Position position;
	
	protected Obstacle(Position position, Skin skin) {
		this.position = position;
		this.skin=skin;
	}
	
	public static Obstacle factoryObstacle(Skin skin, Position position) {
		return new Obstacle(position, skin);
	}
	
	public Skin getSkin() {
		return this.skin;
	}
	
	public void setSkin(Skin skin) {
		this.skin=skin;
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void animate(Graphics2D canvas) {
		//TO BE IMPLEMENTED IN SUB-CLASSES
	}
	
}

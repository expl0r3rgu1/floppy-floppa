package main.obstacles;

import main.utilities.Movable;
import main.utilities.Position;
import main.utilities.Skin;

public class Obstacle extends Movable{
	private Skin skin;
	
	public Obstacle factoryObstacle(Skin skin, Position position) {
		final Obstacle obs = new Obstacle();
		return obs;
	}
	
	public Skin getSkin() {
		return this.skin;
	}
	
	public void setSkin(Skin skin) {
		this.skin=skin;
	}
}

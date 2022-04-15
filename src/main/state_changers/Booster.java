package main.state_changers;

import java.awt.Graphics2D;

import main.utilities.Movable;
import main.utilities.Position;
import main.utilities.Skin;

/**
 * A class that implements an object that helps the character in different ways
 */
public class Booster extends Movable {

	private Skin skin;

	/**
	 * @param position the booster initial position
	 * @param skin     the booster skin
	 */
	public Booster(Position position, Skin skin) {
		super(position);
		this.skin = skin;
	}

	public Skin getSkin() {
		return skin;
	}

	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	public Object changeState() {
		return null;
		// TO BE IMPLEMENTED IN SUB-CLASSES
	}

	@Override
	public void animate(Graphics2D canvas) {
		// TO BE IMPLEMENTED IN SUB-CLASSES
	}
}

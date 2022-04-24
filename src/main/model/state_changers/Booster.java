package main.model.state_changers;

import main.model.utilities.Movable;
import main.model.utilities.Position;
import main.model.utilities.Skin;

/**
 * A class that implements an object that helps the character in different ways
 */
public abstract class Booster extends Movable {

	private Skin skin;

	/**
	 * @param position the booster initial position
	 * @param skin     the booster skin
	 */
	public Booster(Position position, Skin skin) {
		super(position);
		this.skin = skin;
	}

	/**
	 * @return the booster skin
	 */
	public Skin getSkin() {
		return skin;
	}

	/**
	 * This method simulates the effect of the booster
	 */
	public abstract void changeState();

	/**
	 * Used to move the booster off screen so that there is only one effective
	 * collision
	 */
	public void moveOffScreen() {
		setPosition(new Position(-getSkin().getWidth(), getPosition().getY()));
	}
}

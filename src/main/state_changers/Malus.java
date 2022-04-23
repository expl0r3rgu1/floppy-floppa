package main.state_changers;

import main.utilities.Movable;
import main.utilities.Position;
import main.utilities.Skin;

/**
 * A class that implements an object that hampers the character in different
 * ways
 */
public abstract class Malus extends Movable {

	private Skin skin;

	/**
	 * @param position - the malus initial position
	 * @param skin     - the malus skin
	 */
	protected Malus(Position position, Skin skin) {
		super(position);
		this.skin = skin;
	}

	/**
	 * getter method for Skin
	 * 
	 * @return skin
	 */
	public Skin getSkin() {
		return skin;
	}

	/**
	 * changeState is the method that changes a state of the Character or of the
	 * game
	 */
	public abstract void changeState();

	/**
	 * moveOffScreen is the method that spawns the Malus out of the Screen after
	 * been used one time
	 */
	public void moveOffScreen() {
		setPosition(new Position(-getSkin().getWidth(), getPosition().getY()));
	}
}
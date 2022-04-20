package main.state_changers;

import java.awt.Graphics2D;

import main.utilities.Movable;
import main.utilities.Position;
import main.utilities.Skin;

/**
 * A class that implements an object that hampers the character in different ways
 */
public class Malus extends Movable {

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
	 * setter method for Skin
	 * 
	 * @param skin
	 */
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
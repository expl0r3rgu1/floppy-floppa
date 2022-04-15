package main.state_changers;

import java.awt.Graphics2D;

import main.utilities.Position;
import main.utilities.Skin;

/**
 * A class that implements an object that helps the character in different ways
 */
public class Booster extends StateChanger {

	/**
	 * @param position the booster initial position
	 * @param skin     the booster skin
	 */
	public Booster(Position position, Skin skin) {
		super(position, skin);
	}

	/**
	 * A factory method that creates a booster
	 * 
	 * @param position the initial booster position
	 * @param skin     the booster skin
	 * @return a new booster
	 */
	public static Booster factoryBooster(Position position, Skin skin) {
		return new Booster(position, skin);
	}

	@Override
	public Object changeState() {
		return null;
		// TO BE IMPLEMENTED IN SUB-CLASSES
	}

	@Override
	public void animate(Graphics2D canvas) {
		// TO BE IMPLEMENTED IN SUB-CLASSES
	}
}

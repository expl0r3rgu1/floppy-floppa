package main.state_changers;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.Random;

import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Position;
import main.utilities.Skin;

/**
 * A class that extends Booster class and implements an entity that randomly
 * gifts coins to the main character every time they hit this booster.
 *
 */
public class CoinsIncrement extends Booster {

	private Random rand;

	/**
	 * 
	 * @param position The CoinsIncrement initial position
	 * @param skin     The CoinsIncrement skin
	 */
	public CoinsIncrement(Position position, Skin skin) {
		super(position, skin);
		rand = new Random();
	}

	private void updatePositionX() {
		setPosition(new Position(getPosition().getX() - 1, getPosition().getY()));
	}

	@Override
	public Integer changeState() {
		return (int) rand.nextInt(100);
	}

	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(), getPosition.getX(), getPosition.getY(),
				CommonMethods.getPixelsFromPercentage(3), CommonMethods.getPixelsFromPercentage(3), null);

		this.updatePositionX();
	}
}

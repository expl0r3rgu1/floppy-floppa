package main.state_changers;

import java.awt.Graphics2D;
import java.util.Random;

import main.game_engine.PlayPanel;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Position;
import main.utilities.Skin;

/**
 * A class that extends Booster class and implements an entity that randomly
 * gifts coins to the main character every time they hit this booster.
 */
public class CoinsIncrement extends Booster {

	private Random rand;

	/**
	 * @param position The CoinsIncrement initial position
	 * @param skin     The CoinsIncrement skin
	 */
	public CoinsIncrement(Position position, Skin skin) {
		super(position, skin);
	}

	/**
	 * The method gives the CoinsIncrement booster a new Position that leaves the Y
	 * position unchanged, while the X position decreases by one pixel so that the
	 * object moves from right to left
	 */
	private void updatePositionX() {
		setPosition(new Position(getPosition().getX() - 3 * Constants.MOVING_FACTOR, getPosition().getY()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void changeState() {
		PlayPanel.incrementTimes++;
		moveOffScreen();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(), getPosition().getX(), getPosition().getY(), getSkin().getWidth(),
				getSkin().getHeight(), null);

		this.updatePositionX();
	}
}

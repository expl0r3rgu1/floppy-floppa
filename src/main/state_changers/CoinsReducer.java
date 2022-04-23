package main.state_changers;

import java.awt.Graphics2D;
import main.game_engine.PlayPanel;
import main.utilities.Constants;
import main.utilities.Position;
import main.utilities.Skin;

/**
 * A class that extends Malus class and implements an entity that randomly takes
 * away coins to the main character every time they hit this malus
 */
public class CoinsReducer extends Malus {

	/**
	 * @param position - The CoinsReducer initial position
	 * @param skin     - The CoinsReducer skin
	 */
	public CoinsReducer(Position position, Skin skin) {
		super(position, skin);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void changeState() {
		PlayPanel.reducerTimes++;
		moveOffScreen();
	}

	/**
	 * The method gives the CoinsReducer malus a new Position that leaves the Y
	 * position unchanged, while the X position decreases so that the object moves
	 * from right to left
	 */
	private void updatePositionX() {
		setPosition(new Position(getPosition().getX() - 3 * Constants.MOVING_FACTOR, getPosition().getY()));
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
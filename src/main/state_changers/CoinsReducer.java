package main.state_changers;

import static org.junit.Assert.assertTrue;

import java.awt.Graphics2D;

import org.junit.jupiter.api.Test;

import main.game_engine.PlayPanel;

import main.utilities.CommonMethods;

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
	
	/**
	 * TestCoinsReducer is a class that tests the updatePosition of CoinsReducer
	 */
	public static class TestCoinsReducer {
		final private Position POSITION = new Position((int) (Constants.SCREEN_SIZE.getWidth()),
				(int) Constants.SCREEN_SIZE.getHeight() / 2);
		final private Position HALFWAY_POSITION = new Position((int) (Constants.SCREEN_SIZE.getWidth() / 2),
				(int) Constants.SCREEN_SIZE.getHeight() / 2);
		final private Skin SKIN = new Skin("coinsreducer", CommonMethods.getImageResource("coinsreducer"),
				(int) this.POSITION.getX(), (int) this.POSITION.getY());

		@Test
		/**
		 * Check if the moving pattern of the malus works correctly
		 */
		void coinsIncrementBoosterMovement() {
			CoinsReducer coinsReducer1 = new CoinsReducer(this.POSITION, this.SKIN);
			coinsReducer1.updatePositionX();
			assertTrue(coinsReducer1.getPosition().getX() == POSITION.getX() - 3 * Constants.MOVING_FACTOR);
			assertTrue(coinsReducer1.getPosition().getY() == POSITION.getY());

			CoinsReducer coinsReducer2 = new CoinsReducer(this.HALFWAY_POSITION, this.SKIN);
			coinsReducer2.updatePositionX();
			assertTrue(coinsReducer2.getPosition().getX() == HALFWAY_POSITION.getX() - 3 * Constants.MOVING_FACTOR);
			assertTrue(coinsReducer2.getPosition().getY() == HALFWAY_POSITION.getY());

		}
	}

}
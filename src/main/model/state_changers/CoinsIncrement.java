package main.model.state_changers;

import static org.junit.Assert.assertTrue;

import java.awt.Graphics2D;

import org.junit.Test;

import main.model.utilities.CommonMethods;
import main.model.utilities.Constants;
import main.model.utilities.Position;
import main.model.utilities.Skin;
import main.view.game_engine.PlayPanel;

/**
 * A class that extends Booster class and implements an entity that randomly
 * gifts coins to the main character every time they hit this booster.
 */
public class CoinsIncrement extends Booster {

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

	public static class TestCoinsIncrement {
		final private Position POSITION = new Position((int) (Constants.SCREEN_SIZE.getWidth()),
				(int) Constants.SCREEN_SIZE.getHeight() / 2);
		final private Position HALFWAY_POSITION = new Position((int) (Constants.SCREEN_SIZE.getWidth() / 2),
				(int) Constants.SCREEN_SIZE.getHeight() / 2);
		final private Skin SKIN = new Skin("coinsincrement", CommonMethods.getImageResource("coinsincrement"),
				(int) this.POSITION.getX(), (int) this.POSITION.getY());

		@Test
		/**
		 * Check if the moving pattern of the booster works correctly
		 */
		public void coinsIncrementBoosterMovement() {
			CoinsIncrement coinsIncrement1 = new CoinsIncrement(this.POSITION, this.SKIN);
			coinsIncrement1.updatePositionX();
			assertTrue(coinsIncrement1.getPosition().getX() == POSITION.getX()- 3 * Constants.MOVING_FACTOR);
			assertTrue(coinsIncrement1.getPosition().getY() == POSITION.getY());

			CoinsIncrement coinsIncrement2 = new CoinsIncrement(this.HALFWAY_POSITION, this.SKIN);
			coinsIncrement2.updatePositionX();
			assertTrue(coinsIncrement2.getPosition().getX() == HALFWAY_POSITION.getX()- 3 * Constants.MOVING_FACTOR);
			assertTrue(coinsIncrement2.getPosition().getY() == HALFWAY_POSITION.getY());

		}
	}
}

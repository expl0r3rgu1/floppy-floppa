package main.state_changers;

import static org.junit.Assert.assertTrue;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.junit.jupiter.api.Test;

import main.character.Character;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Position;
import main.utilities.Skin;

/**
 * A class that extends Booster class and implements an entity that makes the
 * character immortal if hit by them
 */
public class Immortality extends Booster {

	private final Timer timer;

	/**
	 * @param position The CoinsIncrement initial position
	 * @param skin     The CoinsIncrement skin
	 */
	public Immortality(Position position, Skin skin) {
		super(position, skin);
		Character.immortal = false;

		this.timer = new Timer(Constants.IMMORTALITY_TIMEOUT, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Character.immortal = false;
			}
		});
	}

	/**
	 * The method gives the Immortality booster a new Position that leaves the Y
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
		Character.immortal = true;
		timer.start();
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

	public static class TestImmortality {

		final private Position POSITION = new Position((int) (Constants.SCREEN_SIZE.getWidth()),
				(int) Constants.SCREEN_SIZE.getHeight() / 2);
		final private Position HALFWAY_POSITION = new Position((int) (Constants.SCREEN_SIZE.getWidth() / 2),
				(int) Constants.SCREEN_SIZE.getHeight() / 2);
		final private Skin SKIN = new Skin("immortality", CommonMethods.getImageResource("immortality"),
				(int) this.POSITION.getX(), (int) this.POSITION.getY());

		@Test
		/**
		 * Check if the moving pattern of the booster works correctly
		 */
		void immortalityBoosterMovement() {
			Immortality immortality1 = new Immortality(this.POSITION, this.SKIN);
			immortality1.updatePositionX();
			assertTrue(immortality1.getPosition().getX() == POSITION.getX() - 3 * Constants.MOVING_FACTOR);
			assertTrue(immortality1.getPosition().getY() == POSITION.getY());

			Immortality immortality2 = new Immortality(this.HALFWAY_POSITION, this.SKIN);
			immortality2.updatePositionX();
			assertTrue(immortality2.getPosition().getX() == HALFWAY_POSITION.getX() - 3 * Constants.MOVING_FACTOR);
			assertTrue(immortality2.getPosition().getY() == HALFWAY_POSITION.getY());
		}

		@Test
		/**
		 * Checks if the booster actually sets the Immortality field to true
		 */
		void coinsIncrementChangeState() {
			Immortality immortality = new Immortality(this.POSITION, this.SKIN);
			immortality.changeState();
			assertTrue(Character.immortal);
		}
	}
}

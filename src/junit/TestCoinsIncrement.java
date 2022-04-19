package junit;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import main.state_changers.CoinsIncrement;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Position;
import main.utilities.Skin;

public class TestCoinsIncrement {
	private Random rand = new Random();
	final private Position POSITION = new Position((int) (Constants.SCREEN_SIZE.getWidth()),
			(int) Constants.SCREEN_SIZE.getHeight() / 2);
	final private Position HALFWAY_POSITION = new Position((int) (Constants.SCREEN_SIZE.getWidth() / 2),
			(int) Constants.SCREEN_SIZE.getHeight() / 2);
	final private Position RANDOM_POSITION = new Position(rand.nextInt(), rand.nextInt());
	final private Skin SKIN = new Skin("Immortality", CommonMethods.getImageResource("Immortality"),
			(int) this.POSITION.getX(), (int) this.POSITION.getY());
	private int xPos;

	@Test
	/**
	 * Check if the moving pattern of the booster works correctly
	 */
	void coinsIncrementBoosterMovement() {
		CoinsIncrement coinsIncrement = new CoinsIncrement(this.POSITION, this.SKIN);
		xPos = this.POSITION.getX();
		assertTrue(coinsIncrement.getPosition().getX() == xPos--);

		coinsIncrement.setPosition(HALFWAY_POSITION);
		xPos = this.HALFWAY_POSITION.getX();
		assertTrue(coinsIncrement.getPosition().getX() == xPos--);

		coinsIncrement.setPosition(RANDOM_POSITION);
		xPos = this.RANDOM_POSITION.getX();
		assertTrue(coinsIncrement.getPosition().getX() == xPos--);
	}

}

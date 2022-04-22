package junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import main.character.Character;
import main.state_changers.Immortality;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Position;
import main.utilities.Skin;

public class TestImmortality {

	private Random rand = new Random();
	final private Position POSITION = new Position((int) (Constants.SCREEN_SIZE.getWidth()),
			(int) Constants.SCREEN_SIZE.getHeight() / 2);
	final private Position HALFWAY_POSITION = new Position((int) (Constants.SCREEN_SIZE.getWidth() / 2),
			(int) Constants.SCREEN_SIZE.getHeight() / 2);
	final private Position RANDOM_POSITION = new Position(rand.nextInt(), rand.nextInt());
	final private Skin SKIN = new Skin("immortality", CommonMethods.getImageResource("immortality"),
			(int) this.POSITION.getX(), (int) this.POSITION.getY());
	private int xPos;

	@Test
	/**
	 * Check if the moving pattern of the booster works correctly
	 */
	void immortalityBoosterMovement() {
		Immortality immortality = new Immortality(this.POSITION, this.SKIN);
		xPos = this.POSITION.getX();
		assertTrue(immortality.getPosition().getX() == xPos--);

		immortality.setPosition(HALFWAY_POSITION);
		xPos = this.HALFWAY_POSITION.getX();
		assertTrue(immortality.getPosition().getX() == xPos--);

		immortality.setPosition(RANDOM_POSITION);
		xPos = this.RANDOM_POSITION.getX();
		assertTrue(immortality.getPosition().getX() == xPos--);
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

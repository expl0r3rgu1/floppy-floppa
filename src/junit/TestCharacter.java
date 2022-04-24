package junit;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import main.model.character.Character;
import main.model.obstacles.FixedObstacle;
import main.model.obstacles.MovingObstacle;
import main.model.utilities.CommonMethods;
import main.model.utilities.Constants;
import main.model.utilities.Position;
import main.model.utilities.Skin;

public class TestCharacter {
	private final List<FixedObstacle> FIXED_OBSTACLE_LIST = List.of(Constants.OBSTACLE_FACTORY.fixedObstacleFactory(
			new Position((int) Constants.SCREEN_SIZE.getWidth() / 2, (int) Constants.SCREEN_SIZE.getHeight() / 2),
			new Skin("pipe", CommonMethods.getImageResource("pipe"),
					CommonMethods.getImageResource("pipe").getWidth(null),
					CommonMethods.getImageResource("pipe").getHeight(null))));
	private final Position CHARACTER_COLLIDE_UPPER_PIPE = new Position((int) Constants.SCREEN_SIZE.getWidth() / 2,
			(int) Constants.SCREEN_SIZE.getHeight() / 5);
	private final Position CHARACTER_COLLIDE_LOWER_PIPE = new Position((int) Constants.SCREEN_SIZE.getWidth() / 2,
			(int) Constants.SCREEN_SIZE.getHeight() * 4 / 5);
	private final List<MovingObstacle> MOVING_OBSTACLE_LIST = List.of(Constants.OBSTACLE_FACTORY.movingObstacleFactory(
			new Position((int) Constants.SCREEN_SIZE.getWidth() / 2, (int) Constants.SCREEN_SIZE.getHeight() / 2),
			new Skin("Bingus", CommonMethods.getImageResource("Bingus"),
					CommonMethods.getImageResource("Bingus").getWidth(null),
					CommonMethods.getImageResource("Bingus").getHeight(null))));
	private final Position CHARACTER_COLLIDE_CENTER_MOVING_ENTITY = new Position(
			(int) (Constants.SCREEN_SIZE.getWidth() / 2 + MOVING_OBSTACLE_LIST.get(0).getSkin().getWidth() / 2),
			(int) (Constants.SCREEN_SIZE.getHeight() / 2 + MOVING_OBSTACLE_LIST.get(0).getSkin().getHeight() / 2));
	private final Position CHARACTER_COLLIDE_UPPER_BORDER = new Position((int) Constants.SCREEN_SIZE.getWidth() / 2,
			-1);
	private final Position CHARACTER_COLLIDE_LOWER_BORDER = new Position((int) Constants.SCREEN_SIZE.getWidth() / 2,
			(int) Constants.SCREEN_SIZE.getHeight() + 1);

	@Test
	/**
	 * Checks if the character collides correctly with a fixed obstacle
	 */
	public void collideFixedObstacleTest() {
		Character characterUp = new Character(CHARACTER_COLLIDE_LOWER_PIPE,
				new Skin("floppa", CommonMethods.getImageResource("Floppa"),
						CommonMethods.getImageResource("Floppa").getWidth(null),
						CommonMethods.getImageResource("Floppa").getHeight(null)));
		characterUp.collideFixedObstacle(FIXED_OBSTACLE_LIST);
		assertTrue(characterUp.isDead());

		Character characterLo = new Character(CHARACTER_COLLIDE_UPPER_PIPE,
				new Skin("floppa", CommonMethods.getImageResource("Floppa"),
						CommonMethods.getImageResource("Floppa").getWidth(null),
						CommonMethods.getImageResource("Floppa").getHeight(null)));
		characterLo.collideFixedObstacle(FIXED_OBSTACLE_LIST);
		assertTrue(characterLo.isDead());
	}

	@Test
	/**
	 * Checks if the character collides correctly with a moving entity (moving
	 * obstacle, malus and booster), I only test for moving obstacle because the
	 * different methods for the entities all use the same private method
	 */
	public void collideMovingEntityTest() {
		Character character = new Character(CHARACTER_COLLIDE_CENTER_MOVING_ENTITY,
				new Skin("floppa", CommonMethods.getImageResource("Floppa"),
						CommonMethods.getImageResource("Floppa").getWidth(null),
						CommonMethods.getImageResource("Floppa").getHeight(null)));
		character.collideMovingObstacle(MOVING_OBSTACLE_LIST);
		assertTrue(character.isDead());
	}

	@Test
	/**
	 * Checks if the character collides correctly with the upper and lower border of
	 * the map
	 */
	public void collideBordersTest() {
		Character characterLo = new Character(CHARACTER_COLLIDE_LOWER_BORDER,
				new Skin("floppa", CommonMethods.getImageResource("Floppa"),
						CommonMethods.getImageResource("Floppa").getWidth(null),
						CommonMethods.getImageResource("Floppa").getHeight(null)));
		characterLo.collideBorders();
		assertTrue(characterLo.isDead());
		
		Character characterUp = new Character(CHARACTER_COLLIDE_UPPER_BORDER,
				new Skin("floppa", CommonMethods.getImageResource("Floppa"),
						CommonMethods.getImageResource("Floppa").getWidth(null),
						CommonMethods.getImageResource("Floppa").getHeight(null)));
		characterUp.collideBorders();
		assertTrue(characterLo.isDead());
	}
}

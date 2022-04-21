package junit;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import main.character.Character;
import main.obstacles.FixedObstacle;
import main.obstacles.MovingObstacle;
import main.obstacles.ObstacleFactory;
import main.obstacles.ObstacleFactoryImpl;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Position;
import main.utilities.Skin;

public class TestCharacter {
	private final ObstacleFactory OBSTACLE_FACTORY = new ObstacleFactoryImpl();
	private final List<FixedObstacle> FIXED_OBSTACLE_LIST = List.of(OBSTACLE_FACTORY.fixedObstacleFactory(
			new Position((int) Constants.SCREEN_SIZE.getWidth() / 2, (int) Constants.SCREEN_SIZE.getHeight() / 2),
			new Skin("pipe", CommonMethods.getImageResource("pipe"),
					CommonMethods.getImageResource("pipe").getWidth(null),
					CommonMethods.getImageResource("pipe").getHeight(null))));
	private final Position CHARACTER_COLLIDE_UPPER_PIPE = new Position((int) Constants.SCREEN_SIZE.getWidth() / 2,
			(int) Constants.SCREEN_SIZE.getHeight() / 5);
	private final Position CHARACTER_COLLIDE_LOWER_PIPE = new Position((int) Constants.SCREEN_SIZE.getWidth() / 2,
			(int) Constants.SCREEN_SIZE.getHeight() * 4 / 5);
	private final List<MovingObstacle> MOVING_OBSTACLE_LIST = List.of(OBSTACLE_FACTORY.movingObstacleFactory(
			new Position((int) Constants.SCREEN_SIZE.getWidth() / 2, (int) Constants.SCREEN_SIZE.getHeight() / 2),
			new Skin("Bingus", CommonMethods.getImageResource("Bingus"),
					CommonMethods.getImageResource("Bingus").getWidth(null),
					CommonMethods.getImageResource("Bingus").getHeight(null))));
	private final Position CHARACTER_COLLIDE_CENTER_MOVING_ENTITY = new Position(
			(int) (Constants.SCREEN_SIZE.getWidth() / 2 + MOVING_OBSTACLE_LIST.get(0).getSkin().getWidth() / 2),
			(int) (Constants.SCREEN_SIZE.getHeight() / 2 + MOVING_OBSTACLE_LIST.get(0).getSkin().getHeight() / 2));

	@Test
	/**
	 * Checks if the character collides correctly with a fixed obstacle
	 */
	void collideFixedObstacleTest() {
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
	void collideMovingEntityTest() {
		Character character = new Character(CHARACTER_COLLIDE_CENTER_MOVING_ENTITY,
				new Skin("floppa", CommonMethods.getImageResource("Floppa"),
						CommonMethods.getImageResource("Floppa").getWidth(null),
						CommonMethods.getImageResource("Floppa").getHeight(null)));
		character.collideMovingObstacle(MOVING_OBSTACLE_LIST);
		assertTrue(character.isDead());
	}
}

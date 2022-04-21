package junit;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import main.character.Character;
import main.obstacles.FixedObstacle;
import main.obstacles.ObstacleFactoryImpl;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Position;
import main.utilities.Skin;

public class TestCharacter {
	final private List<FixedObstacle> FIXED_OBSTACLE_LIST = List
			.of(new ObstacleFactoryImpl().fixedObstacleFactory(new Position((int) Constants.SCREEN_SIZE.getWidth()/2, (int) Constants.SCREEN_SIZE.getHeight()/2),
					new Skin("pipe", CommonMethods.getImageResource("pipe"),
							CommonMethods.getImageResource("pipe").getWidth(null),
							CommonMethods.getImageResource("pipe").getHeight(null))));
	final private Position CHARACTER_COLLIDE_UPPER_PIPE = new Position((int) Constants.SCREEN_SIZE.getWidth()/2, (int) Constants.SCREEN_SIZE.getHeight()/5);
	final private Position CHARACTER_COLLIDE_LOWER_PIPE = new Position((int) Constants.SCREEN_SIZE.getWidth()/2, (int) Constants.SCREEN_SIZE.getHeight()*4/5);

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
}

package junit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.obstacles.FixedObstacle;
import main.obstacles.MovingObstacle;
import main.obstacles.ObstacleFactoryImpl;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Position;
import main.utilities.Skin;

/**
 * TestFactoryObstacleImpl is a class that tests ObstacleFactoryImpl
 */
public class TestFactoryObstacleImpl {
	final private Position POSITION = new Position((int) Constants.SCREEN_SIZE.getWidth(),
			(int) Constants.SCREEN_SIZE.getHeight());
	final private Skin SKIN_FIXEDOBSTACLE = new Skin("Pipe", CommonMethods.getImageResource("pipe"),
			this.POSITION.getX(), this.POSITION.getY());
	final private Skin SKIN_MOVINGOBSTACLE = new Skin("Bingus", CommonMethods.getImageResource("Bingus"),
			this.POSITION.getX(), this.POSITION.getY());

	@Test
	public void testFactoryFixedObstacle() {
		ObstacleFactoryImpl factory = new ObstacleFactoryImpl();
		FixedObstacle fixedO = new FixedObstacle(POSITION, SKIN_FIXEDOBSTACLE);

		assertTrue(fixedO.equals(factory.fixedObstacleFactory(POSITION, SKIN_FIXEDOBSTACLE)));
	}

	@Test
	public void testFactoryMovingObstacle() {
		ObstacleFactoryImpl factory = new ObstacleFactoryImpl();
		MovingObstacle movingO = new MovingObstacle(POSITION, SKIN_MOVINGOBSTACLE);

		assertTrue(movingO.equals(factory.movingObstacleFactory(POSITION, SKIN_MOVINGOBSTACLE)));
	}
}

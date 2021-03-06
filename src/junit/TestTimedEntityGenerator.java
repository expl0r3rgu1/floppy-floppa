package junit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.controller.infinite_map.Map;
import main.controller.infinite_map.TimedFixedObstacleGenerator;
import main.controller.infinite_map.TimedMovingObstacleGenerator;
import main.controller.infinite_map.TimedStateChangerGenerator;
import main.model.utilities.Constants;

public class TestTimedEntityGenerator {
	private final int TIMEOUT_SUPPLEMENT = 350;

	@Test
	public void testTimedFixedObstacleGenerator() {
		Map map = new Map(null);
		assertTrue(map.getPaintedFixedObstacles().size() == 0);

		new Thread(new Runnable() {

			@Override
			public void run() {
				TimedFixedObstacleGenerator timedFixedObstacleGenerator = new TimedFixedObstacleGenerator(map);
				timedFixedObstacleGenerator.getTimer().start();
				while (timedFixedObstacleGenerator.getTimer().isRunning())
					;
			}
		}).start();

		try {
			Thread.sleep((int) ((1000 / Constants.SPEED) / Constants.FIXED_OBSTACLE_SPEED) + TIMEOUT_SUPPLEMENT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(map.getPaintedFixedObstacles().size() == 1);
	}

	@Test
	public void testTimedMovingObstacleGenerator() {
		Map map = new Map(null);
		assertTrue(map.getPaintedMovingObstacles().size() == 0);

		new Thread(new Runnable() {

			@Override
			public void run() {
				TimedMovingObstacleGenerator timedMovingObstacleGenerator = new TimedMovingObstacleGenerator(map);
				timedMovingObstacleGenerator.getTimer().start();
				while (timedMovingObstacleGenerator.getTimer().isRunning())
					;
			}
		}).start();

		try {
			Thread.sleep((int) ((1000 / Constants.SPEED) / Constants.MOVING_OBSTACLE_SPEED) + TIMEOUT_SUPPLEMENT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(map.getPaintedMovingObstacles().size() == 1);
	}

	@Test
	public void testTimedStateChangerGenerator() {
		Map map = new Map(null);
		assertTrue(map.getPaintedMalus().size() == 0 && map.getPaintedBoosters().size() == 0);

		new Thread(new Runnable() {

			@Override
			public void run() {
				TimedStateChangerGenerator timedStateChangerGenerator = new TimedStateChangerGenerator(map);
				timedStateChangerGenerator.getTimer().start();
				while (timedStateChangerGenerator.getTimer().isRunning())
					;
			}
		}).start();

		try {
			Thread.sleep((int) ((1000 / Constants.SPEED) / Constants.STATE_CHANGER_SPEED) + TIMEOUT_SUPPLEMENT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(map.getPaintedMalus().size() == 1 ^ map.getPaintedBoosters().size() == 1);
	}
}

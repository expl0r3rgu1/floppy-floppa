package main.infinite_map;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.utilities.Constants;

/**
 * The base of all the entity generators such as TimedFixedObstacleGenerator,
 * TimedMovingObstacleGenerator and TimedStateChangerGenerator
 */
public abstract class TimedEntityGenerator implements ActionListener {
	private final Timer timer;
	private final Map map;

	/**
	 * @param map   The instance of the infinite Map
	 * @param speed The spawning speed of the entity
	 */
	public TimedEntityGenerator(Map map, double speed) {
		this.map = map;
		timer = new Timer((int) ((1000 / Constants.SPEED) / speed), this);
	}

	/**
	 * @return The instance of the infinite Map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * @return The timer that is spawning the entities
	 */
	public Timer getTimer() {
		return timer;
	}
}

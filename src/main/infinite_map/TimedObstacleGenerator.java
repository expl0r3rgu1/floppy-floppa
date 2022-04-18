package main.infinite_map;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.utilities.Constants;

public abstract class TimedObstacleGenerator implements ActionListener {
	private Timer timer;
	private Map map;
	private final double obstacleSpeed;

	public TimedObstacleGenerator(Map map, double obstacleSpeed) {
		this.map = map;
		this.obstacleSpeed = obstacleSpeed;
		timer = new Timer((int) ((1000 / Constants.SPEED) / obstacleSpeed), this);
	}

	public void updateObstacleSpeed() {
		if (timer != null) {
			timer.stop();
		}
		timer = new Timer((int) ((1000 / Constants.SPEED) / this.obstacleSpeed), this);
		timer.start();
	}

	protected Map getMap() {
		return map;
	}

	public Timer getTimer() {
		return timer;
	}
}
package main.infinite_map;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.utilities.Constants;

public abstract class TimedObstacleGenerator implements ActionListener {
	private Timer timer;
	private Map map;

	public TimedObstacleGenerator(Map map, double obstacleSpeed) {
		this.map = map;
		this.timer = new Timer((int) (1000 / (Constants.SPEED * obstacleSpeed)), this);
		this.timer.start();
	}

	public void setObstacleSpeed(double obstacleSpeed) {
		if (!timer.equals(null)) {
			timer.stop();
		}
		timer = new Timer((int) (1000 / (Constants.SPEED * obstacleSpeed)), this);
		timer.start();
	}

	protected Map getMap() {
		return map;
	}
}

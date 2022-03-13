package main.infinite_map;

import java.awt.event.ActionListener;

import javax.swing.Timer;

public abstract class TimedObstacleGenerator implements ActionListener {
	private Timer timer;
	private double obstacleSpeed;
	private Map map;

	public TimedObstacleGenerator(Map map, double obstacleSpeed) {
		this.map = map;
		this.timer = new Timer((int) (1000 / (map.speed * obstacleSpeed)), this);
		this.timer.start();
	}

	protected void setObstacleSpeed(double obstacleSpeed) {
		this.obstacleSpeed = obstacleSpeed;
	}

	protected Map getMap() {
		return map;
	}
}

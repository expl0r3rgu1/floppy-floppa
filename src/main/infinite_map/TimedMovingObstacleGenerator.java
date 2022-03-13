package main.infinite_map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TimedMovingObstacleGenerator implements ActionListener{
	private Timer timer;
	private double movingObstacleSpeed = 0.001; //Every 10 seconds a movingObstacle spawns

	public TimedMovingObstacleGenerator(int speed) {
		this.timer = new Timer((int) (1000 / (speed * movingObstacleSpeed)), this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}

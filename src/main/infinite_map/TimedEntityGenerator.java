package main.infinite_map;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.utilities.Constants;

public abstract class TimedEntityGenerator implements ActionListener {
	private Timer timer;
	private Map map;
	private final double speed;

	public TimedEntityGenerator(Map map, double speed) {
		this.map = map;
		this.speed = speed;
		timer = new Timer((int) ((1000 / Constants.SPEED) / speed), this);
	}
}

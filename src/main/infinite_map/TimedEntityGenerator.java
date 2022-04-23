package main.infinite_map;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.utilities.Constants;

public abstract class TimedEntityGenerator implements ActionListener {
	private final Timer timer;
	private final Map map;

	public TimedEntityGenerator(Map map, double speed) {
		this.map = map;
		timer = new Timer((int) ((1000 / Constants.SPEED) / speed), this);
	}

	public Map getMap() {
		return map;
	}

	public Timer getTimer() {
		return timer;
	}
}

package main.infinite_map;

import java.awt.event.ActionListener;

import javax.swing.Timer;

public abstract class TimedEntityGenerator implements ActionListener {
	private Timer timer;
	private Map map;
	private final double speed;

}

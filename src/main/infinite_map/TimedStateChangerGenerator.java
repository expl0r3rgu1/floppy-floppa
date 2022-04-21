package main.infinite_map;

import java.awt.event.ActionEvent;

import main.utilities.Constants;

public class TimedStateChangerGenerator extends TimedEntityGenerator {

	public TimedStateChangerGenerator(Map map) {
		super(map, Constants.STATE_CHANGER_SPEED);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.getMap().addStateChanger();
	}
}

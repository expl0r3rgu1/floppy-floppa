package main.controller.infinite_map;

import java.awt.event.ActionEvent;

import main.model.utilities.Constants;

/**
 * TimedEntityGenerator customized for StateChanger (Malus or Booster)
 */
public class TimedStateChangerGenerator extends TimedEntityGenerator {

	/**
	 * Initializes the Generator with a speed defined by
	 * Constants.STATE_CHANGER_SPEED
	 * 
	 * @param map The instance of the infinite Map
	 */
	public TimedStateChangerGenerator(Map map) {
		super(map, Constants.STATE_CHANGER_SPEED);
	}

	/**
	 * Adds a FixedObstacle to the map
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.getMap().addStateChanger();
	}
}

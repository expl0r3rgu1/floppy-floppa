package main.state_changers;

import main.utilities.Position;
import main.utilities.Skin;

/**
 * StateChangerFactory is an interface for the factory of a StateChanger
 */
public interface StateChangerFactory {

	/**
	 * MalusFactory is a method that creates a factory using position and skin to
	 * generate a Malus
	 * 
	 * @param position - the position for a Malus
	 * @return Malus
	 */
	public Malus malusFactory(Position position);

	/**
	 * BoosterFactory is a method that creates a factory using position and skin to
	 * generate a Booster
	 * 
	 * @param position - the position for a Booster
	 * @return Booster
	 */
	public Booster boosterFactory(Position position);
}

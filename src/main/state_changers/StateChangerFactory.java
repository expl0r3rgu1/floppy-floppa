package main.state_changers;

import main.utilities.Position;
import main.utilities.Skin;

/**
 * StateChangerFactory is an interface for the factory if a StateChanger
 */
public interface StateChangerFactory {

	/**
	 * MalusFactory is a method that creates a factory using position and skiin to
	 * generate a Malus
	 * 
	 * @param position - the position for a Malus
	 * @param skin     - the skin for a Malus
	 * @return Malus
	 */
	public Malus MalusFactory(Position position, Skin skin);

	/**
	 * BoosterFactory is a method that creates a factory using position and skiin to
	 * generate a Booster
	 * 
	 * @param position - the position for a Booster
	 * @param skin     - the skin for a Booster
	 * @return Booster
	 */
	public Booster BoosterFactory(Position position, Skin skin);
}

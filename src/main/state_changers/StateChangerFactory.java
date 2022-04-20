package main.state_changers;

import main.utilities.Position;
import main.utilities.Skin;

public interface StateChangerFactory {
	
	public Malus malusFactory(Position position);

	public Booster boosterFactory(Position position, Skin skin);
}

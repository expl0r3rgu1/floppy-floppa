package main.state_changers;

import main.utilities.Position;
import main.utilities.Skin;

public interface StateChangerFactory {
	
	public Malus MalusFactory(Position position, Skin skin);

	public Booster BoosterFactory(Position position, Skin skin);
}

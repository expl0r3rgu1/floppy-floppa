package main.state_changers;

import main.utilities.Position;
import main.utilities.Skin;

public class StateChangerFactoryImpl implements StateChangerFactory {

	@Override
	public Malus MalusFactory(Position position, Skin skin) {
		return new Malus(position, skin);
	}

	@Override
	public Booster BoosterFactory(Position position, Skin skin) {
		return new Booster(position, skin);
	}

}

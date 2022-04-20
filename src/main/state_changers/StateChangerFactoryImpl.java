package main.state_changers;

import main.utilities.Position;
import main.utilities.Skin;

public class StateChangerFactoryImpl implements StateChangerFactory {

	@Override
	public Malus malusFactory(Position position, Skin skin) {
		return new Malus(position, skin);
	}

	@Override
	public Booster boosterFactory(Position position, Skin skin) {
		return new Booster(position, skin);
	}

}

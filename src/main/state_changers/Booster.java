package main.state_changers;

import main.utilities.Position;
import main.utilities.Skin;

public class Booster extends StateChanger{

	public Booster(Position position, Skin skin) {
		super(position, skin);
	}
	
	public static Booster factoryBooster (Position position, Skin skin) {
		return new Booster(position, skin);
	}
}

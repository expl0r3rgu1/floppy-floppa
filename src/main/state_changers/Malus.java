package main.state_changers;

import main.utilities.Position;
import main.utilities.Skin;

public class Malus extends StateChanger {
	
	
	protected Malus(Position position, Skin skin) {
		super(position, skin);
	}
	
	public static Malus factoryMalus(Position position, Skin skin) {
		return new Malus(position, skin);
	}

}
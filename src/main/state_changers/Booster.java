package main.state_changers;

import java.awt.Graphics2D;

import main.utilities.Position;
import main.utilities.Skin;

public class Booster extends StateChanger {

	public Booster(Position position, Skin skin) {
		super(position, skin);
	}

	public static Booster factoryBooster(Position position, Skin skin) {
		return new Booster(position, skin);
	}
	
	@Override
	public void changeState() {
		// TO BE IMPLEMENTED IN SUB-CLASSES
	}

	@Override
	public void animate(Graphics2D canvas) {
		// TO BE IMPLEMENTED IN SUB-CLASSES
	}
}

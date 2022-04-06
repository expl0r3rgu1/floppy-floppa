package main.state_changers;

import main.utilities.Skin;
import main.utilities.Movable;
import main.utilities.Position;

public abstract class StateChanger extends Movable {
	Skin skin;

	public StateChanger(Position position, Skin skin) {
		super(position);
		this.skin = skin;
	}

	public Skin getSkin() {
		return this.skin;
	}

	public void setSkin(Skin s) {
		this.skin = s;
	}

	public abstract Object changeState();
	
}

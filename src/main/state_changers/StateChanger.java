package main.state_changers;

import main.utilities.Skin;

public abstract class StateChanger {
	Skin skin;
	
	public Skin getSkin() {
		return this.skin;
	}
	
	public void setSkin(Skin s) {
		this.skin=s;
	}
}

package main.state_changers;

import java.awt.Graphics2D;

import main.utilities.Movable;
import main.utilities.Position;
import main.utilities.Skin;

public class Malus extends Movable {

	private Skin skin;

	protected Malus(Position position, Skin skin) {
		super(position);
		this.skin = skin;
	}

	public Skin getSkin() {
		return skin;
	}

	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	public Object changeState() {
		return null;
		// TO BE IMPLEMENTED IN SUB-CLASSES
	}

	@Override
	public void animate(Graphics2D canvas) {
		// TO BE IMPLEMENTED IN SUB-CLASSES
	}
}
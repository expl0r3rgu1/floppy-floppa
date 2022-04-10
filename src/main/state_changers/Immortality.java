package main.state_changers;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import main.utilities.Position;
import main.utilities.Skin;

public class Immortality extends Booster {

	private static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private boolean immortal;
	
	public Immortality(Position position, Skin skin) {
		super(position, skin);
		this.immortal = false;
	}

	public boolean isImmortal() {
		return immortal;
	}

	public void setImmortal(boolean immortal) {
		this.immortal = immortal;
	}

	@Override
	public boolean changeState() {
		this.immortal = true;
		return immortal;
	}

	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(), getPosition.getX(), getPosition.getY(),
				SIZE.getWidth() * 3 / 100, SIZE.getWidth() * 3 / 100, null);
	}
}

package main.state_changers;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import main.utilities.Position;
import main.utilities.Skin;

public class Immortality extends Booster {

	private static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();

	public Immortality(Position position, Skin skin) {
		super(position, skin);
	}

	@Override
	public void changeState() {

	}

	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(this.getSkin().getImage(), this.getPosition.getX(), this.getPosition.getY(),
				SIZE.getWidth() * 3 / 100, SIZE.getWidth() * 3 / 100, null);
	}
}

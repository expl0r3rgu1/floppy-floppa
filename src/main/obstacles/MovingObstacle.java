package main.obstacles;

import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.print.attribute.standard.Sides;

import main.utilities.Position;
import main.utilities.Skin;

public class MovingObstacle extends Obstacle {

	public final static Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();

	public MovingObstacle(Position position, Skin skin) {
		super(position, skin);
	}

	public void movingPattern() {
		while (true) {
			if (getPosition().getY() == ((int) SIZE.getHeight()) / 2) {

				this.movingPatternSupport(1, 5, 1, 800);

			} else {

				this.movingPatternSupport(1, 10, -1, 800);

				this.movingPatternSupport(1, 10, 1, 800);
			}
		}
	}

	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(this.getSkin().getImage(), getPosition().getX(), getPosition().getY(),
				(int) (SIZE.getWidth()) / 20, (int) (SIZE.getWidth()) / 10, null);

		this.movingPattern();
	}

	private void movingPatternSupport(int start, int end, int shift, int sleepTime) {
		for (int i = start; i <= end; i++) {
			setPosition((this.updateView(getPosition().getY(), shift)));
		}
		this.sleep(sleepTime);
	}

	public boolean validPositionY(int y) {
		int maxHeight = (int) SIZE.getHeight();

		return y <= maxHeight && y >= 0;
	}

	private int updateView(int y, int increment) {
		this.sleep(250);
		if (validPositionY(y + increment)) {
			return y + increment;
		}
		return 0;
	}

	private void sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}

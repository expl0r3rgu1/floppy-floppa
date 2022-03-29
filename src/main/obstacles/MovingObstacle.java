package main.obstacles;

import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import main.utilities.Position;
import main.utilities.Skin;

public class MovingObstacle extends Obstacle {

	private Position position;
	private Skin skin;

	public MovingObstacle(Skin skin) {
		super();
		this.skin = skin;
		this.position.setX((Toolkit.getDefaultToolkit().getScreenSize().width) + 1);
		this.position.setY((Toolkit.getDefaultToolkit().getScreenSize().height) / 2);
	}

	public void movingPattern() {
		while (true) {
			if (this.position.getY() == (Toolkit.getDefaultToolkit().getScreenSize().height) / 2) {
				
				this.movingPatternSupport(1, 5, 1, 800);
				
			} else {
				
				this.movingPatternSupport(1, 10, -1, 800);

				this.movingPatternSupport(1, 10, 1, 800);
			}
		}
	}

	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(this.skin, this.position.getX(), this.getY(),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width) / 20,
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width) / 10, null);

		this.movingPattern();
	}

	private void movingPatternSupport(int start, int end, int shift, int sleepTime) {
		for (int i = start; i <= end; i++) {
			this.position.setY(this.updateView(this.position.getY(), shift));
		}
		this.sleep(sleepTime);
	}

	public boolean validPositionY(int y) {
		int maxHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

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

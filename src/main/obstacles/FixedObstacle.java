package main.obstacles;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import main.utilities.Skin;

public class FixedObstacle extends Obstacle{
	
	private Image image;
	private int movingFactor;

	public FixedObstacle(Position position, Skin skin) {
		super();
		this.position=position;
		this.skin=skin;
		this.x=(Toolkit.getDefaultToolkit().getScreenSize().width)+2;
		this.y=(Toolkit.getDefaultToolkit().getScreenSize().height);
	}
	
	public void animate(Graphics2D a) {
		a.drawImage(image, getPosition().getX(), getPosition().getY(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(), null);

		updatePosition(movingFactor);
	}
	
	private void updatePosition(int movingFactor) {
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		if (screenSize.getWidth() > 2000) {
			movingFactor *= 2;
		}

		getPosition().setX(getPosition().getX() - movingFactor);
	}
	
	private boolean isOffStageLeft() {
		if (getPosition().getX() <= -1 * Toolkit.getDefaultToolkit().getScreenSize().getWidth()) {
			return true;
		} else {
			return false;
		}
	}

	private void moveToSideOfSecondBackground() {
		getPosition().setX(getPosition().getX() + (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2);
	}
	
}

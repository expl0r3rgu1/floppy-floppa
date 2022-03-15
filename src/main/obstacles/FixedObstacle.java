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
				(int) image.getWidth(null),
				(int) image.getHeight(null), null);

		updatePosition(movingFactor);
	}
	
	private void updatePosition(int movingFactor) {
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		if (screenSize.getWidth() > 2000) {
			movingFactor *= 2;
		}

		getPosition().setX(getPosition().getX() - movingFactor);
	}
	
}

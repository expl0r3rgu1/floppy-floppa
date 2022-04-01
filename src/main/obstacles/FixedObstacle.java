package main.obstacles;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import main.utilities.Skin;
import test.Position;

public class FixedObstacle extends Obstacle{

	private int movingFactor=1;

	public FixedObstacle(Position position, Skin skin) {
		super(position, skin);
		this.x=(Toolkit.getDefaultToolkit().getScreenSize().width)+1;
		this.y=(Toolkit.getDefaultToolkit().getScreenSize().height);
	}
	
	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(), position.getX(), position.getY(),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth())/10,
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight())/10, null);

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

package main.obstacles;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import main.utilities.Position;
import main.utilities.Skin;


public class FixedObstacle extends Obstacle{

	public static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static final double space = (SIZE.getHeight())/7; //hole the floppa will pass throught
	private int movingFactor=1;

	public FixedObstacle(Position position, Skin skin) {
		super(position, skin);
		this.x=(Toolkit.getDefaultToolkit().getScreenSize().width)+1;
		this.y=(Toolkit.getDefaultToolkit().getScreenSize().height);
	}
	
	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(),  position.getX(), (position.getY()+(space)/2),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth())/10,
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()-(position.getY()+(space)/2)), null);
		canvas.drawImage(getSkin().getImage().rotate, position.getX(), 0,
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth())/10,
				(int) (position.getY()-(space)/2), null);

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

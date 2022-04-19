package main.obstacles;

import java.awt.Graphics2D;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Movable;
import main.utilities.Position;
import main.utilities.Skin;

public class FixedObstacle extends Movable {

	public static final double space = (Constants.SCREEN_SIZE.getHeight()) / 7;
	private Skin skin;

	public FixedObstacle(Position position, Skin skin) {
		super(position);
		this.skin = skin;
	}

	public Skin getSkin() {
		return skin;
	}

	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(), getPosition().getX(), (int) (getPosition().getY() + (space) / 2),
				(int) (Constants.SCREEN_SIZE.getWidth()) / 10,
				(int) (Constants.SCREEN_SIZE.getHeight() - (getPosition().getY() + (space) / 2)), null);
		canvas.drawImage(CommonMethods.getAngledImage(getSkin().getImage(), 180), getPosition().getX(), 0,
				(int) (Constants.SCREEN_SIZE.getWidth()) / 10, (int) (getPosition().getY() - (space) / 2), null);

		updatePosition();
	}

	private void updatePosition() {
		getPosition().setX(getPosition().getX() - Constants.MOVING_FACTOR);
	}

}

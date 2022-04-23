package main.obstacles;

import static org.junit.Assert.assertTrue;

import java.awt.Graphics2D;

import org.junit.jupiter.api.Test;

import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Movable;
import main.utilities.Position;
import main.utilities.Skin;

/**
 * A class that implements a fixed Obstacle
 */
public class FixedObstacle extends Movable {

	private Skin skin;

	/**
	 * @param position - the obstacle initial position
	 * @param skin     - the obstacle Skin
	 */
	public FixedObstacle(Position position, Skin skin) {
		super(position);
		this.skin = skin;
	}

	/*
	 * getter method for Skin
	 */
	public Skin getSkin() {
		return skin;
	}

	/**
	 * setter method for Skin
	 */
	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(), getPosition().getX(), (int) (getPosition().getY() + (Constants.SPACE_BETWEEN_PIPES) / 2),
				(int) (Constants.SCREEN_SIZE.getWidth()) / 10,
				(int) (Constants.SCREEN_SIZE.getHeight() - (getPosition().getY() + (Constants.SPACE_BETWEEN_PIPES) / 2)), null);
		canvas.drawImage(CommonMethods.getAngledImage(getSkin().getImage(), 180), getPosition().getX(), 0,
				(int) (Constants.SCREEN_SIZE.getWidth()) / 10, (int) (getPosition().getY() - (Constants.SPACE_BETWEEN_PIPES) / 2), null);

		updatePosition();
	}

	/**
	 * updatePosition is a method that as its name says updates the position, the X,
	 * of the fixedobstacle
	 */
	private void updatePosition() {
		getPosition().setX(getPosition().getX() - Constants.MOVING_FACTOR);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		FixedObstacle other = (FixedObstacle) obj;
		return super.equals(other) && this.skin.equals(other.skin);
	}

	public static class TestFixedObstacle {

		final private Position POSITION = new Position((int) (Constants.SCREEN_SIZE.getWidth()),
				(int) Constants.SCREEN_SIZE.getHeight() / 2);
		final private Position HALFWAY_POSITION = new Position((int) (Constants.SCREEN_SIZE.getWidth() / 2),
				(int) Constants.SCREEN_SIZE.getHeight() / 2);
		final private Skin SKIN = new Skin("pipe", CommonMethods.getImageResource("pipe"),
				(int) this.POSITION.getX(), (int) this.POSITION.getY());

		@Test
		/**
		 * Check if the moving pattern of the moving obstacle works correctly
		 */
		public void fixedObstacleMovement() {
			FixedObstacle fixedObstacle1 = new FixedObstacle(this.POSITION, this.SKIN);
			fixedObstacle1.updatePosition();
			assertTrue(fixedObstacle1.getPosition().getX() == (POSITION.getX() - Constants.MOVING_FACTOR));

			FixedObstacle fixedObstacle2 = new FixedObstacle(this.HALFWAY_POSITION, this.SKIN);
			fixedObstacle2.updatePosition();
			assertTrue(fixedObstacle2.getPosition().getX() == (HALFWAY_POSITION.getX() - Constants.MOVING_FACTOR));
		}

	}

}

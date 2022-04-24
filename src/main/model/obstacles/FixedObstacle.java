package main.model.obstacles;

import static org.junit.Assert.assertTrue;

import java.awt.Graphics2D;

import org.junit.jupiter.api.Test;

import main.model.utilities.CommonMethods;
import main.model.utilities.Constants;
import main.model.utilities.Movable;
import main.model.utilities.Position;
import main.model.utilities.Skin;

/**
 * A class that implements a fixed Obstacle
 */
public class FixedObstacle extends Movable {

	private final Skin skin;

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
	 * 
	 * @return skin
	 */
	public Skin getSkin() {
		return skin;
	}

	/**
	 * updatePosition is a method that as its name says updates the position, the X,
	 * of the fixedobstacle
	 */
	private void updatePosition() {
		this.setPosition(new Position(this.getPosition().getX() - Constants.MOVING_FACTOR, this.getPosition().getY()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(), getPosition().getX(),
				(int) (getPosition().getY() + (Constants.SPACE_BETWEEN_PIPES) / 2),
				(int) (Constants.SCREEN_SIZE.getWidth()) / 10, (int) (Constants.SCREEN_SIZE.getHeight()
						- (getPosition().getY() + (Constants.SPACE_BETWEEN_PIPES) / 2)),
				null);
		canvas.drawImage(CommonMethods.getAngledImage(getSkin().getImage(), 180), getPosition().getX(), 0,
				(int) (Constants.SCREEN_SIZE.getWidth()) / 10,
				(int) (getPosition().getY() - (Constants.SPACE_BETWEEN_PIPES) / 2), null);

		updatePosition();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		FixedObstacle other = (FixedObstacle) obj;
		return super.equals(other) && this.skin.equals(other.skin);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

  /**
	 * TestFixedObstacle is a class that tests the updatePosition of fixedObstacle
	 */
	public static class TestFixedObstacle {

		final private Position POSITION = new Position((int) (Constants.SCREEN_SIZE.getWidth()),
				(int) Constants.SCREEN_SIZE.getHeight() / 2);
		final private Position HALFWAY_POSITION = new Position((int) (Constants.SCREEN_SIZE.getWidth() / 2),
				(int) Constants.SCREEN_SIZE.getHeight() / 2);
		final private Skin SKIN = new Skin("pipe", CommonMethods.getImageResource("pipe"), (int) this.POSITION.getX(),
				(int) this.POSITION.getY());

		@Test
		/**
		 * Check if the moving pattern of the fixed obstacle works correctly
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

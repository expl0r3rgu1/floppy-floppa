package main.model.obstacles;

import static org.junit.Assert.assertTrue;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.junit.Test;

import main.model.utilities.CommonMethods;
import main.model.utilities.Constants;
import main.model.utilities.Movable;
import main.model.utilities.Position;
import main.model.utilities.Skin;

/**
 * A class that implements an Obstacle that changes its position on the map
 *
 */
public class MovingObstacle extends Movable implements ActionListener {

	private final Timer timer;
	private final Skin skin;
	private int direction = -1;

	/**
	 * @param position the obstacle initial position
	 * @param skin     the obstacle Skin
	 */
	public MovingObstacle(Position position, Skin skin) {
		super(position);
		this.skin = skin;

		this.timer = new Timer(Constants.CHANGE_DIRECTION_TIMEOUT / Constants.SPEED, this);
		this.timer.start();
	}

	/**
	 * @return the MovingObstacle Skin
	 */
	public Skin getSkin() {
		return skin;
	}

	/**
	 * The main method to update the MovingObstacle position, in the map, through
	 * time
	 */
	private void updatePosition() {
		this.setPosition(new Position(getPosition().getX() - 3 * Constants.MOVING_FACTOR,
				getPosition().getY() + direction * Constants.MOVING_FACTOR));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(this.getSkin().getImage(), getPosition().getX(), getPosition().getY(), getSkin().getWidth(),
				getSkin().getHeight(), null);

		this.updatePosition();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.direction = -this.direction;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		MovingObstacle other = (MovingObstacle) obj;
		return super.equals(other) && this.skin.equals(other.skin);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public static class TestMovingObstacle {

		final private Position POSITION = new Position((int) (Constants.SCREEN_SIZE.getWidth()),
				(int) Constants.SCREEN_SIZE.getHeight() / 2);
		final private Position HALFWAY_POSITION = new Position((int) (Constants.SCREEN_SIZE.getWidth() / 2),
				(int) Constants.SCREEN_SIZE.getHeight() / 2);
		final private Skin SKIN = new Skin("Bingus", CommonMethods.getImageResource("Bingus"),
				(int) this.POSITION.getX(), (int) this.POSITION.getY());
		private int direction = -1;

		@Test
		/**
		 * Check if the moving pattern of the moving obstacle works correctly
		 */
		public void movingObstacleMovement() {
			MovingObstacle movingObstacle = new MovingObstacle(this.POSITION, this.SKIN);
			movingObstacle.updatePosition();

			assertTrue(movingObstacle.getPosition().getX() == (POSITION.getX() - 3 * Constants.MOVING_FACTOR));
			assertTrue(movingObstacle.getPosition().getY() == (POSITION.getY() + direction * Constants.MOVING_FACTOR));

			MovingObstacle movingObstacle1 = new MovingObstacle(this.HALFWAY_POSITION, this.SKIN);
			movingObstacle1.updatePosition();
			assertTrue(movingObstacle1.getPosition().getX() == (HALFWAY_POSITION.getX() - 3 * Constants.MOVING_FACTOR));
			assertTrue(movingObstacle1.getPosition()
					.getY() == (HALFWAY_POSITION.getY() + direction * Constants.MOVING_FACTOR));
		}

	}

}

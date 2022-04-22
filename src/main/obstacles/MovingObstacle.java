package main.obstacles;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.utilities.Constants;
import main.utilities.Movable;
import main.utilities.Position;
import main.utilities.Skin;

/**
 * A class that implements an Obstacle that changes its position on the map
 *
 */
public class MovingObstacle extends Movable implements ActionListener {

	private Timer timer;
	private Skin skin;
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
	 * Sets the MovingObstacle Skin
	 * 
	 * @param skin
	 */
	public void setSkin(Skin skin) {
		this.skin = skin;
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
		MovingObstacle other = (MovingObstacle)obj;
		return super.equals(other) && this.skin.equals(other.skin);
	}

}

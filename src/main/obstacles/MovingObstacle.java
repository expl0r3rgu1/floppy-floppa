package main.obstacles;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.utilities.CommonMethods;
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
	private int shift;
	private int counter;
	private int firstPositionShift;
	private int PositionShift;
	private int upShift;
	private int downShift;
	private Skin skin;

	/**
	 * @param position the obstacle initial position
	 * @param skin     the obstacle Skin
	 */
	public MovingObstacle(Position position, Skin skin) {
		super(position);
		this.skin = skin;
		counter = 0;
		firstPositionShift = CommonMethods.getPixelsFromPercentage(5);
		PositionShift = CommonMethods.getPixelsFromPercentage(10);
		upShift = 1;
		downShift = -1;
		this.timer = new Timer(Constants.CHANGE_DIRECTION_TIMEOUT, this);
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
	 * {@inheritDoc}
	 */
	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(this.getSkin().getImage(), getPosition().getX(), getPosition().getY(), getSkin().getWidth(),
				getSkin().getHeight(), null);

		this.movingPattern();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		setPosition(new Position(getPosition().getX(), getPosition().getY() + shift));
		timer.stop();
	}

}

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

	public Skin getSkin() {
		return skin;
	}

	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	/**
	 * This method changes the Obstacle X and Y positions through time
	 */
	public void movingPattern() {
		this.movingPatternSupportX();
		if (counter == 0) {

			this.movingPatternSupportY(this.firstPositionShift, this.upShift);
			counter++;

		} else {

			this.movingPatternSupportY(this.PositionShift, this.downShift);

			this.movingPatternSupportY(this.PositionShift, this.upShift);
		}
	}

	/**
	 * It sets a new position for the object, where its X position is decreased by 1
	 * pixel, so that the moving obstacle keeps moving from right to left
	 */
	private void movingPatternSupportX() {
		setPosition(new Position(getPosition().getX() - 1, getPosition().getY()));
	}

	/**
	 * For end times the method calls a timer, after
	 * Constants.CHANGE_DIRECTION_TIMEOUT milliseconds the actionPermormed method is
	 * called and the Y position changes according to the shift field
	 * 
	 * @param end
	 * @param shift Determines if the position will increment or decrement
	 */
	private void movingPatternSupportY(int end, int shift) {
		for (int i = 0; i < end; i++) {
			this.shift = shift;
			this.timer.start();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(this.getSkin().getImage(), getPosition().getX(), getPosition().getY(),
				CommonMethods.getPixelsFromPercentage(10), CommonMethods.getPixelsFromPercentage(10), null);

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

package main.state_changers;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Position;
import main.utilities.Skin;

/**
 * A class that extends Booster class and implements an entity that makes the
 * character immortal if hit by them
 */
public class Immortality extends Booster {

	private boolean immortal;
	private Timer timer;

	/**
	 * @param position The CoinsIncrement initial position
	 * @param skin     The CoinsIncrement skin
	 */
	public Immortality(Position position, Skin skin) {
		super(position, skin);
		this.immortal = false;

		this.timer = new Timer(Constants.CHANGED_STATE_TIME, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				immortal = false;
			}
		});
	}

	/**
	 * @return immortal, true if the character has hit the booster, false otherwise
	 */
	public boolean isImmortal() {
		return immortal;
	}

	/**
	 * Sets the immortal parameter of the Immortality booster class
	 * 
	 * @param immortal
	 */
	public void setImmortal(boolean immortal) {
		this.immortal = immortal;
	}

	/**
	 * The method gives the Immortality booster a new Position that leaves the Y
	 * position unchanged, while the X position decreases by one pixel so that the
	 * object moves from right to left
	 */
	private void updatePositionX() {
		setPosition(new Position(getPosition().getX() - Constants.MOVING_FACTOR, getPosition().getY()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object changeState() {
		this.immortal = true;
		timer.start();
		return immortal;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(), getPosition().getX(), getPosition().getY(),
				CommonMethods.getPixelsFromPercentageWidth(4), CommonMethods.getPixelsFromPercentageWidth(4), null);

		this.updatePositionX();
	}
}

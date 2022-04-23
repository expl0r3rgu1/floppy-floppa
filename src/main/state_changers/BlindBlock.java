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
 * A class that extends Malus class and implements an entity that makes appear a
 * black block on the screen, blocking the players vision of part of the right
 * side of the screen, every time they hit this malus
 */
public class BlindBlock extends Malus implements ActionListener {

	private boolean collided = false;
	private Timer timer;

	/**
	 * @param position - The CoinsReducer initial position
	 * @param skin     - The CoinsReducer skin
	 */
	public BlindBlock(Position position, Skin skin) {
		super(position, skin);
		timer = new Timer(Constants.CHANGED_STATE_TIME, this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void changeState() {
		timer.start();
		this.collided = true;
		setPosition(new Position(getPosition().getX() - Constants.SKIN_DIMENSION, getPosition().getY()));
	}

	/**
	 * The method gives the CoinsReducer malus a new Position that leaves the Y
	 * position unchanged, while the X position decreases so that the object moves
	 * from right to left
	 */
	private void updatePositionX() {
		setPosition(new Position(getPosition().getX() - 3 * Constants.MOVING_FACTOR, getPosition().getY()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(), getPosition().getX(), getPosition().getY(), getSkin().getWidth(),
				getSkin().getHeight(), null);

		this.updatePositionX();

		if (collided) {
			canvas.drawImage(getSkin().getImage(), (int) Constants.SCREEN_SIZE.getWidth() / 3, 0,
					CommonMethods.getPixelsFromPercentageWidth(33), (int) Constants.SCREEN_SIZE.getHeight(), null);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.collided = false;
		timer.stop();
	}

}

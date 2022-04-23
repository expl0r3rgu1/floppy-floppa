package main.state_changers;

import main.utilities.Constants;
import main.utilities.Position;
import main.utilities.Skin;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * A class that extends Malus class and implements an entity that makes appear a
 * stain on the screen, blocking the players vision of parts of the screen,
 * every time they hit this malus
 */
public class BlackStain extends Malus implements ActionListener {

	private boolean collided = false;
	private Timer timer;

	/**
	 * @param position - The CoinsReducer initial position
	 * @param skin     - The CoinsReducer skin
	 */
	public BlackStain(Position position, Skin skin) {
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
			canvas.drawImage(getSkin().getImage(), 0, 0, (int) Constants.SCREEN_SIZE.getWidth(),
					(int) Constants.SCREEN_SIZE.getHeight(), null);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.collided = false;
		this.timer.stop();
	}

}

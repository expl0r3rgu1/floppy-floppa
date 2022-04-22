package main.state_changers;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import main.character.Character;
import main.utilities.Constants;
import main.utilities.Position;
import main.utilities.Skin;

/**
 * A class that extends Booster class and implements an entity that makes the
 * character immortal if hit by them
 */
public class Immortality extends Booster {

	private Timer timer;

	/**
	 * @param position The CoinsIncrement initial position
	 * @param skin     The CoinsIncrement skin
	 */
	public Immortality(Position position, Skin skin) {
		super(position, skin);
		Character.immortal = false;

		this.timer = new Timer(Constants.IMMORTALITY_TIMEOUT, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Character.immortal = false;
			}
		});
	}

	/**
	 * The method gives the Immortality booster a new Position that leaves the Y
	 * position unchanged, while the X position decreases by one pixel so that the
	 * object moves from right to left
	 */
	private void updatePositionX() {
		setPosition(new Position(getPosition().getX() - 3 * Constants.MOVING_FACTOR, getPosition().getY()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void changeState() {
		Character.immortal = true;
		timer.start();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(), getPosition().getX(), getPosition().getY(), getSkin().getWidth(),
				getSkin().getHeight(), null);

		this.updatePositionX();
	}
}

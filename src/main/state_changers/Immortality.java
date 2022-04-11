package main.state_changers;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import main.utilities.Constants;
import main.utilities.Position;
import main.utilities.Skin;

/**
 * 
 * A class that extends Booster class and implements an entity that makes the
 * character immortal if hit by them
 *
 */
public class Immortality extends Booster {

	private boolean immortal;
	private Timer timer;

	/**
	 * 
	 * @param position The CoinsIncrement initial position
	 * @param skin     The CoinsIncrement skin
	 */
	public Immortality(Position position, Skin skin) {
		super(position, skin);
		this.immortal = false;

		this.timer = new javax.swing.Timer(Constants.CHANGED_STATE_TIME, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				immortal = false;
			}
		});
	}

	/**
	 * 
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

	@Override
	public Object changeState() {
		this.immortal = true;
		timer.start();
		return immortal;
	}

	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(), getPosition.getX(), getPosition.getY(),
				Constants.SCREEN_SIZE.getWidth() * 3 / 100, Constants.SCREEN_SIZE.getWidth() * 3 / 100, null);
	}
}

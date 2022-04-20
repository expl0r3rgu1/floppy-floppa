package main.state_changers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Position;
import main.utilities.Skin;

/**
 * A class that extends Malus class and implements an entity that makes appear a
 * black block on the screen, blocking the players vision of part of the right side of
 * the screen, every time they hit this malus
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
	 * changeState is a method that makes timer start when the player collides with
	 * the malus
	 */
	public Object changeState() {
		timer.start();
		this.collided = true;
		return null;
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
	 * animate is a method that draws the malus on the screen and it updates its
	 * position by the method updatePositionX and when the player collides in it an
	 * image of a stain is drawn on the playing screen
	 * 
	 * @param canvas - Graphics2D
	 */
	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(), getPosition().getX(), getPosition().getY(),
				CommonMethods.getPixelsFromPercentage(3), CommonMethods.getPixelsFromPercentage(3), null);

		this.updatePositionX();
		
		if (collided) {
			canvas.drawImage(getSkin().getImage(), 0, 0, (int) Constants.SCREEN_SIZE.getWidth(),
					(int) Constants.SCREEN_SIZE.getHeight(), null);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.collided = false;
		timer.stop();
	}

}

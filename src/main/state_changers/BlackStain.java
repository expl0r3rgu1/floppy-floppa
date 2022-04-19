package main.state_changers;

import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Position;
import main.utilities.Skin;
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

/**
 * A class that extends Malus class and implements an entity that makes appear a
 * stain on the screen, blocking the players vision of parts of the screen,
 * every time they hit this malus
 */
public class BlackStain extends Malus implements ActionListener {

	// Dirty Screen
	private String blackstains;
	private StainPanel stain;
	private Timer timer = new Timer(Constants.CHANGED_STATE_TIME, this);

	/**
	 * @param position - The CoinsReducer initial position
	 * @param skin     - The CoinsReducer skin
	 */
	public BlackStain(Position position, Skin skin) {
		super(position, skin);
		blackstains = "blackstains";
		stain = new StainPanel(blackstains);
	}

	public Object changeState() {
		timer.start();
		play.add(stain);

		return null;
	}

	/**
	 * The method gives the CoinsReducer malus a new Position that leaves the Y
	 * position unchanged, while the X position decreases by one pixel so that the
	 * object moves from right to left
	 */
	private void updatePositionX() {
		setPosition(new Position(getPosition().getX() - 1, getPosition().getY()));
	}

	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(), getPosition().getX(), getPosition().getY(),
				CommonMethods.getPixelsFromPercentage(3), CommonMethods.getPixelsFromPercentage(3), null);

		this.updatePositionX();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		play.remove(stain);
	}

}

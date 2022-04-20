package main.state_changers;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;

import main.utilities.Constants;
import main.utilities.CommonMethods;
import main.utilities.Position;
import main.utilities.Skin;

/**
 * A class that extends Malus class and implements an entity that randomly
 * takes away coins to the main character every time they hit this malus
 */
public class CoinsReducer extends Malus {

	// Lose coins
	ArrayList<Integer> malus = new ArrayList<Integer>(Arrays.asList(5, 10, 20, 30, 40, 50));

	/**
	 * @param position - The CoinsReducer initial position
	 * @param skin     - The CoinsReducer skin
	 */
	public CoinsReducer(Position position, Skin skin) {
		super(position, skin);
	}

	public Integer changeState() {
		return malus.get((int) (Math.random() * (malus.size())));
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

}
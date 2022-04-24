package main.model.infinite_map;

import java.awt.Graphics2D;
import java.awt.Image;

import main.model.utilities.Constants;
import main.model.utilities.Movable;
import main.model.utilities.Position;

/**
 * A still background that fills the whole screen
 */
public class Background extends Movable {
	private final String name;
	private final Image image;

	/**
	 * @param name  The name of the Background
	 * @param image The image that will be displayed
	 */
	public Background(String name, Image image) {
		super(new Position(0, 0));
		this.name = name;
		this.image = image;
	}

	/**
	 * @param name     The name of the Background
	 * @param image    The image that will be displayed
	 * @param position The starting position the image will be displayed at
	 */
	public Background(String name, Image image, Position position) {
		super(position);
		this.name = name;
		this.image = image;
	}

	/**
	 * @return The name of the Background
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The image of the Background
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void animate(Graphics2D canvas) {
		// Drawing the background on the canvas to fill it completely
		canvas.drawImage(image, getPosition().getX(), getPosition().getY(), (int) Constants.SCREEN_SIZE.getWidth(),
				(int) Constants.SCREEN_SIZE.getHeight(), null);
	}

	/**
	 * Moves the Background to the left by Constants.MOVING_FACTOR pixels
	 */
	public void updatePosition() {
		getPosition().setX(getPosition().getX() - Constants.MOVING_FACTOR);
	}

	/**
	 * @return True if the image is completely off-screen left
	 */
	public boolean isOffStageLeft() {
		if (getPosition().getX() <= -1 * Constants.SCREEN_SIZE.getWidth()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Moves the Background to the right screen edge
	 */
	public void moveToRightScreenEdge() {
		getPosition().setX(getPosition().getX() + (int) Constants.SCREEN_SIZE.getWidth() * 2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		Background other = (Background) obj;
		return super.equals(other) && this.name.equals(other.getName()) && this.image.equals(other.getImage());
	}
}

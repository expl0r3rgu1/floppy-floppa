package main.infinite_map;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import main.utilities.Movable;
import main.utilities.Position;

public class Background extends Movable {
	private String name;
	private Image image;
	private int movingFactor; // Makes the background skip movingFactor pixels at each update

	public Background(String name, Image image) {
		super(new Position(0, 0));
		this.name = name;
		this.image = image;
		this.movingFactor = 1;
	}

	public Background(String name, Image image, Position position) {
		super(position);
		this.name = name;
		this.image = image;
		this.movingFactor = 1;
	}

	public Background(String name, Image image, int movingFactor) {
		super(new Position(0, 0));
		this.name = name;
		this.image = image;
		this.movingFactor = movingFactor;
	}

	public Background(String name, Image image, Position position, int movingFactor) {
		super(position);
		this.name = name;
		this.image = image;
		this.movingFactor = movingFactor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public void animate(Graphics2D canvas) {
		// Drawing the background on the canvas to fill it completely
		canvas.drawImage(image, getPosition().getX(), getPosition().getY(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(), null);

		updatePosition(movingFactor);

		if (isOffStageLeft()) {
			moveToSideOfSecondBackground();
		}
	}

	private void updatePosition(int movingFactor) {
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// Checking the width of the screen to decide if to use double the movingFactor
		// or not
		if (screenSize.getWidth() > 2000) {
			movingFactor *= 2;
		}

		getPosition().setX(getPosition().getX() - movingFactor);
	}

	private boolean isOffStageLeft() {
		if (getPosition().getX() <= -1 * Toolkit.getDefaultToolkit().getScreenSize().getWidth()) {
			return true;
		} else {
			return false;
		}
	}

	private void moveToSideOfSecondBackground() {
		getPosition().setX(getPosition().getX() + (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2);
	}
}

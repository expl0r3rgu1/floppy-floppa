package main.infinite_map;

import java.awt.Graphics2D;
import java.awt.Image;

import main.utilities.Constants;
import main.utilities.Movable;
import main.utilities.Position;

public class Background extends Movable {
	private final String name;
	private final Image image;

	public Background(String name, Image image) {
		super(new Position(0, 0));
		this.name = name;
		this.image = image;
	}

	public Background(String name, Image image, Position position) {
		super(position);
		this.name = name;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public Image getImage() {
		return image;
	}

	@Override
	public void animate(Graphics2D canvas) {
		// Drawing the background on the canvas to fill it completely
		canvas.drawImage(image, getPosition().getX(), getPosition().getY(), (int) Constants.SCREEN_SIZE.getWidth(),
				(int) Constants.SCREEN_SIZE.getHeight(), null);
	}

	public void updatePosition() {
		getPosition().setX(getPosition().getX() - Constants.MOVING_FACTOR);
	}

	public boolean isOffStageLeft() {
		if (getPosition().getX() <= -1 * Constants.SCREEN_SIZE.getWidth()) {
			return true;
		} else {
			return false;
		}
	}

	public void moveToRightScreenEdge() {
		getPosition().setX(getPosition().getX() + (int) Constants.SCREEN_SIZE.getWidth() * 2);
	}

	@Override
	public boolean equals(Object obj) {
		Background other = (Background) obj;
		return super.equals(other) && this.name.equals(other.getName()) && this.image.equals(other.getImage());
	}
}

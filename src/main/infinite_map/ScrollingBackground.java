package main.infinite_map;

import java.awt.Graphics2D;
import java.awt.Image;

import main.utilities.Constants;
import main.utilities.Position;

public class ScrollingBackground {

	// Two copies of the background image to scroll
	private final Background backOne;
	private final Background backTwo;

	public ScrollingBackground(String name, Image image) {
		backOne = new Background(name, image);
		backTwo = new Background(name, image, new Position((int) Constants.SCREEN_SIZE.getWidth(), 0));
	}

	// Animates the two backgrounds
	public void animate(Graphics2D canvas) {
		backOne.animate(canvas);
		backTwo.animate(canvas);
	}
}

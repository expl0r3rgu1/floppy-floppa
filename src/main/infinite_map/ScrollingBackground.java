package main.infinite_map;

import java.awt.Graphics2D;

import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Position;

public class ScrollingBackground {

	// Two copies of the background image to scroll
	private Background backOne;
	private Background backTwo;

	public ScrollingBackground(String name, String imageFile) {
		backOne = new Background(name, CommonMethods.getImageResource(imageFile));
		backTwo = new Background(name, CommonMethods.getImageResource(imageFile),
				new Position((int) Constants.SCREEN_SIZE.getWidth(), 0));
	}

	// Animates the two backgrounds
	public void animate(Graphics2D canvas) {
		backOne.animate(canvas);
		backTwo.animate(canvas);
	}
}

package main.model.infinite_map;

import java.awt.Graphics2D;
import java.awt.Image;

import main.model.utilities.Constants;
import main.model.utilities.Position;

/**
 * A Decorator of Background that makes of two still Backgrounds a scrollable
 * one
 */
public class ScrollingBackground {

	// Two copies of the background image to scroll
	private final Background backOne;
	private final Background backTwo;

	/**
	 * @param name  The name of the ScrollingBackground
	 * @param image The image that will be displayed
	 */
	public ScrollingBackground(String name, Image image) {
		backOne = new Background(name, image);
		backTwo = new Background(name, image, new Position((int) Constants.SCREEN_SIZE.getWidth(), 0));
	}

	/**
	 * Animates the two Background instances, updates their Position and moves them
	 * to the right edge of the screen when if Background.isOffStageLeft() returns
	 * true
	 * 
	 * @param canvas A Graphics2D canvas to animate the two Background instances
	 *               onto
	 */
	public void animate(Graphics2D canvas) {
		backOne.animate(canvas);
		backTwo.animate(canvas);

		backOne.updatePosition();
		backTwo.updatePosition();

		if (backOne.isOffStageLeft()) {
			backOne.moveToRightScreenEdge();
		}

		if (backTwo.isOffStageLeft()) {
			backTwo.moveToRightScreenEdge();
		}
	}
}

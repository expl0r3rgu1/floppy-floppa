package main.infinite_map;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.utilities.Position;

public class ScrollingBackground {

	// Two copies of the background image to scroll
	private Background backOne;
	private Background backTwo;

	public ScrollingBackground(String name, String imageFile) {
		backOne = new Background(name, getImageResource(imageFile));
		backTwo = new Background(name, getImageResource(imageFile),
				new Position((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 0));
	}

	public void animate(Graphics2D canvas) {
		backOne.animate(canvas);
		backTwo.animate(canvas);
	}

	public Image getImageResource(String imageName) {
		try {
			return ImageIO.read(getClass().getResource("/resources/images/" + imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}

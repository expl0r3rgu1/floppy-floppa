package main.obstacles;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import main.obstacles.Obstacle;
import main.utilities.Position;
import main.utilities.Skin;

public class FixedObstacle extends Obstacle {

	public static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static final double space = (SIZE.getHeight()) / 7; // hole the floppa will pass throught
	private int movingFactor = 1;

	public FixedObstacle(Position position, Skin skin) {
		super(position, skin);
	}

	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(), getPosition().getX(), (int) (getPosition().getY() + (space) / 2),
				(int) (SIZE.getWidth()) / 10, (int) (SIZE.getHeight() - (getPosition().getY() + (space) / 2)), null);
		canvas.drawImage(getUpsidedownImage(getSkin().getImage()), getPosition().getX(), 0,
				(int) (SIZE.getWidth()) / 10, (int) (getPosition().getY() - (space) / 2), null);

		updatePosition(movingFactor);
	}

	private void updatePosition(int movingFactor) {
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		if (screenSize.getWidth() > 2000) {
			movingFactor *= 2;
		}

		getPosition().setX(getPosition().getX() - movingFactor);
	}

	private Image getUpsidedownImage(Image image) {
		double rotationRequired = Math.toRadians(180);
		double locationX = image.getWidth(null) / 2;
		double locationY = image.getHeight(null) / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		return op.filter(toBufferedImage(image), null);
	}

	private static BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		return bimage;
	}

}

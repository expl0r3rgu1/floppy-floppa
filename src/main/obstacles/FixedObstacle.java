package main.obstacles;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import main.utilities.Constants;
import main.utilities.Movable;
import main.utilities.Position;
import main.utilities.Skin;

public class FixedObstacle extends Movable {

	public static final double space = (Constants.SCREEN_SIZE.getHeight()) / 7;
	private Skin skin;

	public FixedObstacle(Position position, Skin skin) {
		super(position);
		this.skin = skin;
	}

	public Skin getSkin() {
		return skin;
	}

	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(), getPosition().getX(), (int) (getPosition().getY() + (space) / 2),
				(int) (Constants.SCREEN_SIZE.getWidth()) / 10,
				(int) (Constants.SCREEN_SIZE.getHeight() - (getPosition().getY() + (space) / 2)), null);
		canvas.drawImage(getUpsidedownImage(getSkin().getImage()), getPosition().getX(), 0,
				(int) (Constants.SCREEN_SIZE.getWidth()) / 10, (int) (getPosition().getY() - (space) / 2), null);

		updatePosition();
	}

	private void updatePosition() {
		getPosition().setX(getPosition().getX() - Constants.MOVING_FACTOR);
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

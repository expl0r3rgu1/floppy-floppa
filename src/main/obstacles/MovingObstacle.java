package main.obstacles;

import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.attribute.standard.Sides;
import javax.swing.Timer;

import main.utilities.Constants;
import main.utilities.Position;
import main.utilities.Skin;

public class MovingObstacle extends Obstacle implements ActionListener {

	private Timer timer;
	private int shift;

	public MovingObstacle(Position position, Skin skin) {
		super(position, skin);
		this.timer = new Timer(300, this);
	}

	public void movingPattern() {
		while (true) {
			if (getPosition().getY() == ((int) Constants.SCREEN_SIZE.getHeight()) / 2) {

				this.movingPatternSupport(5, 1);

			} else {

				this.movingPatternSupport(10, -1);

				this.movingPatternSupport(10, 1);
			}
		}
	}

	private void movingPatternSupport(int end, int shift) {
		for (int i = 0; i < end; i++) {
			this.shift = shift;
			this.timer.start();
		}
	}

	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(this.getSkin().getImage(), getPosition().getX(), getPosition().getY(),
				(int) (Constants.SCREEN_SIZE.getWidth()) / 20, (int) (Constants.SCREEN_SIZE.getWidth()) / 10, null);

		this.movingPattern();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setPosition(new Position(getPosition().getX(), getPosition().getY() + shift));
		timer.stop();
	}

}

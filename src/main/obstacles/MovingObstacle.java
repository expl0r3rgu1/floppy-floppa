package main.obstacles;

import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.attribute.standard.Sides;
import javax.swing.Timer;

import main.utilities.Position;
import main.utilities.Skin;

public class MovingObstacle extends Obstacle {

	public final static Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private Timer timer1;
	private Timer timer2;
	private boolean flag1 = false;
	private boolean flag2 = false;

	public MovingObstacle(Position position, Skin skin) {
		super(position, skin);
		this.timer1 = new Timer(250, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				flag1 = false;
			}
		});
		this.timer2 = new Timer(800, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				flag2 = false;
			}
		});
	}

	public void movingPattern() {
		while (true) {
			if (getPosition().getY() == ((int) SIZE.getHeight()) / 2) {

				this.movingPatternSupport(1, 5, 1);

			} else {

				this.movingPatternSupport(1, 10, -1);

				this.movingPatternSupport(1, 10, 1);
			}
		}
	}

	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(this.getSkin().getImage(), getPosition().getX(), getPosition().getY(),
				(int) (SIZE.getWidth()) / 20, (int) (SIZE.getWidth()) / 10, null);

		this.movingPattern();
	}

	private void movingPatternSupport(int start, int end, int shift) {
		for (int i = start; i <= end; i++) {
			setPosition((this.updateView(getPosition().getY(), shift)));
		}
		flag2 = true;
		if (flag2) {
			timer2.start();
		} else {
			timer2.stop();
		}
	}

	public boolean validPositionY(int y) {
		int maxHeight = (int) SIZE.getHeight();

		return y <= maxHeight && y >= 0;
	}

	private int updateView(int y, int increment) {
		flag1 = true;
		if (flag1) {
			timer1.start();
		} else {
			timer1.stop();
		}
		
		if (validPositionY(y + increment)) {
			return y + increment;
		}
		return 0;
	}
}

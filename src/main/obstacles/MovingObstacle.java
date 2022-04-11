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
	private boolean flag2 = false;
	private int shift;

	public MovingObstacle(Position position, Skin skin) {
		super(position, skin);
		this.timer1 = new Timer(250, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setPosition(getPosition().getY() + shift);
			}
		});
		this.timer2 = new Timer(800, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}

	public void movingPattern() {
		while (true) {
			if (getPosition().getY() == ((int) SIZE.getHeight()) / 2) {

				this.movingPatternSupport(5, 1);

			} else {

				this.movingPatternSupport(10, -1);

				this.movingPatternSupport(10, 1);
			}
		}
	}

	@Override
	public void animate(Graphics2D canvas) {
		canvas.drawImage(this.getSkin().getImage(), getPosition().getX(), getPosition().getY(),
				(int) (SIZE.getWidth()) / 20, (int) (SIZE.getWidth()) / 10, null);

		this.movingPattern();
	}

	private void movingPatternSupport(int end, int shift) {
		for (int i = 0; i < end; i++) {
			this.shift = shift;
			this.timer1.start();
		}
//		flag2 = true;
//		if (flag2) {
//			timer2.start();
//		} else {
//			timer2.stop();
//		}
	}

}

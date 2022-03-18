package main.obstacles;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics2D;

public class MovingObstacle extends Obstacle {
	
//	private int x;
//	private int y;
//	private Image skin;

	public MovingObstacle(Image skin) {
		super();
		this.skin = skin;
		this.x = (Toolkit.getDefaultToolkit().getScreenSize().width)+1;
		this.y = (Toolkit.getDefaultToolkit().getScreenSize().height)/2;
	}

	public void movingPattern() {
		while (true) {
			if(y == (Toolkit.getDefaultToolkit().getScreenSize().height)/2) {
				for (int i = 1; i <= 5; i++) {
					this.y = this.updateView(this.y, 1);
				}
				this.sleep(800);
			}else {
				for (int i = 1; i <= 10; i++) {
					this.y = this.updateView(this.y, -1);
				}
				this.sleep(800);

				for (int i = 1; i <= 0; i++) {
					this.y = this.updateView(this.y, +1);
				}
				this.sleep(800);
			}
		}
	}
	
	public void animate(Graphics2D g) {
		
	}
	
	public boolean validPositionY(int y) {
		int maxHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		if(y <= maxHeight && y >= 0) {
			return true;
		}else {
			return false;
		}
	}

	private int updateView(int y, int increment) {
		this.sleep(250);
		if(validPositionY(y+increment)) {
			return y + increment;
		}
		return 0;
	}

	private void sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}

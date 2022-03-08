package main.obstacles;

import java.awt.Toolkit;

public class MovingObstacle extends Obstacle {

	
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	public MovingObstacle() {
		super();
	}

	public void movingPattern() {
		this.position = height / 2;
		int j = 0;
		while (j < 3) {
			if(position == height/2) {
				for (int i = 1; i <= 5; i++) {
					this.position = this.updateView(this.position, 1);
					System.out.println(position);
				}
				this.sleep(750);
			}else {
				for (int i = 1; i <= 10; i++) {
					this.position = this.updateView(this.position, -1);
					System.out.println(position);
				}
				this.sleep(750);

				for (int i = 1; i <= 10; i++) {
					this.position = this.updateView(this.position, +1);
					System.out.println(position);
				}
				this.sleep(750);
			}
			j++;
		}
	}

	private int updateView(int position, int increment) {
		this.sleep(250);
		return position + increment;
	}

	private void sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}

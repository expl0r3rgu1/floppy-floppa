package main.obstacles;

import java.awt.Toolkit;

public class MovingObstacle extends Obstacle {
	
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	public MovingObstacle() {
		super();
		this.position = height/2;
		this.skin;
	}

	public void movingPattern() {
		while (true) {
			if(position == height/2) {
				for (int i = 1; i <= 5; i++) {
					this.position = this.updateView(this.position, 1);
					System.out.println(position);
				}
				this.sleep(800);
			}else {
				for (int i = 1; i <= 10; i++) {
					this.position = this.updateView(this.position, -1);
					System.out.println(position);
				}
				this.sleep(800);

				for (int i = 1; i <= 0; i++) {
					this.position = this.updateView(this.position, +1);
					System.out.println(position);
				}
				this.sleep(800);
			}
		}
	}
	public boolean validPosition(int pos) {
		int maxHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		if(pos <= maxHeight && pos >= 0) {
			return true;
		}else {
			return false;
		}
	}

	private int updateView(int position, int increment) {
		this.sleep(250);
		if(validPosition(position+increment)) {
			return position + increment;
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

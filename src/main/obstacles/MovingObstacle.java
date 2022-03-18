package main.obstacles;

import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import main.utilities.Position;
import main.utilities.Skin;

public class MovingObstacle extends Obstacle {
	
	private Position position;
	private Skin skin;

	public MovingObstacle(Skin skin) {
		super();
		this.skin = skin;
		this.position.setX((Toolkit.getDefaultToolkit().getScreenSize().width)+1);
		this.position.setY((Toolkit.getDefaultToolkit().getScreenSize().height)/2);
	}

	public void movingPattern() {
		while (true) {
			if(this.position.getY() == (Toolkit.getDefaultToolkit().getScreenSize().height)/2) {
				for (int i = 1; i <= 5; i++) {
					this.position.setY(this.updateView(this.position.getY(), 1));
				}
				this.sleep(800);
			}else {
				for (int i = 1; i <= 10; i++) {
					this.position.setY(this.updateView(this.position.getY(), -1));
				}
				this.sleep(800);

				for (int i = 1; i <= 0; i++) {
					this.position.setY(this.updateView(this.position.getY(), 1));
				}
				this.sleep(800);
			}
		}
	}
	
	public void animate(Graphics2D canvas) {
		canvas.drawImage(this.skin, this.x, this.y, 
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width)/20, 
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width)/10, null);
		
		this.movingPattern();
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

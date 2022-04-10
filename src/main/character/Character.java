package main.character;

import main.obstacles.FixedObstacle;
import main.obstacles.MovingObstacle;
import main.state_changers.Malus;
import main.state_changers.StateChanger;
import main.utilities.Movable;
import main.utilities.Position;
import main.utilities.Skin;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

public class Character extends Movable {
	private Skin skin;
	private boolean dead; //true if the character is dead
	private boolean hit; //true if the character is hit by an obstacle
	private boolean jumping; //true if the character is jumping
	private Timer timer;
	
	//these variables will be changed with constants
	private int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	public Character(Position position, Skin skin) {
		super(position);
		this.skin = skin;
		this.dead = false;
		this.hit = false;
		this.jumping = false;
		this.timer = new Timer(500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jumping = false;
				timer.stop();
			}
			
		});
	}
	
	public boolean isDead() {
		return this.dead;
	}
	
	public void die() {
		this.dead = true;
	}
	
	public boolean isHit() {
		return this.hit;
	}
	
	public void hit() {
		this.hit = true;
	}
	
	public void setSkin(Skin skin){
		this.skin = skin;
	}
	
	public Skin getSkin() {
		return this.skin;
	}
	
	public void jump() {
		
		if (!this.jumping) {
			this.timer.start();
			this.jumping = true;
		} else {
			this.timer.restart();
		}
	}
	
	public void collide(FixedObstacle fixedObstacle) {
		//variables to make it more readable
		int characterPointOfCollisionX = this.getPosition().getX() + (int) this.skin.getImage().getWidth(null);
		int characterY = this.getPosition().getY();
		int characterPointOfCollisionY = characterY + (int) fixedObstacle.getSkin().getImage().getHeight(null);
		int obstacleLeftLimit = fixedObstacle.getPosition().getX();
		int obstacleRightLimit = fixedObstacle.getPosition().getX() + (int) fixedObstacle.getSkin().getImage().getWidth(null);
		int upperObstacleRange = fixedObstacle.getPosition().getY() + (int) FixedObstacle.space/2;
		int lowerObstacleRange = fixedObstacle.getPosition().getY() - (int) FixedObstacle.space/2;
		
		//the purpose of this if is to check if the character is in the range of the obstacle
		if (characterPointOfCollisionX >= obstacleLeftLimit
			&& characterPointOfCollisionX <= obstacleRightLimit) {
					
			//the purpose of this if is to check if the character hits the obstacle
			if (characterY >= upperObstacleRange
				|| characterPointOfCollisionY <= lowerObstacleRange) {
						
				this.hit();
			}
		}
	}

	public void collide(MovingObstacle movingObstacle) {
		//variables to make it more readable
		int x = movingObstacle.getPosition().getX();
		int y = movingObstacle.getPosition().getY();
		int height = (int) movingObstacle.getSkin().getImage().getHeight(null);
		int width = (int) movingObstacle.getSkin().getImage().getWidth(null);
		
		if(this.checkCollision(x, y, height, width)) {
			this.hit();
		}
	}
	
	public void collide(StateChanger sc) {
		//variables to make it more readable
		int x = sc.getPosition().getX();
		int y = sc.getPosition().getY();
		int height = (int) sc.getSkin().getImage().getHeight(null);
		int width = (int) sc.getSkin().getImage().getWidth(null);
		
		if(this.checkCollision(x, y, height, width)) {
			sc.changeState();
		}
	}
	
	public void collideBorders() {
		//variables to make it more readable
		int characterUpperPointOfCollision = this.getPosition().getY();
		int characterLowerPointOfCollision = this.getPosition().getY() + (int) this.getSkin().getImage().getHeight(null);
		int upperBorder = 0;
		int lowerBorder = this.screenHeight;
		
		if (characterUpperPointOfCollision >= upperBorder
			|| characterLowerPointOfCollision <= lowerBorder) {
			
			this.hit();
		}
	}
	
	private boolean checkCollision(int x, int y, int height, int width) {
		int characterY = this.getPosition().getY();
		int characterPointOfCollision = this.getPosition().getX() + (int) this.skin.getImage().getWidth(null);
		int obstacleHeightLowerLimit = characterY + height;
		int obstacleHeightUpperLimit = y;
		int obstacleWidthLimit= x + width;
		
		//the purpose of this if is to check if the character is in the range of the obstacle
		if (characterY <= obstacleHeightUpperLimit
			&& characterY >= obstacleHeightLowerLimit) {
					
			//the purpose of this if is to check if the character hits the obstacle
			if (characterPointOfCollision >= x
				&& characterPointOfCollision <= obstacleWidthLimit) {
								
				return true;
			}
		}
		
		return false;
	}
	
	
	@Override
	public void animate(Graphics2D canvas) {
		
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();
		int width = (int) (screenWidth * 0.035);
		int height = (int) (screenHeight * 0.045);
		
		if (this.jumping) {
			canvas.drawImage(getAngledImage(-15), x, y, width, height, null);
		} else {
			canvas.drawImage(getAngledImage(15), x, y, width, height, null);
		}

		if (this.isHit()) {
			this.die();
		}

		this.updatePosition();
	}
	
	private void updatePosition() {
		
		int value = jumping ? -2 : 1;
		this.getPosition().setY(this.getPosition().getY() + value);

	}
	
	private Image getAngledImage(int degrees) {
		double rotationRequired = Math.toRadians(degrees);
		double locationX = this.skin.getImage().getWidth(null) / 2;
		double locationY = this.skin.getImage().getHeight(null) / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		return op.filter(toBufferedImage(this.skin.getImage()), null);
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

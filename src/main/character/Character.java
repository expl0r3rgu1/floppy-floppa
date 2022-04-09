package main.character;

import main.obstacles.FixedObstacle;
import main.obstacles.MovingObstacle;
import main.state_changers.Malus;
import main.state_changers.StateChanger;
import main.utilities.Movable;
import main.utilities.Position;
import main.utilities.Skin;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.Timer;

public class Character extends Movable implements ActionListener{
	private Skin skin;
	private boolean dead; //true if the character is dead
	private boolean hit; //true if the character is hit by an obstacle
	private boolean falling; //true if the character is fallin
	private boolean jumping;
	private Timer timer;
	
	public Character(Position position, Skin skin) {
		super(position);
		this.skin = skin;
		this.dead = false;
		this.hit = false;
		this.falling = true;
		this.jumping = false;
		this.timer = new Timer(100, this);
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
		//to-do: add rotation
		if (!this.jumping) {
			this.timer.start();
		}
		this.falling = false;
		this.jumping = true;
		this.updatePosition(falling);
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
		int lowerBorder = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
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
		
		/*double angle = (((90 * (velocity + 20) / 20) - 90))*Math.PI/180;
		
		if(angle > Math.PI) {
			angle = Math.PI;
		}*/
		
		//this.rotateAndDrawImage(canvas, angle);
		
		//these variables will be changed with constants
		int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();
		int width = (int) (screenWidth * 0.035);
		int height = (int) (screenHeight * 0.045);
		
		
		canvas.drawImage(this.getSkin().getImage(), x, y, width, height, null);
		
		if(this.isHit()) {
			this.die();
		}
		
		if(!this.jumping) {
			this.timer.stop();
			this.updatePosition(falling);
		}
	}
	
	/*private void rotateAndDrawImage(Graphics2D g2D, double angle) {
		//these variables will be changed with constants
		int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		int transformX = this.getPosition().getX() + this.getSkin().getImage().getWidth(null)/2;
		int transformY = this.getPosition().getY() + this.getSkin().getImage().getHeight(null)/2;
		int x = this.getPosition().getX();
		int y = this.initialY + this.velocity;
		int width = (int) (screenWidth * 0.035);
		int height = (int) (screenHeight * 0.045);
		
		AffineTransform rotatedImage = AffineTransform.getRotateInstance(Math.toRadians(angle), transformX, transformY);
		g2D.setTransform(rotatedImage);
		g2D.drawImage(this.getSkin().getImage(), x, y, width, height, null);
	}*/
	
	private void updatePosition(boolean falling) {
		//these variable will be changed with constants
		int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		double value = falling ? screenHeight*0.0015 : -screenHeight*0.0015;
		int y = this.getPosition().getY();
		
		this.getPosition().setY((int) (y + value));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.falling = true;
		this.jumping = false;
		this.updatePosition(falling);
	}
	
}

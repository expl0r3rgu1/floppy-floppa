package main.character;

import main.utilities.Movable;
import main.utilities.Position;
import main.utilities.Skin;
import java.awt.*;
import java.util.Timer;

public class Character extends Movable {
	private Skin skin;
	private boolean dead; //true if the character is dead
	private boolean hit; //true if the character is hit by an obstacle
	private int velocity; //it will be used to move the character
	private int gravity; //it will determine if the character goes down (default) or up if it jumps
	private static int DELAY=1; //a constant used to make the typical effect of the bird's jump in flappy bird
	private Timer timer;
	
	
	Character(Position position, Skin skin) {
		super(position);
		this.skin=skin;
		this.dead=false;
		this.hit=false;
	}
	
	public boolean isDead() {
		return this.dead;
	}
	
	public void die() {
		this.dead=true;
	}
	
	public boolean isHit() {
		return this.hit;
	}
	
	public void hit() {
		this.hit=true;
	}
	
	public void jump() {
		//to-do
	}
	
	public void collide() {
		//to-do
	}
	
	@Override
	public void animate(Graphics2D canvas) {
		//to-do
	}
	
}

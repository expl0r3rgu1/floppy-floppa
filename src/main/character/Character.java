package main.character;

import main.utilities.Movable;
import main.utilities.Position;
import main.utilities.Skin;

public class Character extends Movable {
	private Skin skin;
	private boolean dead; //true if the character is alive
	private boolean hit; //true if the character is hit by an obstacle
	
	Character(Skin skin, Position position) {
		this.skin=skin;
		//
		this.dead=false;
		this.hit=false;
	}
	
	public boolean isDead() {
		if (dead) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void die() {
		this.dead=true;
	}
	
	public boolean isHit() {
		if (hit) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void hit() {
		this.hit=true;
	}
	
}

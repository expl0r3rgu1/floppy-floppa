package main.utilities;

import java.awt.Graphics2D;

public abstract class Movable {
	Position position;
	
	public Movable(Position position) {
		this.position=position;
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	public void setPosition(Position position) {
		this.position=position;
	}
	
	public abstract void animate(Graphics2D canvas);
	
	@Override
	public boolean equals(Object obj) {
		Movable other = (Movable) obj;
		return this.position.equals(other.position);
	}
}

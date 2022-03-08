package main.utilities;

import java.awt.*;

public abstract class Movable {
	Position position;
	
	Movable(Position position) {
		this.position=position;
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	public void setPosition(Position position) {
		this.position=position;
	}
	
	public void animate(Graphics2D g) {}
}

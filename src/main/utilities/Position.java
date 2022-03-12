package main.utilities;

import java.awt.*;

public class Position {
	//the range of these integers is from 0 to the screen size
	private int x; 
	private int y;
	
	Position(int x, int y) {
		if(this.validPosition(x, y)) {
			this.x=x;
			this.y=y;
		}
	}
	
	private boolean validPosition(int x, int y) {
		//I get the screen size to check if the position is in the right range
		int maxWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int maxHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		if(x<=maxWidth && y<=maxHeight) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		if(this.validPosition(x, this.y)) {
			this.x=x;
		}
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		if(this.validPosition(this.x, y)) {
			this.y=y;
		}
	}
}

package main.utilities;

public class Position {
	//the range of these integers is from 0 to the screen size
	private int x; 
	private int y;
	
	public Position(int x, int y) {
		if(this.validPosition(x, y)) {
			this.x=x;
			this.y=y;
		}
	}
	
	private boolean validPosition(int x, int y) {
		
		if(x <= Constants.SCREEN_SIZE.getWidth() 
			&& y <= Constants.SCREEN_SIZE.getHeight()) {
			
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

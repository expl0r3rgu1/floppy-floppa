package main.utilities;

public class Position {
	//the range of these integers is from 0 to the screen size
	private int x; 
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		Position other = (Position) obj;
		return this.x == other.getX() && this.y == other.getY();
	}
}

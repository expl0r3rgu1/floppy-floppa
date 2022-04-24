package main.model.utilities;

/**
 * A class that implements an object to keep track of the position of the
 * various entities on the map
 */
public class Position {
	private int x;
	private int y;

	/**
	 * @param x the x coordinate on the map
	 * @param y the y coordinate on the map
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Getter of the x coordinate
	 * 
	 * @return the current x coordinate
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Setter of the x coordinate
	 * 
	 * @param x the new x coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Getter of the y coordinate
	 * 
	 * @return the current y coordinate
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Setter of the y coordinate
	 * 
	 * @param y the new y coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object obj) {
		Position other = (Position) obj;
		return this.x == other.getX() && this.y == other.getY();
	}
}

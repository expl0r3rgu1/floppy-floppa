package main.model.utilities;

import java.awt.Graphics2D;

/**
 * A class that represents all entities that will move on the map
 */
public abstract class Movable {
	private Position position;

	/**
	 * @param position the initial spawning position of the entity
	 */
	public Movable(Position position) {
		this.position = position;
	}

	/**
	 * Getter of the entity position
	 * 
	 * @return the current position of the entity
	 */
	public Position getPosition() {
		return this.position;
	}

	/**
	 * Setter of the entity position
	 * 
	 * @param position the new position of the entity
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * A method that will be used to animate all the entities on the map
	 * 
	 * @param canvas the entity that will be animated
	 */
	public abstract void animate(Graphics2D canvas);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		Movable other = (Movable) obj;

		if (other == null) {
			return false;
		}

		return this.position.equals(other.position);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}

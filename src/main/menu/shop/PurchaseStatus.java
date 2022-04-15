package main.menu.shop;

/**
 * A class that keeps track of the current purchase status of an object X
 *
 * @param <X>
 */

public class PurchaseStatus<X> {
	private X x;
	private boolean purchased;

	public PurchaseStatus(X x, boolean purchased) {
		this.x = x;
		this.purchased = purchased;
	}

	/**
	 * @return the X field
	 */
	public X getX() {
		return this.x;
	}

	/**
	 * Sets the X field
	 * 
	 * @param x
	 */
	public void setX(X x) {
		this.x = x;
	}

	/**
	 * @return true if the item has been purchased, false otherwise
	 */
	public boolean isPurchased() {
		return this.purchased;
	}

	/**
	 * Sets the purchase field to true
	 */
	public void purchase() {
		this.purchased = true;
	}

}

package main.utilities;

import java.awt.Image;

/**
 * A class that implements a generic item with its price field
 */
public abstract class PricedItem {

	protected String name;
	protected Image image;
	protected int price;

	/**
	 * @param name  the item's name
	 * @param image the item's Image
	 * @param price the item's price
	 */
	public PricedItem(String name, Image image, int price) {
		this.name = name;
		this.image = image;
		this.price = price;
	}

	/**
	 * @return the item's name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the item's name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the item's image
	 */
	public Image getImage() {
		return this.image;
	}

	/**
	 * Sets the item's image
	 * 
	 * @param image
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @return the item's price
	 */
	public int getPrice() {
		return this.price;
	}

	/**
	 * Sets the item's price
	 * 
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

}

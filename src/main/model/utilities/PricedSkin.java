package main.model.utilities;

import java.awt.Image;

/**
 * A class that extends Skin class and shapes a Skin object equipped with a
 * price field
 */
public class PricedSkin extends Skin {
	private int price;

	/**
	 * 
	 * @param name   the Skin name
	 * @param image  the Image to show the object
	 * @param width  the Skin width
	 * @param height the Skin height
	 * @param price  the PricedSkin price
	 */
	public PricedSkin(String name, Image image, int width, int height, int price) {
		super(name, image, width, height);
		this.price = price;
	}

	/**
	 * @return the PricedSkin price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Sets the PricedSkin price
	 * 
	 * @param price the price field
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		PricedSkin other = (PricedSkin) obj;
		return super.equals(other) && this.price == other.getPrice();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}

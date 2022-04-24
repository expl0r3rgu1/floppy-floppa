package main.model.utilities;

import java.awt.Image;

import main.model.infinite_map.Background;

/**
 * A class that extends Background class and shapes a Background object equipped
 * with a price field
 */
public class PricedBackground extends Background {

	private int price;

	/**
	 * @param name  The Background name
	 * @param image The Background Image to show it
	 * @param price The PricedBackground price
	 */
	public PricedBackground(String name, Image image, int price) {
		super(name, image);
		this.price = price;
	}

	/**
	 * @return the PricedBackground price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Sets the PricedBackground price
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
		PricedBackground other = (PricedBackground) obj;
		return super.equals(other) && this.price == other.getPrice();
	}
}

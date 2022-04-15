package main.utilities;

import java.awt.Image;

/**
 * A class that extends PricedItem class and shapes a Skin object equipped with
 * a price field
 */
public class PricedBackground extends PricedItem {

	/**
	 * @param name  The PricedSkin object name
	 * @param image The PricedSkin object image
	 * @param price The PricedSkin object price
	 */
	public PricedBackground(String name, Image image, int price) {
		super(name, image, price);
	}

}

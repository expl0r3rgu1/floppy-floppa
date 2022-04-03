package main.utilities;

import java.awt.Image;

public class PricedSkin extends Skin {
	private int price;

	public PricedSkin(String name, Image image, int price) {
		super(name, image);
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}

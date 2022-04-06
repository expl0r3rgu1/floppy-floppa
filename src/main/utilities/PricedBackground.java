package main.utilities;

import java.awt.Image;

public class PricedBackground extends Background{
	private int price;

	public PricedBackground(String name, Image image, int price) {
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

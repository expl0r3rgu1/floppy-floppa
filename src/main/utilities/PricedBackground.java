package main.utilities;

import java.awt.Image;

import main.infinite_map.Background;

public class PricedBackground extends Background implements PricedItem{
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

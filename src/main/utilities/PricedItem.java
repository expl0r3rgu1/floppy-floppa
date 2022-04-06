package main.utilities;

import java.awt.Image;

public class PricedItem {

	protected String name;
	protected Image image;
	protected int price;

	public PricedItem(String name, Image image, int price) {
		this.name = name;
		this.image = image;
		this.price = price;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}

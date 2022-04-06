package main.utilities;

import java.awt.Image;


public class PricedBackground extends PricedItem {
	
	private String name;
	private Image image;

	public PricedBackground(String name, Image image, int price) {
		super(price);
		this.name = name;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public int getPrice() {
		return this.price;
	}

	@Override
	public void setPrice(int price) {
		this.price = price;
	}
	
}

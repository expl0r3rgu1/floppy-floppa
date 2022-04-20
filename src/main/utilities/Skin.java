package main.utilities;

import java.awt.Image;

public class Skin {
	private String name;
	private Image image;
	private int width;
	private int height;

	public Skin(String name, Image image, int width, int height) {
		this.name = name;
		this.image = image;
		this.width = width;
		this.height = height;
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

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public boolean equals(Object obj) {
		Skin other = (Skin) obj;

		return this.name.equals(other.getName()) && this.image.equals(other.getImage())
				&& this.width == other.getWidth() && this.height == other.getHeight();
	}
}

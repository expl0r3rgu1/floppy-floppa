package main.utilities;

import java.awt.Image;

/**
 * A class that represents the entity appearance on the map
 */
public class Skin {
	private String name;
	private Image image;
	private int width;
	private int height;

	/**
	 * @param name   the name of the skin
	 * @param image  the image of the skin
	 * @param width  the width of the skin
	 * @param height the height of the skin
	 */
	public Skin(String name, Image image, int width, int height) {
		this.name = name;
		this.image = image;
		this.width = width;
		this.height = height;
	}

	/**
	 * Getter of the skin name
	 * 
	 * @return the name of the skin
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter of the skin name
	 * 
	 * @param name the new name of the skin
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter of the skin image
	 * 
	 * @return the image of the skin
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Setter of the skin image
	 * 
	 * @param image the new image of the skin
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * Getter of the skin width
	 * 
	 * @return the width of the skin
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Setter of the skin width
	 * 
	 * @param width the new width of the skin
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Getter of the skin height
	 * 
	 * @return the height of the skin
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Setter of the skin height
	 * 
	 * @param height the new height of the skin
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object obj) {
		Skin other = (Skin) obj;

		return this.name.equals(other.getName()) && this.image.equals(other.getImage())
				&& this.width == other.getWidth() && this.height == other.getHeight();
	}
}

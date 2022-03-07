package main.infinite_map;

import java.awt.Graphics2D;
import java.awt.Image;

import main.utilities.Movable;
import main.utilities.Position;

public class Background extends Movable {
	private String name;
	private Image image;

	public Background(String name, Image image, Position position) {
		super(position);
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
	public void animate(Graphics2D canvas) {
		
	}

}

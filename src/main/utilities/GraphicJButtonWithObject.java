package main.utilities;

import java.awt.Color;

public class GraphicJButtonWithObject extends GraphicJButton {

	private static final long serialVersionUID = 446458769883620817L;
	private Object object;

	public GraphicJButtonWithObject(String name, Color backgroundColor, Color borderColor, String font,
			int fontAppearance, Object object) {
		super(name, backgroundColor, borderColor, font, fontAppearance);
		this.object = object;
	}

	public Object getObject() {
		return this.object;
	}

}

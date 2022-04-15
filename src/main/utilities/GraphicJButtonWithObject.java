package main.utilities;

import java.awt.Color;

/**
 * A class that extends GraphicJButton adding an Object field for the
 * constructor
 */
public class GraphicJButtonWithObject extends GraphicJButton {

	private static final long serialVersionUID = 446458769883620817L;
	private Object object;

	/**
	 * @param name            String that will appear on the Button
	 * @param backgroundColor Button background color
	 * @param borderColor     Button borders color
	 * @param font            Font for the String that is shown on the Button
	 * @param fontAppearance  how the font will appear (bold, italic, plain...)
	 * @param object          Object associated with the Button
	 */
	public GraphicJButtonWithObject(String name, Color backgroundColor, Color borderColor, String font,
			int fontAppearance, Object object) {
		super(name, backgroundColor, borderColor, font, fontAppearance);
		this.object = object;
	}

	/**
	 * @return the object associated with the Button
	 */
	public Object getObject() {
		return this.object;
	}

}

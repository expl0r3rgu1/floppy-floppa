package main.utilities;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class generateJButton extends JButton {

	private static final long serialVersionUID = 6442195154436684850L;

	/**
	 * generateButton is a method that creates a JButton and it sets its font and
	 * color, for its background and corner
	 * 
	 * @param name               - The text of the JButton
	 * @param actionListenerName - The string to pass to the adHocActionListener
	 *                           method
	 * @param font               - The name of the font needed by the text of the
	 *                           JButton
	 */
	public generateJButton(String name, String font, int fontDimension, String ColorBackground, String ColorBorder) {
		super(name);
		this.setFont(CommonMethods.getFontResource(font)
				.deriveFont((float) Constants.SCREEN_SIZE.getWidth() / fontDimension));
		this.setBackground(Color.decode(ColorBackground));
		this.setBorder(BorderFactory.createLineBorder(Color.decode(ColorBorder), 4, true));

	}
}

package main.model.utilities;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * A class that extends JButton adding graphic features for the button
 */
public class GraphicJButton extends JButton {

	private static final long serialVersionUID = 3550487230752126610L;

	/**
	 * @param name            String that will appear on the Button
	 * @param backgroundColor Button background color
	 * @param borderColor     Button borders color
	 * @param font            Font for the String that is shown on the Button
	 * @param fontAppearance  how the font will appear (bold, italic, plain...)
	 */
	public GraphicJButton(String name, Color backgroundColor, Color borderColor, String font, int fontAppearance) {
		super(name);
		this.setBackground(backgroundColor);
		this.setBorder(BorderFactory.createLineBorder(borderColor, 4, true));
		this.setFont(new Font(font, fontAppearance, (int) Constants.SCREEN_SIZE.getWidth() / 70));
	}

	/**
	 * @param name            - text of the JButton
	 * @param font            - name of the font needed by the text of the JButton
	 * @param fontDimension   - dimension of the font
	 * @param ColorBackground - background color of the JButton
	 * @param ColorBorder     - border color of the JButton
	 */
	public GraphicJButton(String name, String font, int fontDimension, String ColorBackground, String ColorBorder) {
		super(name);
		this.setFont(CommonMethods.getFontResource(font)
				.deriveFont((float) Constants.SCREEN_SIZE.getWidth() / fontDimension));
		this.setBackground(Color.decode(ColorBackground));
		this.setBorder(BorderFactory.createLineBorder(Color.decode(ColorBorder), 4, true));

	}

}

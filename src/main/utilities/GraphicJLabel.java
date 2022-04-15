package main.utilities;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * A class that extends JLabel adding graphic features for the label
 */
public class GraphicJLabel extends JLabel {

	private static final long serialVersionUID = 3550487230752126610L;

	/**
	 * @param name            String that will appear in the Label
	 * @param backgroundColor Label background color
	 * @param borderColor     Label borders color
	 * @param font            Font for the String that is shown in the Label
	 * @param fontAppearance  how the font will appear (bold, italic, plain...)
	 */
	public GraphicJLabel(String name, Color backgroundColor, Color borderColor, String font, int fontAppearance) {
		super(name, CENTER);
		this.setOpaque(true);
		this.setBackground(backgroundColor);
		this.setBorder(BorderFactory.createLineBorder(borderColor, 4, true));
		this.setFont(new Font(font, fontAppearance, (int) Constants.SCREEN_SIZE.getWidth() / 70));
	}

	/**
	 * @param name            String that will appear in the Label
	 * @param backgroundColor Label background color
	 * @param borderColor     Label borders color
	 * @param font            Font for the String that is shown in the Label, this
	 *                        font is not a Java standard font but is taken from the
	 *                        resources package
	 * @param fontSize        Size of the Label font
	 */
	public GraphicJLabel(String name, Color backgroundColor, Color borderColor, String font, float fontSize) {
		super(name, CENTER);
		this.setOpaque(true);
		this.setBackground(backgroundColor);
		this.setBorder(BorderFactory.createLineBorder(borderColor, 4, true));
		this.setFont(CommonMethods.getFontResource(font).deriveFont(fontSize));
	}
}

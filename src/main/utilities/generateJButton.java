package main.utilities;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class generateJButton extends JButton{
	
	private static final long serialVersionUID = 6442195154436684850L;

	public generateJButton(String name, String font, int fontDimension, String ColorBackground, String ColorBorder) {
		super(name);
		this.setFont(CommonMethods.getFontResource(font).deriveFont((float) Constants.SCREEN_SIZE.getWidth() / fontDimension));
		this.setBackground(Color.decode(ColorBackground));
		this.setBorder(BorderFactory.createLineBorder(Color.decode(ColorBorder), 4, true));

	}
}

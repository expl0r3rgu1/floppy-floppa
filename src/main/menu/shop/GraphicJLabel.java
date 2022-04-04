package main.menu.shop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class GraphicJLabel extends JLabel {

	private static final long serialVersionUID = 3550487230752126610L;
	private static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();

	public GraphicJLabel(String name, Color backgroundColor, Color borderColor, String font, int fontAppearance) {
		super(name, CENTER);
		this.setOpaque(true);
		this.setBackground(backgroundColor);
		this.setBorder(BorderFactory.createLineBorder(borderColor, 4, true));
		this.setFont(new Font(font, fontAppearance, (int) SIZE.getWidth() / 70));
	}

}

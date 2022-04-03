package main.menu.shop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class GraphicJButton extends JButton {

	private static final long serialVersionUID = 3550487230752126610L;
	private static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private Object object;

	public GraphicJButton(String name, Object object, Color backgroundColor, Color borderColor, String font,
			int fontAppearance) {
		super(name);
		this.object = object;
		this.setBackground(backgroundColor);
		this.setBorder(BorderFactory.createLineBorder(borderColor, 4, true));
		this.setFont(new Font(font, fontAppearance, (int) SIZE.getWidth() / 70));
	}

	public Object getObject() {
		return this.object;
	}

}

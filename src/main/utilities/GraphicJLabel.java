package main.utilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class GraphicJLabel extends JLabel{

	private static final long serialVersionUID = 3550487230752126610L;
	
	public GraphicJLabel(String name ,Color backgroundColor, Color borderColor, String font, int fontAppearance) {
		super(name, CENTER);
		this.setOpaque(true);
		this.setBackground(backgroundColor);
		this.setBorder(BorderFactory.createLineBorder(borderColor, 4, true));
		this.setFont(new Font(font, fontAppearance, (int) Constants.SCREEN_SIZE.getWidth() / 70));
	}
	
	public GraphicJLabel(String name ,Color backgroundColor, Color borderColor, String font, float fontSize) {
		super(name, CENTER);
		this.setOpaque(true);
		this.setBackground(backgroundColor);
		this.setBorder(BorderFactory.createLineBorder(borderColor, 4, true));
		try {
			this.setFont(Font.createFont(Font.TRUETYPE_FONT, new File(getClass().getResource("/resources/fonts/" + font).getFile())).deriveFont(fontSize));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}

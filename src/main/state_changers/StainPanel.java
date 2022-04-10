package main.state_changers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

class StainPanel extends JPanel{
	
	public static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private Image image;
	
	public StainPanel(String imageName) {
		
		this.setLayout(null);
		
		try {
			image = ImageIO.read(getClass().getResource("/resources/images/"+imageName+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.setPreferredSize(SIZE);
		this.setOpaque(false);
		
		this.setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D canvas = (Graphics2D) g;
		canvas.drawImage(image, 0, 0, (int) SIZE.getWidth(), (int) SIZE.getHeight(), null);
	}
	
}

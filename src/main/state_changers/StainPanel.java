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
	
	public StainPanel(Image image) {
		
		this.setLayout(null);
		
		try {
			image = ImageIO.read(getClass().getResource("/resurces/images/"+image+".png"));
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
		canvas.drawImage(scale(image), 0, 0, (int) SIZE.getWidth(), (int) SIZE.getHeight(), null);
	}
	
	private Image scale(Image image) {
		return image.getScaledInstance((int) SIZE.getWidth(), (int) SIZE.getHeight(), Image.SCALE_DEFAULT);
	}
}

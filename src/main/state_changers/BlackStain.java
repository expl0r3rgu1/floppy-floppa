package main.state_changers;

import main.utilities.Skin;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BlackStain extends Malus implements ActionListener{
	
	//Dirty Screen
	public static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	StainPanel stain = new StainPanel();
	Timer timer = new Timer(300, this);
	
	public BlackStain(Position position, Skin skin) {
		super(position, skin);
	}

	public void timeStains() {
		timer.start();
		play.add(stain);
	}
	
	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(), position.getX(), position.getY(), SIZE.getWidth() * 3 / 100, SIZE.getWidth() * 3 / 100, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		play.remove(stain);
	}
	
}

class StainPanel extends JPanel{
	
	public static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private Image blackstains;
	
	public StainPanel() {
		
		this.setLayout(null);

		//this.setBackground(Color.red);
		try {
			blackstains = ImageIO.read(getClass().getResource("/test/blackstains.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.setPreferredSize(SIZE);
		this.setBackground(new Color(0,0,0,0));
		this.setOpaque(false);
		
		this.setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D canvas = (Graphics2D) g;
		canvas.drawImage(scale(blackstains), 0, 0, (int) SIZE.getWidth(), (int) SIZE.getHeight(), null);
	}
	
	private Image scale(Image image) {
		return image.getScaledInstance((int) SIZE.getWidth(), (int) SIZE.getHeight(), Image.SCALE_DEFAULT);
	}
}

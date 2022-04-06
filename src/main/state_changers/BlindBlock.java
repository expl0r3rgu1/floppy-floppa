package main.state_changers;

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
import main.utilities.Skin;

public class BlindBlock extends Malus implements ActionListener{
	
	//Black Block
	public static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	StainPanel stain = new StainPanel();
	Timer timer = new Timer(300, this);
	
	public BlindBlock(Position position, Skin skin) {
		super(position, skin);
	}

	public Object changeState() {
		timer.start();
		play.add(stain);
		
		return null;
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
	private Image blindBlock;
	
	public StainPanel() {
		
		this.setLayout(null);

		//this.setBackground(Color.red);
		try {
			blindBlock = ImageIO.read(getClass().getResource("/test/BlindBlock.png"));
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
		canvas.drawImage(blindBlock, (int) (SIZE.getWidth()-(SIZE.getWidth()/4)), 0, (int) SIZE.getWidth()/4, (int) SIZE.getHeight(), null);
	}
}

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
	private String blackstains;
	private StainPanel stain;
	Timer timer = new Timer(300, this);
	
	public BlackStain(Position position, Skin skin) {
		super(position, skin);
		blackstains = "blackstains";
		stain = new StainPanel(blackstains);
	}

	public Object changeState() {
		timer.start();
		play.add(stain);
		
		return null;
	}
	
	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(), getPosition().getX(), getPosition().getY(), SIZE.getWidth() * 3 / 100, SIZE.getWidth() * 3 / 100, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		play.remove(stain);
	}
	
}

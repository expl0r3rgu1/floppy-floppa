package main.state_changers;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;

import main.utilities.Skin;

public class CoinsReducer extends Malus{
	
	//Lose coins
	public static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	ArrayList<Integer> malus = new ArrayList<Integer>(Arrays.asList(5, 10, 20, 30, 40, 50));
	
	public CoinsReducer(Position position, Skin skin) {
		super(position, skin);
	}

	public Integer changeState() {
		return malus.get((int) (Math.random()*(malus.size())));
	}
	
	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(), position.getX(), position.getY(), SIZE.getWidth() * 3 / 100, SIZE.getWidth() * 3 / 100, null);
	}
	
}
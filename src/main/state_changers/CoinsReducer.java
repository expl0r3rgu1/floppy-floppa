package main.state_changers;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;

import main.utilities.Constants;
import main.utilities.Position;
import main.utilities.Skin;

public class CoinsReducer extends Malus {

	// Lose coins
	ArrayList<Integer> malus = new ArrayList<Integer>(Arrays.asList(5, 10, 20, 30, 40, 50));

	public CoinsReducer(Position position, Skin skin) {
		super(position, skin);
	}

	public Integer changeState() {
		return malus.get((int) (Math.random() * (malus.size())));
	}

	private void updatePositionX() {
		setPosition(new Position(getPosition().getX() - 1, getPosition().getY()));
	}
	
	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(), getPosition().getX(), getPosition().getY(),
				(int) Constants.SCREEN_SIZE.getWidth() * 3 / 100, (int) Constants.SCREEN_SIZE.getWidth() * 3 / 100,
				null);
		
		this.updatePositionX();
	}

}
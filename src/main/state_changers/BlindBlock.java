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

import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Position;
import main.utilities.Skin;

public class BlindBlock extends Malus implements ActionListener {

	// Black Block
	private String blindBlock;
	private StainPanel stain;
	private Timer timer = new Timer(Constants.CHANGED_STATE_TIME, this);

	public BlindBlock(Position position, Skin skin) {
		super(position, skin);
		blindBlock = "blindBlock";
		stain = new StainPanel(blindBlock);
	}

	public Object changeState() {
		timer.start();
		play.add(stain);

		return null;
	}

	private void updatePositionX() {
		setPosition(new Position(getPosition().getX() - 1, getPosition().getY()));
	}

	public void animate(Graphics2D canvas) {
		canvas.drawImage(getSkin().getImage(), getPosition().getX(), getPosition().getY(),
				CommonMethods.getPixelsFromPercentage(3), CommonMethods.getPixelsFromPercentage(3), null);

		this.updatePositionX();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		play.remove(stain);
	}

}

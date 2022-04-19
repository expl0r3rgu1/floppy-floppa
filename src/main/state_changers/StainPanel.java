package main.state_changers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import main.utilities.CommonMethods;
import main.utilities.Constants;

/**
 * StainPanel is a class that makes appear a JPanel on the screen, used by
 * BlackStain and BlindBlock
 */
public class StainPanel extends JPanel {

	private static final long serialVersionUID = 9048749950295388684L;
	private Image image;

	/**
	 * @param imageName
	 */
	public StainPanel(String imageName) {

		this.setLayout(null);

		CommonMethods.getImageResource(imageName);

		this.setPreferredSize(Constants.SCREEN_SIZE);
		this.setOpaque(false);

		this.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D canvas = (Graphics2D) g;
		canvas.drawImage(image, 0, 0, (int) Constants.SCREEN_SIZE.getWidth(), (int) Constants.SCREEN_SIZE.getHeight(),
				null);
	}

}

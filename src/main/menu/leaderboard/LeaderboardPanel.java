package main.menu.leaderboard;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LeaderboardPanel extends JPanel {
	private static final long serialVersionUID = -2850654943551437120L;
	private Leaderboard leaderboard;
	Image background;

	public LeaderboardPanel() {
		try {
			background = ImageIO.read(getClass().getResource("/resources/images/shopBackground.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D canvas = (Graphics2D) g;

		canvas.drawImage(background, 0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(), null);
	}

	public GridBagConstraints getConstraints(int gridx, int gridy, Insets insets, int ipadx, int ipady, int anchor) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = insets;
		constraints.anchor = anchor;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.ipadx = ipadx;
		constraints.ipady = ipady;

		return constraints;
	}
}

package main.menu.leaderboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

import test.GraphicJButton;

public class LeaderboardPanel extends JPanel {
	private static final long serialVersionUID = -2850654943551437120L;
	private Leaderboard leaderboard;
	private final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

	Image background;
	JLabel title;

	public LeaderboardPanel() {
		this.setPreferredSize(SCREEN_SIZE);
		this.setLayout(new GridBagLayout());

		leaderboard = new Leaderboard();

		background = getImageResource("menuBackground.png");

		// TITLE
		addTitle();

		// SCROLLABLE PANE
		addScrollablePane();

		// MENU BUTTON
		addMenuButton();
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

	public Image getImageResource(String imageName) {
		try {
			return ImageIO.read(getClass().getResource("/resources/images/" + imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Font getFontResource(String fontName) {
		try {
			return Font.createFont(Font.TRUETYPE_FONT,
					new File(getClass().getResource("/resources/fonts/" + fontName).getFile()));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	// GRAPHIC COMPONENTS

	private void addTitle() {
		title = new JLabel("LEADERBOARD", SwingConstants.CENTER);
		title.setFont(getFontResource("pixel.TTF").deriveFont(50f));

		this.add(title, getConstraints(0, 0, new Insets(0, 0, (int) (SCREEN_SIZE.getWidth() * 0.02), 0), 0, 0,
				GridBagConstraints.PAGE_START));
	}

	private JPanel getScorePanel() {
		JPanel scorePanel = new JPanel(new GridLayout(leaderboard.getLeaderboard().size(), 1));
		scorePanel.setOpaque(false);

		int placeNumber = 1;
		for (var player : leaderboard.getLeaderboard()) {
			JLabel score = new JLabel(
					placeNumber + " - " + player.getNickname() + " - " + player.getPersonalBest() + " m",
					SwingConstants.CENTER);
			score.setFont(getFontResource("fipps.otf").deriveFont(20f));
			score.setForeground(Color.lightGray);
			scorePanel.add(score);

			placeNumber++;
		}

		return scorePanel;
	}

	private void addScrollablePane() {
		JScrollPane scrollablePane = new JScrollPane(getScorePanel());
		scrollablePane.setOpaque(false);
		scrollablePane.getViewport().setBackground(new Color(0, 0, 0, .5f));
		scrollablePane.getViewport().setOpaque(true);
		scrollablePane.setBorder(BorderFactory.createEmptyBorder());

		// SCROLLBAR CUSTOMIZATION
		scrollablePane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				super.configureScrollBarColors();

				this.thumbColor = Color.darkGray;
				this.trackColor = Color.gray;
			}
		});

		this.add(scrollablePane, getConstraints(0, 1, new Insets(0, 0, 0, 0), (int) (SCREEN_SIZE.getWidth() * 0.7),
				(int) (SCREEN_SIZE.getHeight() * 0.7), GridBagConstraints.CENTER));
	}

	private void addMenuButton() {
		GraphicJButton menuButton = new GraphicJButton("MENU", Color.decode("#FFDD62"), Color.decode("#FF971A"),
				"Arial", Font.PLAIN);

		menuButton.addActionListener(e -> {
			// TODO: Call MainMenu back to main menu method
		});

		this.add(menuButton,
				getConstraints(0, 2,
						new Insets((int) (SCREEN_SIZE.getHeight() * 0.03), (int) (SCREEN_SIZE.getWidth() * 0.3), 0,
								(int) (SCREEN_SIZE.getWidth() * 0.3)),
						0, (int) (SCREEN_SIZE.getWidth() * 0.02), GridBagConstraints.PAGE_END));
	}
}

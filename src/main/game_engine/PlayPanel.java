package main.game_engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import main.character.Character;
import main.infinite_map.Map;
import main.infinite_map.ScrollingBackground;
import main.obstacles.FixedObstacle;
import main.obstacles.MovingObstacle;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.GBCSimplified;
import main.utilities.GameSettings;
import main.utilities.Position;
import main.utilities.Skin;

public class PlayPanel extends JPanel implements ActionListener, KeyListener {
	private Timer refreshRate;
	private final Map map;
	private final NicknamePanel nicknamePanel;
	private final Character character;

	public PlayPanel(GameSettings gameSettings) {
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(Constants.SCREEN_SIZE);

		map = new Map(new ScrollingBackground("Default", "Classic"),
				new FixedObstacle(null, new Skin("pipe", CommonMethods.getImageResource("pipe"))),
				List.of(new MovingObstacle(null, new Skin("Bingus", CommonMethods.getImageResource("Bingus"))),
						new MovingObstacle(null, new Skin("Walter", CommonMethods.getImageResource("Walter")))));

		refreshRate = new Timer(1000 / Constants.SPEED, this);

		character = new Character(
				new Position((int) Constants.SCREEN_SIZE.getHeight() / 2, CommonMethods.getPixelsFromPercentage(30)),
				new Skin("Floppa", CommonMethods.getImageResource("Floppa")));

		this.nicknamePanel = new NicknamePanel(this, gameSettings);
		this.add(nicknamePanel, new GBCSimplified(0, 0, 0, 0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER));
	}

	public void dismissNicknamePanel() {
		this.remove(nicknamePanel);
		refreshRate.start();
		map.startTimer();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D canvas = (Graphics2D) g;
		map.animate(canvas);
		character.animate(canvas);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.character.jump();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}

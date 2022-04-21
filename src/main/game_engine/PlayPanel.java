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
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import main.character.Character;
import main.infinite_map.Map;
import main.infinite_map.ScrollingBackground;
import main.menu.EOGMenuGUI;
import main.menu.MainMenu;
import main.obstacles.FixedObstacle;
import main.obstacles.MovingObstacle;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.GBCSimplified;
import main.utilities.GameSettings;
import main.utilities.Position;
import main.utilities.Skin;
import main.utilities.Constants.PANEL;

public class PlayPanel extends JPanel implements ActionListener, KeyListener {
	private static final long serialVersionUID = 4739973632599419506L;
	private Timer refreshRate;
	private final MainMenu mainMenu;
	private final Map map;
	private final NicknamePanel nicknamePanel;
	private final Character character;
	private Timestamp gameStart;
	private Timestamp gameEnd;

	public PlayPanel(MainMenu mainMenu, GameSettings gameSettings) {
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(Constants.SCREEN_SIZE);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();

		this.mainMenu = mainMenu;

		map = new Map(gameSettings.getScrollingBackground(),
				new FixedObstacle(null, new Skin("pipe", CommonMethods.getImageResource("pipe"))),
				List.of(new MovingObstacle(null, new Skin("Bingus", CommonMethods.getImageResource("Bingus"))),
						new MovingObstacle(null, new Skin("Walter", CommonMethods.getImageResource("Walter")))));

		refreshRate = new Timer(1000 / Constants.SPEED, this);

		character = new Character(
				new Position((int) Constants.SCREEN_SIZE.getHeight() / 2, CommonMethods.getPixelsFromPercentage(30)),
				gameSettings.getSkin());

		this.nicknamePanel = new NicknamePanel(this, gameSettings);
		this.add(nicknamePanel, new GBCSimplified(0, 0, 0, 0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER));
	}

	public void dismissNicknamePanel() {
		this.remove(nicknamePanel);
		refreshRate.start();
		map.startTimer();

		gameStart = new Timestamp(System.currentTimeMillis());
	}

	private int getMetersTraveled() {
		return (int) ((gameEnd.getTime() - gameStart.getTime()) / 1000);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D canvas = (Graphics2D) g;
		map.animate(canvas);
		character.animate(canvas);

		character.collideFixedObstacle(map.getPaintedFixedObstacles());
		character.collideMovingObstacle(map.getPaintedMovingObstacles());
		character.collideMalus(map.getPaintedMalus());
		character.collideBooster(map.getPaintedBoosters());
		character.collideBorders();

		if (character.isDead()) {
			refreshRate.stop();

			gameEnd = new Timestamp(System.currentTimeMillis());

			mainMenu.add(PANEL.EOGMENU.name(), new EOGMenuGUI(mainMenu, getMetersTraveled(), 0, 0));
			mainMenu.showCard(PANEL.EOGMENU);
		}
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

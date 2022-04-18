package main.utilities;

import main.infinite_map.ScrollingBackground;
import main.menu.leaderboard.Player;

public class GameSettings {
	
	public static final int dimesion = (Constants.SCREEN_SIZE.getHeight()) / 12;
	private ScrollingBackground scrollingBackground;
	private Skin skin;
	private Player player;

	public GameSettings() {
		this.scrollingBackground = new ScrollingBackground("Default", CommonMethods.getImageResource("Classic"));
		this.skin = new Skin("Floppa", CommonMethods.getImageResource("Floppa"), dimesion, dimesion);
	}

	public ScrollingBackground getScrollingBackground() {
		return scrollingBackground;
	}

	public void setScrollingBackground(ScrollingBackground scrollingBackground) {
		this.scrollingBackground = scrollingBackground;
	}

	public Skin getSkin() {
		return skin;
	}

	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
package main.model.utilities;

import main.model.infinite_map.ScrollingBackground;
import main.model.leaderboard.Player;

/**
 * GameSettings is the class that sets the Skin for the Character and the
 * Background for ScrollingBackground
 */
public class GameSettings {

	private ScrollingBackground scrollingBackground;
	private Skin skin;
	private Player player;

	public GameSettings() {
		this.scrollingBackground = new ScrollingBackground("Default", CommonMethods.getImageResource("Classic"));
		this.skin = new Skin("Floppa", CommonMethods.getImageResource("Floppa"), Constants.SKIN_DIMENSION,
				Constants.SKIN_DIMENSION);
	}

	/**
	 * getter for ScrollingBackground
	 * 
	 * @return scrollingBackground
	 */
	public ScrollingBackground getScrollingBackground() {
		return scrollingBackground;
	}

	/**
	 * setter for ScrollingBackground
	 * 
	 * @param scrollingBackground - the background used during the game
	 */
	public void setScrollingBackground(ScrollingBackground scrollingBackground) {
		this.scrollingBackground = scrollingBackground;
	}

	/**
	 * getter for Skin
	 * 
	 * @return skin
	 */
	public Skin getSkin() {
		return skin;
	}

	/**
	 * setter for Skin
	 * 
	 * @param skin - the skin used for the character
	 */
	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	/**
	 * getter for Player
	 * 
	 * @return player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * setter for Player
	 * 
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

}
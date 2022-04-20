package main.utilities;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Constants {
	public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int MOVING_FACTOR = (int) Math.floor(SCREEN_SIZE.getWidth() / (double) 1000) * 2;
	public static final double SPACE_BETWEEN_PIPES = (Toolkit.getDefaultToolkit().getScreenSize().getHeight()) / 3;
	public static final int SPEED = 60;
	public static final double FIXED_OBSTACLE_SPEED = 0.002;
	public static final double MOVING_OBSTACLE_SPEED = 0.0005;
	public static final String SAVINGS_FILE_PATH = "savings";
	public static final String SAVINGS_FILE_START_CONTENT = "0\n0,0,0,0,0\n0,0,0,0,0";
	public static final int CHANGED_STATE_TIME = 3000;
	public static final int CHARACTER_ANGLE_DEGREES = 15;
	public static final int LEADERBOARD_FILE_LINE_START = 3;
	public static final int CHANGE_DIRECTION_TIMEOUT = 300;

	public static enum PANEL {
		MENU, PLAY, LEADERBOARD, SHOP, TUTORIAL, EOGMENU
	}
}

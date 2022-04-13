package main.utilities;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Constants {
	public static final int SPEED = 60;
	public static final double FIXED_OBSTACLE_SPEED = 0.004; // Every 2.375 seconds a fixedObstacle spawns
	public static final double MOVING_OBSTACLE_SPEED = 0.001; // Every 10 seconds a movingObstacle spawns
	public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static final String SAVINGS_FILE_PATH = "savings";
	public static final int CHANGED_STATE_TIME = 3000;
	public static final int CHARACTER_ANGLE_DEGREES = 15;
	public static final int LEADERBOARD_FILE_LINE_START = 3;

	public static enum PANEL {
		MENU, LEADERBOARD, SHOP, TUTORIAL, EOGMENU
	}
}

package main.utilities;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import main.obstacles.FixedObstacle;
import main.obstacles.MovingObstacle;
import main.obstacles.ObstacleFactory;
import main.obstacles.ObstacleFactoryImpl;
import main.state_changers.BlackStain;
import main.state_changers.BlindBlock;
import main.state_changers.Booster;
import main.state_changers.CoinsIncrement;
import main.state_changers.CoinsReducer;
import main.state_changers.Immortality;
import main.state_changers.Malus;
import main.state_changers.StateChangerFactory;
import main.state_changers.StateChangerFactoryImpl;

public class Constants {
	public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int SPACE_BETWEEN_PIPES = (int) (SCREEN_SIZE.getHeight() / 3);
	public static final int SKIN_DIMENSION = (int) (SCREEN_SIZE.getHeight() / 12);
	public static final int MOVING_FACTOR = (int) Math.floor(SCREEN_SIZE.getWidth() / (double) 1000) * 2;
	public static final int SPEED = 60;
	public static final double FIXED_OBSTACLE_SPEED = 0.0025;
	public static final double MOVING_OBSTACLE_SPEED = 0.0015;
	public static final double STATE_CHANGER_SPEED = 0.0007;
	public static final String SAVINGS_FILE_PATH = "savings";
	public static final String SAVINGS_FILE_START_CONTENT = "0\n1,0,0,0,0\n1,0,0,0,0";
	public static final int CHANGED_STATE_TIME = 3000;
	public static final int CHARACTER_ANGLE_DEGREES = 15;
	public static final int LEADERBOARD_FILE_LINE_START = 3;
	public static final int CHANGE_DIRECTION_TIMEOUT = 60000;
	public static final int CHARACTER_JUMP_TIMEOUT = 30000;
	public static final int IMMORTALITY_TIMEOUT = 10000;

	public static final ObstacleFactory OBSTACLE_FACTORY = new ObstacleFactoryImpl();
	public static final StateChangerFactory STATE_CHANGER_FACTORY = new StateChangerFactoryImpl();

	public static final FixedObstacle FIXED_OBSTACLE = OBSTACLE_FACTORY.fixedObstacleFactory(null, new Skin("pipe",
			CommonMethods.getImageResource("pipe"), CommonMethods.getPixelsFromPercentageWidth(10), 0));

	public static final List<MovingObstacle> MOVING_OBSTACLES = List.of(
			OBSTACLE_FACTORY.movingObstacleFactory(null,
					new Skin("Bingus",
							CommonMethods.getImageResource("Bingus"), CommonMethods.getPixelsFromPercentageWidth(8),
							CommonMethods.getPixelsFromPercentageHeight(8))),
			OBSTACLE_FACTORY.movingObstacleFactory(null,
					new Skin("Walter",
							CommonMethods.getImageResource("Walter"), CommonMethods.getPixelsFromPercentageWidth(8),
							CommonMethods.getPixelsFromPercentageHeight(8))));

	public static final List<Malus> MALUS = List.of(
			new BlackStain(null, new Skin("blackstains", CommonMethods.getImageResource("blackstains"),
					CommonMethods.getPixelsFromPercentageWidth(8), CommonMethods.getPixelsFromPercentageHeight(8))),
			new BlindBlock(null, new Skin("blindblock", CommonMethods.getImageResource("blindblock"),
					CommonMethods.getPixelsFromPercentageWidth(8), CommonMethods.getPixelsFromPercentageHeight(8))),
			new CoinsReducer(null, new Skin("coinsreducer", CommonMethods.getImageResource("coinsreducer"),
					CommonMethods.getPixelsFromPercentageWidth(8), CommonMethods.getPixelsFromPercentageHeight(8))));

	public static final List<Booster> BOOSTERS = List.of(
			new Immortality(null, new Skin("immortality", CommonMethods.getImageResource("immortality"),
					CommonMethods.getPixelsFromPercentageWidth(8), CommonMethods.getPixelsFromPercentageHeight(8))),
			new CoinsIncrement(null, new Skin("coinsincrement", CommonMethods.getImageResource("coinsincrement"),
					CommonMethods.getPixelsFromPercentageWidth(8), CommonMethods.getPixelsFromPercentageHeight(8))));

	public static enum PANEL {
		MENU, PLAY, LEADERBOARD, SHOP, TUTORIAL, EOGMENU
	}
}

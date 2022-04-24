package main.model.utilities;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import main.model.obstacles.FixedObstacle;
import main.model.obstacles.MovingObstacle;
import main.model.obstacles.ObstacleFactory;
import main.model.obstacles.ObstacleFactoryImpl;
import main.model.state_changers.BlackStain;
import main.model.state_changers.BlindBlock;
import main.model.state_changers.Booster;
import main.model.state_changers.CoinsIncrement;
import main.model.state_changers.CoinsReducer;
import main.model.state_changers.Immortality;
import main.model.state_changers.Malus;
import main.model.state_changers.StateChangerFactory;
import main.model.state_changers.StateChangerFactoryImpl;

/**
 * A class that contains all the constants used
 */
public class Constants {
	/**
	 * The dimension of the screen
	 */
	public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * The space between the upper pipe and the lower pipe of a fixed obstacle
	 */
	public static final int SPACE_BETWEEN_PIPES = (int) (SCREEN_SIZE.getHeight() / 3);
	/**
	 * The dimension of the skin
	 */
	public static final int SKIN_DIMENSION = CommonMethods.getPixelsFromPercentageHeight(8);
	/**
	 * The dimension of the state changers
	 */
	public static final int STATE_CHANGER_DIMENSION = CommonMethods.getPixelsFromPercentageHeight(6);
	/**
	 * The factor that regulates the movement of every entity that moves
	 */
	public static final int MOVING_FACTOR = (int) Math.floor(SCREEN_SIZE.getWidth() / (double) 1000) * 2;
	/**
	 * The frames per second, every entity velocity is based on this constant
	 */
	public static final int SPEED = 60;
	/**
	 * The velocity of the spawn of a fixed obstacle, the bigger the faster
	 */
	public static final double FIXED_OBSTACLE_SPEED = 0.0025;
	/**
	 * The velocity of the spawn of a moving obstacle, the bigger the faster
	 */
	public static final double MOVING_OBSTACLE_SPEED = 0.0015;
	/**
	 * The velocity of the spawn of a state changer, the bigger the faster
	 */
	public static final double STATE_CHANGER_SPEED = 0.0007;
	/**
	 * The path of the file in which are stored the savings
	 */
	public static final String SAVINGS_FILE_PATH = "savings";
	/**
	 * The initial content of the savings file when generated
	 */
	public static final String SAVINGS_FILE_START_CONTENT = "0\n1,0,0,0,0\n1,0,0,0,0";
	/**
	 * For how long the effect of a state changer that use a timer is applied
	 */
	public static final int CHANGED_STATE_TIME = 3000;
	/**
	 * The degrees of the angle the skin of the character is rotated when jumping or
	 * falling
	 */
	public static final int CHARACTER_ANGLE_DEGREES = 15;
	/**
	 * The line of the savings file in which the leaderboard starts
	 */
	public static final int LEADERBOARD_FILE_LINE_START = 3;
	/**
	 * After how long the moving obstacle change direction, the lower the faster
	 */
	public static final int CHANGE_DIRECTION_TIMEOUT = 60000;
	/**
	 * After how long the character stops to going up after a jump, the lower the
	 * less space the character goes up
	 */
	public static final int CHARACTER_JUMP_TIMEOUT = 30000;
	/**
	 * For how long the character is immortal after the immortality Booster
	 * changeState is applied
	 */
	public static final int IMMORTALITY_TIMEOUT = 10000;
	
	public static final Random RANDOM = new Random();
	
	/**
	 * This Array contains the names of all Skin objects and Background objects used  
	 */
	public static final List<String> SKIN_BACKGROUND_NAMES = new ArrayList<>(Arrays.asList("Floppa", "Sogga", "Capibara", "Quokka",
			"Buding", "Classic", "Beach", "Woods", "Space", "NeonCity"));

	/**
	 * Used to instantiate ObstacleFactoryImpl
	 */
	public static final ObstacleFactory OBSTACLE_FACTORY = new ObstacleFactoryImpl();
	/**
	 * Used to instantiate StateChangerFactoryImpl
	 */
	public static final StateChangerFactory STATE_CHANGER_FACTORY = new StateChangerFactoryImpl();

	/**
	 * Used to instantiate a FixedObstacle
	 */
	public static final FixedObstacle FIXED_OBSTACLE = OBSTACLE_FACTORY.fixedObstacleFactory(null, new Skin("pipe",
			CommonMethods.getImageResource("pipe"), CommonMethods.getPixelsFromPercentageWidth(10), 0));

	/**
	 * List of the different MovingObstacle of the game
	 */
	public static final List<MovingObstacle> MOVING_OBSTACLES = List.of(
			OBSTACLE_FACTORY.movingObstacleFactory(null, new Skin("Bingus", CommonMethods.getImageResource("Bingus"),
					CommonMethods.getPixelsFromPercentageWidth(8), CommonMethods.getPixelsFromPercentageHeight(8))),
			OBSTACLE_FACTORY.movingObstacleFactory(null, new Skin("Walter", CommonMethods.getImageResource("Walter"),
					CommonMethods.getPixelsFromPercentageWidth(8), CommonMethods.getPixelsFromPercentageHeight(8))));

	/**
	 * List of the different Malus of the game
	 */
	public static final List<Malus> MALUS = List.of(
			new BlackStain(null,
					new Skin("blackstains", CommonMethods.getImageResource("blackstains"), STATE_CHANGER_DIMENSION,
							STATE_CHANGER_DIMENSION)),
			new BlindBlock(null,
					new Skin("blindblock", CommonMethods.getImageResource("blindblock"), STATE_CHANGER_DIMENSION,
							STATE_CHANGER_DIMENSION)),
			new CoinsReducer(null, new Skin("coinsreducer", CommonMethods.getImageResource("coinsreducer"),
					STATE_CHANGER_DIMENSION, STATE_CHANGER_DIMENSION)));

	/**
	 * List of the different Booster of the game
	 */
	public static final List<Booster> BOOSTERS = List.of(
			new Immortality(null,
					new Skin("immortality", CommonMethods.getImageResource("immortality"), STATE_CHANGER_DIMENSION,
							STATE_CHANGER_DIMENSION)),
			new CoinsIncrement(null, new Skin("coinsincrement", CommonMethods.getImageResource("coinsincrement"),
					STATE_CHANGER_DIMENSION, STATE_CHANGER_DIMENSION)));

	/**
	 * The names of the different panels of the game
	 */
	public static enum PANEL {
		MENU, PLAY, LEADERBOARD, SHOP, TUTORIAL, EOGMENU, SELECT
	}
}

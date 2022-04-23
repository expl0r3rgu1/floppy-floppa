package main.character;

import main.obstacles.FixedObstacle;
import main.obstacles.MovingObstacle;
import main.state_changers.Booster;
import main.state_changers.Malus;
import main.utilities.CommonMethods;
import main.utilities.Constants;
import main.utilities.Movable;
import main.utilities.Position;
import main.utilities.Skin;

import static org.junit.Assert.assertTrue;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import org.junit.Test;

/**
 * A class that implements the character, it keeps track of its current status
 * (if it is dead or alive), it updates its position making it go lower due to
 * gravity or upper when performing a jump and it checks its collision with the
 * various entities on the map and with the borders of the map, this class
 * extends the class Movable
 */
public class Character extends Movable {
	private final Skin skin;
	private boolean dead;
	/**
	 * if it is true the character is immune from death, its default value is false
	 */
	public static boolean immortal = false;
	private boolean jumping;
	private final Timer timer;

	/**
	 * @param position the initial spawning position of the character
	 * @param skin     the skin of the character
	 */
	public Character(Position position, Skin skin) {
		super(position);
		this.skin = skin;
		this.dead = false;
		this.jumping = false;
		this.timer = new Timer(Constants.CHARACTER_JUMP_TIMEOUT / Constants.SPEED, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jumping = false;
				timer.stop();
			}

		});
	}

	/**
	 * @return true if the character is dead
	 */
	public boolean isDead() {
		return this.dead;
	}

	/**
	 * Makes the status of the character as dead
	 */
	public void die() {
		this.dead = (Character.immortal) ? false : true;
	}

	/**
	 * Getter of the character skin
	 * 
	 * @return the character skin
	 */
	public Skin getSkin() {
		return this.skin;
	}

	/**
	 * Makes the character jump
	 */
	public void jump() {
		if (!this.jumping) {
			this.timer.start();
			this.jumping = true;
		} else {
			this.timer.restart();
		}
	}

	/**
	 * Checks if the character collides with a fixed obstacle, if it happens it will
	 * change the character status to dead
	 * 
	 * @param fixedObstacleList a list of the fixed obstacles that the character
	 *                          could collide
	 */
	public void collideFixedObstacle(List<FixedObstacle> fixedObstacleList) {
		for (FixedObstacle fixedObstacle : fixedObstacleList) {
			int characterX = this.getPosition().getX();
			int characterWiderX = characterX + this.skin.getWidth();
			int characterY = this.getPosition().getY();
			int characterLowerY = characterY + this.skin.getHeight();
			int obstacleX = fixedObstacle.getPosition().getX();
			int obstacleWiderX = obstacleX + fixedObstacle.getSkin().getWidth();
			int obstacleUpperY = fixedObstacle.getPosition().getY() + (int) Constants.SPACE_BETWEEN_PIPES / 2;
			int obstacleLowerY = fixedObstacle.getPosition().getY() - (int) Constants.SPACE_BETWEEN_PIPES / 2;

			if ((characterX >= obstacleX && characterX <= obstacleWiderX)
					|| (characterWiderX >= obstacleX && characterWiderX <= obstacleWiderX)) {

				if ((characterY >= obstacleUpperY || characterY <= obstacleLowerY)
						|| (characterLowerY >= obstacleUpperY || characterLowerY <= obstacleLowerY)) {

					this.die();
					break;
				}
			}
		}
	}

	/**
	 * Checks if the character collides with a fixed obstacle, if it happens it will
	 * change the character status to dead
	 * 
	 * @param movingObstacleList a list of the moving obstacles that the character
	 *                           could collide
	 */
	public void collideMovingObstacle(List<MovingObstacle> movingObstacleList) {
		for (MovingObstacle movingObstacle : movingObstacleList) {
			int x = movingObstacle.getPosition().getX();
			int y = movingObstacle.getPosition().getY();
			int height = movingObstacle.getSkin().getHeight();
			int width = movingObstacle.getSkin().getWidth();

			if (this.checkCollision(x, y, height, width)) {
				this.die();
				break;
			}
		}
	}

	/**
	 * Checks if the character collides with a malus, if it happens it will apply
	 * the effects of that malus
	 * 
	 * @param malusList a list of the maluses that the character could collide
	 */
	public void collideMalus(List<Malus> malusList) {
		for (Malus malus : malusList) {
			int x = malus.getPosition().getX();
			int y = malus.getPosition().getY();
			int height = malus.getSkin().getHeight();
			int width = malus.getSkin().getWidth();

			if (this.checkCollision(x, y, height, width)) {
				malus.changeState();
				break;
			}
		}
	}

	/**
	 * Checks if the character collides with a booster, if it happens it will apply
	 * the effects of that booster
	 * 
	 * @param boosterList a list of the boosters that the character could collide
	 */
	public void collideBooster(List<Booster> boosterList) {
		for (Booster booster : boosterList) {
			int x = booster.getPosition().getX();
			int y = booster.getPosition().getY();
			int height = booster.getSkin().getHeight();
			int width = booster.getSkin().getWidth();

			if (this.checkCollision(x, y, height, width)) {
				booster.changeState();
				break;
			}
		}
	}

	/**
	 * Checks if the character collides with the borders of the map
	 */
	public void collideBorders() {
		int characterY = this.getPosition().getY();
		int characterLowerY = characterY + this.skin.getHeight();
		int upperBorder = 0;
		int lowerBorder = (int) Constants.SCREEN_SIZE.getHeight();

		if (characterY <= upperBorder || characterLowerY >= lowerBorder) {
			this.die();
		}
	}

	/**
	 * Private method to check if the character collides with a moving item
	 * 
	 * @param x      the x coordinate of the entity
	 * @param y      the y coordinate of the entity
	 * @param height the height of the entity
	 * @param width  the width of the entity
	 * @return true if the character collides with an entity
	 */
	private boolean checkCollision(int x, int y, int height, int width) {
		int characterX = this.getPosition().getX();
		int characterWiderX = characterX + this.skin.getWidth();
		int characterY = this.getPosition().getY();
		int characterLowerY = characterY + this.skin.getHeight();
		int entityWiderX = x + width;
		int entityLowerY = y + height;

		if ((characterX >= x && characterX <= entityWiderX)
				|| (characterWiderX >= x && characterWiderX <= entityWiderX)) {

			if ((characterY >= y && characterY <= entityLowerY)
					|| (characterLowerY >= y && characterLowerY <= entityLowerY)) {

				return true;
			}
		}

		return false;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void animate(Graphics2D canvas) {
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();
		int width = this.skin.getWidth();
		int height = this.skin.getHeight();
		int angle = this.jumping ? -Constants.CHARACTER_ANGLE_DEGREES : Constants.CHARACTER_ANGLE_DEGREES;
		Image image = CommonMethods.getAngledImage(this.skin.getImage(), angle);

		canvas.drawImage(image, x, y, width, height, null);

		this.updatePosition();
	}

	/**
	 * Private method to update the position of the character
	 */
	private void updatePosition() {
		int value = this.jumping ? -2 : 1;
		this.getPosition().setY(this.getPosition().getY() + value * Constants.MOVING_FACTOR);

	}

	public static class TestUpdatePosition {

		private final Position CHARACTER_INITIAL_POSITION = new Position((int) Constants.SCREEN_SIZE.getWidth() / 2,
				(int) Constants.SCREEN_SIZE.getHeight() / 2);
		private final Position CHARACTER_AFTER_FALLING_POSITION = new Position(
				(int) Constants.SCREEN_SIZE.getWidth() / 2,
				(int) Constants.SCREEN_SIZE.getHeight() / 2 + Constants.MOVING_FACTOR);
		private final Position CHARACTER_AFTER_JUMPING_POSITION = new Position(
				(int) Constants.SCREEN_SIZE.getWidth() / 2,
				CHARACTER_AFTER_FALLING_POSITION.getY() - Constants.MOVING_FACTOR * 2);

		@Test
		/**
		 * Checks if the position is updated correctly when the character falls or jumps
		 */
		public void updatePositionTest() {
			Character character = new Character(CHARACTER_INITIAL_POSITION,
					new Skin("floppa", CommonMethods.getImageResource("Floppa"),
							CommonMethods.getImageResource("Floppa").getWidth(null),
							CommonMethods.getImageResource("Floppa").getHeight(null)));
			character.updatePosition();
			assertTrue(character.getPosition().equals(CHARACTER_AFTER_FALLING_POSITION));

			character.jump();
			character.updatePosition();
			assertTrue(character.getPosition().equals(CHARACTER_AFTER_JUMPING_POSITION));
		}
	}

}

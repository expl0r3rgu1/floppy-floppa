package main.model.leaderboard;

/**
 * The user that is playing the game
 */
public class Player {
	private final String nickname;
	private int personalBest;

	/**
	 * @param nickname     The nickname of the Player
	 * @param personalBest The best score of the Player
	 */
	public Player(String nickname, int personalBest) {
		this.nickname = nickname;
		if (personalBest >= 0) {
			this.personalBest = personalBest;
		}
	}

	/**
	 * @return The nickname of the Player
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @return The best score of the Player
	 */
	public int getPersonalBest() {
		return personalBest;
	}

	/**
	 * @param personalBest The new best score of the Player
	 */
	public void setPersonalBest(int personalBest) {
		if (personalBest >= 0) {
			this.personalBest = personalBest;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		Player other = (Player) obj;
		if(other == null) {
			return false;
		}
		return this.nickname.equals(other.getNickname());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return nickname + "," + personalBest;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}

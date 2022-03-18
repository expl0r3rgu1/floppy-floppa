package main.menu.leaderboard;

public class Player {
	private String nickname;
	private int personalBest;

	public Player(String nickname, int personalBest) {
		this.nickname = nickname;
		if (personalBest >= 0) {
			this.personalBest = personalBest;
		}
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getPersonalBest() {
		return personalBest;
	}

	public void setPersonalBest(int personalBest) {
		if (personalBest >= 0) {
			this.personalBest = personalBest;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		Player other = (Player) obj;
		return this.nickname.equals(other.getNickname());
	}
	
	@Override
	public String toString() {
		return nickname + "," + personalBest;
	}
}

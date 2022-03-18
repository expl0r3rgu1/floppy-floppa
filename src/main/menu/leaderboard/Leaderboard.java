package main.menu.leaderboard;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Leaderboard {
	private File leaderboardRecord;
	private ArrayList<Player> leaderboard;

	public Player getBestPlayer() {
		this.sortLeaderboard();
		return leaderboard.get(0);
	}

	public ArrayList<Player> getLeaderboard() {
		this.sortLeaderboard();
		return leaderboard;
	}

	public void sortLeaderboard() {
		leaderboard.sort((a, b) -> a.getPersonalBest() - b.getPersonalBest());
	}

	public void update(Player player) {
		int index = Collections.binarySearch(this.leaderboard, player,
				(a, b) -> a.getPersonalBest() - b.getPersonalBest());

		if (index < 0)
			index = ~index; // Converting negative index to positive index = (-index) - 1

		this.leaderboard.add(index, player);
	}
}

package main.menu.leaderboard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import main.utilities.Constants;

public class Leaderboard {
	private final File leaderboardFile = new File(Constants.SAVINGS_FILE_PATH);
	private ArrayList<Player> leaderboard;

	public Leaderboard() {
		try {
			Scanner leaderboardFileScanner = new Scanner(leaderboardFile);
			this.skipToLeaderboardStart(leaderboardFileScanner);

			leaderboard = new ArrayList<>();

			while (leaderboardFileScanner.hasNextLine()) {
				var line = leaderboardFileScanner.nextLine();

				// Preventing wrong player to be added due to blank line
				if (!line.isBlank()) {
					leaderboard.add(new Player(line.split(",")[0], Integer.parseInt(line.split(",")[1])));
				}
			}

			leaderboardFileScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Player getBestPlayer() {
		return leaderboard.get(0);
	}

	public ArrayList<Player> getLeaderboard() {
		return leaderboard;
	}

	private void skipToLeaderboardStart(Scanner scanner) {
		for (int i = 0; i < Constants.LEADERBOARD_FILE_LINE_START && scanner.hasNextLine(); i++) {
			scanner.nextLine();
		}
	}

	public void writeToFile() throws IOException {
		FileWriter leaderboardFileWriter = new FileWriter(leaderboardFile, true);

		for (var player : leaderboard) {
			leaderboardFileWriter.append(player + "\n";
		}

		leaderboardFileWriter.close();
	}

	public void update(Player newPlayer) {
		int index = Collections.binarySearch(this.leaderboard, newPlayer,
				(a, b) -> a.getPersonalBest() - b.getPersonalBest());

		if (index < 0)
			index = ~index; // Converting negative index to positive index = (-index) - 1

		Player player = this.leaderboard.get(index);
		if (player.equals(newPlayer) && newPlayer.getPersonalBest() > player.getPersonalBest()) {
			player.setPersonalBest(newPlayer.getPersonalBest());
		} else {
			this.leaderboard.add(index, newPlayer);
		}
	}

  public void clearLeaderboard() {
    this.leaderboard.clear();
  }
}

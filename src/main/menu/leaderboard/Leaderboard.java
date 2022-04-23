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
	private final File leaderboardFile;
	private final ArrayList<Player> leaderboard;

	public Leaderboard() {
		leaderboardFile = new File(Constants.SAVINGS_FILE_PATH);
		leaderboard = new ArrayList<>();
		try {
			Scanner leaderboardFileScanner = new Scanner(leaderboardFile);
			this.skipToLeaderboardStart(leaderboardFileScanner);

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
			leaderboardFileWriter.append(player + "\n");
		}

		leaderboardFileWriter.close();
	}

	public void update(Player newPlayer) {
		int playerIndexInLeaderboard = this.leaderboard.indexOf(newPlayer);
		boolean playerAlreadyPresent = (playerIndexInLeaderboard == -1) ? false : true;

		if (playerAlreadyPresent) {
			if (newPlayer.getPersonalBest() > this.leaderboard.get(playerIndexInLeaderboard).getPersonalBest()) {
				this.leaderboard.remove(playerIndexInLeaderboard);

				binarySearchInsert(newPlayer);
			}
		} else {
			binarySearchInsert(newPlayer);
		}

	}

	private void binarySearchInsert(Player newPlayer) {
		int index = Collections.binarySearch(this.leaderboard, newPlayer, (a, b) -> {
			return b.getPersonalBest() - a.getPersonalBest();
		});

		if (index < 0)
			index = ~index; // Converting negative index to positive index = (-index) - 1

		this.leaderboard.add(index, newPlayer);
	}

	public void clearLeaderboard() {
		this.leaderboard.clear();
	}
}

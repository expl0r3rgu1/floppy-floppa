package junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import main.controller.menu.leaderboard.Leaderboard;
import main.model.leaderboard.Player;
import main.model.utilities.Constants;

public class TestLeaderboard {
	@Test
	public void testLeaderboardInitialization() {
		createFile();

		Leaderboard leaderboard = new Leaderboard();

		assertNotNull(leaderboard.getLeaderboard());
		assertTrue(leaderboard.getLeaderboard().size() == 0);
	}

	@Test
	public void testLeaderboardAddPlayer() {
		createFile();

		Leaderboard leaderboard = new Leaderboard();
		assertNotNull(leaderboard.getLeaderboard());
		assertTrue(leaderboard.getLeaderboard().size() == 0);

		Player newPlayer = new Player("expl0r3rgu1", 50);
		leaderboard.update(newPlayer);

		assertTrue(leaderboard.getLeaderboard().size() == 1);
		assertNotNull(leaderboard.getLeaderboard().get(0));
		assertNotNull(leaderboard.getLeaderboard().indexOf(newPlayer));
	}

	@Test
	public void testLeaderboardUpdatePlayer() {
		createFile();

		Leaderboard leaderboard = new Leaderboard();
		assertNotNull(leaderboard.getLeaderboard());
		assertTrue(leaderboard.getLeaderboard().size() == 0);

		String nickname = "expl0r3rgu1";

		Player player = new Player(nickname, 50);
		leaderboard.update(player);

		Player updatedPlayer = new Player(nickname, 60);

		leaderboard.update(updatedPlayer);

		assertTrue(leaderboard.getLeaderboard().size() == 1);
	}

	private void createFile() {
		File savingsFile = new File(Constants.SAVINGS_FILE_PATH);

		try {
			savingsFile.createNewFile();
			FileWriter savingsFileWriter = new FileWriter(savingsFile);
			savingsFileWriter.write(Constants.SAVINGS_FILE_START_CONTENT);
			savingsFileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

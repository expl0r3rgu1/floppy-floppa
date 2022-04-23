package junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import main.menu.leaderboard.Leaderboard;
import main.menu.leaderboard.Player;
import main.utilities.Constants;

public class TestLeaderboard {
	@Test
	public void testLeaderboardInitialization() {
		createFile();

		Leaderboard leaderboard = new Leaderboard();

		assertNotNull(leaderboard.getLeaderboard());
		assertTrue(leaderboard.getLeaderboard().size() == 0);
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

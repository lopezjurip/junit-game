import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameRulesCaseTest {
	static HighLifeBoard createBoard(int length, int width, int n, boolean populate) {
		boolean[][] data = new boolean[length][width];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				if (Math.floor(length / 2) == i && Math.floor(width / 2) == j) {
					data[i][j] = populate;
				} else if (n > 0) {
					data[i][j] = true;
					n--;
				} else {
					data[i][j] = false;
				}
			}
		}
		return new HighLifeBoard(data);
	}

	@Test
	public void testBornRule() {
		for (int i = 0; i < 9; i++) {
			HighLifeBoard board = createBoard(3, 3, i, false);
			assertEquals("Cell should stil dead", board.isAlive(1, 1), false);

			if (3 <= i && i <= 6) {
				assertEquals("Cell should born", true, board.shouldBeBorn(1, 1));
			} else {
				assertEquals("Cell should stil dead", false, board.shouldBeBorn(1, 1));
			}
		}
	}

	@Test
	public void testSurviveRule() {
		for (int i = 0; i < 9; i++) {
			HighLifeBoard board = createBoard(3, 3, i, true);
			assertEquals("Cell should stil alive", true, board.isAlive(1, 1));

			if (2 <= i && i <= 3) {
				assertEquals("Cell should survive", true, board.shouldSurvive(1, 1));
			} else {
				assertEquals("Cell should die", false, board.shouldSurvive(1, 1));
			}
		}
	}
}

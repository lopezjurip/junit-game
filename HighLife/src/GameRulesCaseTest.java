import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameRulesCaseTest {
	/**
	 * Create a sample board with some life on it (if required) and a center being (if required).
	 * @param length Board size
	 * @param width Board size
	 * @param n Number of starting life entities.
	 * @param populate True to start with a starting and centered (test subject) being.
	 * @return
	 */
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

	/**
	 * Test born rule according to HighLife rules.
	 * "... a cell is born if it has 3 or 6 neighbors." 
	 */
	@Test
	public void testBornRule() {
		for (int i = 0; i < 9; i++) {
			HighLifeBoard board = createBoard(3, 3, i, false);
			assertEquals("Cell should stil dead", board.isAlive(1, 1), false);

			if (3 == i || i == 6) {
				assertEquals("Cell should born", true, board.shouldBeBorn(1, 1));
			} else {
				assertEquals("Cell should stay dead", false, board.shouldBeBorn(1, 1));
			}
		}
	}

	/**
	 * Test survive rule according to HighLife rules.
	 * "... survives if it has 2 or 3 neighbors." 
	 */
	@Test
	public void testSurviveRule() {
		for (int i = 0; i < 9; i++) {
			HighLifeBoard board = createBoard(3, 3, i, true);
			assertEquals("Cell should stil alive", true, board.isAlive(1, 1));

			if (2 == i || i == 3) {
				assertEquals("Cell should survive", true, board.shouldSurvive(1, 1));
			} else {
				assertEquals("Cell should die", false, board.shouldSurvive(1, 1));
			}
		}
	}
}

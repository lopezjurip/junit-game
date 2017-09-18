import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.security.auth.SolarisNumericGroupPrincipal;
import com.sun.xml.internal.ws.policy.sourcemodel.AssertionData;

import junit.framework.Assert;

/**
 *
 */

/**
 * @author patriciolopez
 *
 */
public class CreatesHighLifeBoardTestCase {
	/**
	 * Creating a 0x0 board should run without creating exceptions. This is a border-case.
	 */
	@Test
	public void testCreateSmallestsBoard() {
		int length = 0, width = 0;
		HighLifeBoard board = new HighLifeBoard(length, width);

		boolean[][] data = board.getData();

		assertEquals("Row/Col should be empty", data.length, 0);
		for (int i = 0; i < data.length; i++) {
			assertEquals("Row/Col should be empty", data[i].length, 0);
		}
	}

	/**
	 * Should create a base 1x1 board.
	 */
	@Test
	public void testCreateUnidimentionalBoard() {
		int length = 1, width = 1;
		HighLifeBoard board = new HighLifeBoard(length, width);
		boolean[][] data = board.getData();
		assertEquals("Row/Col should be unidimentional", data.length, 1);
		for (int i = 0; i < data.length; i++) {
			assertEquals("Row/Col should be unidimentional", data[i].length, 1);
		}
	}

	/**
	 * Test the method isAlive to check correctness and in-bounds.
	 */
	@Test
	public void testIsAlive() {
		HighLifeBoard board = new HighLifeBoard(1, 1);
		board.setCell(0, 0, true);
		assertTrue("Should say it's alive", board.isAlive(0, 0));

		board.setCell(0, 0, false);
		assertFalse("Should say it's not alive", board.isAlive(0, 0));

		// Test out of bounds
		assertFalse("Out-of-bounds should say false", board.isAlive(-1, 0));
		assertFalse("Out-of-bounds should say false", board.isAlive(2, 0));
		assertFalse("Out-of-bounds should say false", board.isAlive(0, -1));
		assertFalse("Out-of-bounds should say false", board.isAlive(0, 2));
		assertFalse("Out-of-bounds should say false", board.isAlive(-1, -1));
		assertFalse("Out-of-bounds should say false", board.isAlive(2, 2));
	}

	/**
	 * Should create a normal sized board with no starting life on it.
	 */
	@Test
	public void testCreateBoard() {
		int length = 10, width = 10;
		HighLifeBoard board = new HighLifeBoard(length, width, false);

		boolean[][] data = board.getData();

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				assertEquals(data[i][j], false);
			}
		}
	}

	/**
	 * Should create a normal board with a chance of having life.
	 * This test can fail because is non-deterministic. Please run a couple of times.
	 */
	@Test
	public void testCreateBoardRandomConstructorAndTrue() {
		int length = 10, width = 10;
		HighLifeBoard board = new HighLifeBoard(length, width, true);

		boolean[][] data = board.getData();

		boolean hasLife = false;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (data[i][j]) hasLife = true;
			}
		}
		assertTrue(hasLife); // This is non-deterministic. Sometimes can fail.
	}
}

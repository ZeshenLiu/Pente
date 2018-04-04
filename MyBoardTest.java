import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MyBoardTest {

	MyBoard mb;
	
	@Before
	public void setUp() throws Exception {
		mb = new MyBoard();
	}

	@Test
	public void testPlaceStone() {
		assertEquals(0, mb.getMoveNumber());
		Coordinate c = new MyCoordinate(9, 9);
		mb.placeStone(Stone.RED, c);
		assertEquals(1, mb.getMoveNumber());
		c = new MyCoordinate(10, 10);
		mb.placeStone(Stone.YELLOW, c);
		assertEquals(2, mb.getMoveNumber());
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void testPlaceStoneErr2() {
		Coordinate c = new MyCoordinate(9, 9);
		mb.placeStone(Stone.RED, c);
		mb.placeStone(Stone.YELLOW, c);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testPlaceStoneErr3() {
		Coordinate c = new MyCoordinate(1, 0);
		mb.placeStone(Stone.RED, c);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testPlaceStoneErr4() {
		Coordinate c = new MyCoordinate(9, 9);
		mb.placeStone(Stone.RED, c);
		c = new MyCoordinate(10, 9);
		mb.placeStone(Stone.YELLOW, c);
		c = new MyCoordinate(11, 11);
		mb.placeStone(Stone.RED, c);
	}
	
	
	@Test
	public void testPieceAt() {
		Coordinate c = new MyCoordinate(9, 9);
		assertEquals(Stone.EMPTY, mb.pieceAt(c));
		mb.placeStone(Stone.RED, c);
		assertEquals(Stone.RED, mb.pieceAt(c));
		c = new MyCoordinate(10, 10);
		assertEquals(Stone.EMPTY, mb.pieceAt(c));
		mb.placeStone(Stone.YELLOW, c);
		assertEquals(Stone.YELLOW, mb.pieceAt(c));
	}

	@Test
	public void testIsOutOfBounds() {
		Coordinate c = new MyCoordinate(9, 9);
		assertFalse(mb.isOutOfBounds(c));
		c = new MyCoordinate(-1, 9);
		assertTrue(mb.isOutOfBounds(c));
		c = new MyCoordinate(29, 9);
		assertTrue(mb.isOutOfBounds(c));
		c = new MyCoordinate(4, 59);
		assertTrue(mb.isOutOfBounds(c));
		c = new MyCoordinate(4, -9);
		assertTrue(mb.isOutOfBounds(c));
		c = new MyCoordinate(19, 19);
		assertTrue(mb.isOutOfBounds(c));
		c = new MyCoordinate(-4, -18);
		assertTrue(mb.isOutOfBounds(c));
	}

	@Test
	public void testIsEmpty() {
		Coordinate c = new MyCoordinate(9, 9);
		assertTrue(mb.isEmpty(c));
		mb.placeStone(Stone.RED, c);
		assertFalse(mb.isEmpty(c));
		c = new MyCoordinate(12, 9);
		assertTrue(mb.isEmpty(c));
		mb.placeStone(Stone.YELLOW, c);
		assertFalse(mb.isEmpty(c));
	}

	@Test
	public void testGetMoveNumber() {
		assertEquals(0, mb.getMoveNumber());
		Coordinate c = new MyCoordinate(9, 9);
		mb.placeStone(Stone.RED, c);
		assertEquals(1, mb.getMoveNumber());
		c = new MyCoordinate(10, 10);
		mb.placeStone(Stone.YELLOW, c);
		assertEquals(2, mb.getMoveNumber());
	}

	@Test
	public void testGetRedCaptures() {
		Coordinate c = new MyCoordinate(9, 9);
		mb.placeStone(Stone.RED, c);
		Coordinate c2 = new MyCoordinate(8, 9);
		mb.placeStone(Stone.YELLOW, c2);
		
		c = new MyCoordinate(0, 3);
		mb.placeStone(Stone.RED, c);
		c2 = new MyCoordinate(7, 9);
		mb.placeStone(Stone.YELLOW, c2);
//		System.out.println(mb);
		
		assertEquals(0, mb.getRedCaptures());
		c = new MyCoordinate(6, 9);
		mb.placeStone(Stone.RED, c);
		assertEquals(1, mb.getRedCaptures());
		
		c2 = new MyCoordinate(0, 1);
		mb.placeStone(Stone.YELLOW, c2);

		c = new MyCoordinate(3, 3);
		mb.placeStone(Stone.RED, c);
		c2 = new MyCoordinate(1, 1);
		mb.placeStone(Stone.YELLOW, c2);

		c = new MyCoordinate(10, 11);
		mb.placeStone(Stone.RED, c);
		c2 = new MyCoordinate(2, 2);
		mb.placeStone(Stone.YELLOW, c2);
		
		c = new MyCoordinate(15, 18);
		mb.placeStone(Stone.RED, c);
		c2 = new MyCoordinate(0, 2);
		mb.placeStone(Stone.YELLOW, c2);
		assertEquals(1, mb.getRedCaptures());
		c = new MyCoordinate(0, 0);
		mb.placeStone(Stone.RED, c);
		assertEquals(3, mb.getRedCaptures());
	}

	@Test
	public void testGetYellowCaptures() {
		Coordinate c = new MyCoordinate(9, 9);
		mb.placeStone(Stone.RED, c);
		Coordinate c2 = new MyCoordinate(9, 8);
		mb.placeStone(Stone.YELLOW, c2);
		
		c = new MyCoordinate(0, 3);
		mb.placeStone(Stone.RED, c);
		c2 = new MyCoordinate(9, 0);
		mb.placeStone(Stone.YELLOW, c2);
		
		c = new MyCoordinate(9, 10);
		mb.placeStone(Stone.RED, c);
		assertEquals(0, mb.getYellowCaptures());
		c2 = new MyCoordinate(9, 11);
		mb.placeStone(Stone.YELLOW, c2);
		assertEquals(1, mb.getYellowCaptures());
	}

	@Test
	public void testGameOver() {
		assertFalse(mb.gameOver());
		Coordinate c = new MyCoordinate(9, 9);
		mb.placeStone(Stone.RED, c);
		assertFalse(mb.gameOver());
		c = new MyCoordinate(9, 10);
		mb.placeStone(Stone.RED, c);
		assertFalse(mb.gameOver());
		c = new MyCoordinate(9, 13);
		mb.placeStone(Stone.RED, c);
		assertFalse(mb.gameOver());
		c = new MyCoordinate(9, 12);
		mb.placeStone(Stone.RED, c);
		assertFalse(mb.gameOver());
		c = new MyCoordinate(9, 11);
		mb.placeStone(Stone.RED, c);
		assertTrue(mb.gameOver());

	}
	
	@Test
	public void testGameOverDiag() {
		assertFalse(mb.gameOver());
		Coordinate c = new MyCoordinate(9, 9);
		mb.placeStone(Stone.RED, c);
		c = new MyCoordinate(18, 0);
		mb.placeStone(Stone.RED, c);
		assertFalse(mb.gameOver());
		c = new MyCoordinate(17, 1);
		mb.placeStone(Stone.RED, c);
		assertFalse(mb.gameOver());
		c = new MyCoordinate(16, 2);
		mb.placeStone(Stone.RED, c);
		assertFalse(mb.gameOver());
		c = new MyCoordinate(15, 3);
		mb.placeStone(Stone.RED, c);
		assertFalse(mb.gameOver());
		c = new MyCoordinate(14, 4);
		mb.placeStone(Stone.RED, c);
		assertTrue(mb.gameOver());

	}

	@Test
	public void testGetWinner() {
		Coordinate c = new MyCoordinate(9, 9);
		mb.placeStone(Stone.RED, c);
		Coordinate c2 = new MyCoordinate(10, 9);
		mb.placeStone(Stone.YELLOW, c2);
		
		c = new MyCoordinate(9, 13);
		mb.placeStone(Stone.RED, c);
		c2 = new MyCoordinate(11, 9);
		mb.placeStone(Stone.YELLOW, c2);
		
		c = new MyCoordinate(9, 10);
		mb.placeStone(Stone.RED, c);
		c2 = new MyCoordinate(12, 9);
		mb.placeStone(Stone.YELLOW, c2);

		c = new MyCoordinate(9, 12);
		mb.placeStone(Stone.RED, c);
		c2 = new MyCoordinate(13, 9);
		mb.placeStone(Stone.YELLOW, c2);

		c = new MyCoordinate(9, 11);
		mb.placeStone(Stone.RED, c);
		assertTrue(mb.gameOver());
		assertEquals(Stone.RED, mb.getWinner());

	}
	
	@Test(expected = IllegalStateException.class)
	public void testGetWinnerException() {
		Coordinate c = new MyCoordinate(9, 9);
		mb.placeStone(Stone.RED, c);
		Coordinate c2 = new MyCoordinate(10, 9);
		mb.placeStone(Stone.YELLOW, c2);
		mb.getWinner();
	}
}

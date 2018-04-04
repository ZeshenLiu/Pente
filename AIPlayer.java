import java.util.Random;


public class ZeshenPlayer implements Player{

	private Stone s;
	
	public ZeshenPlayer(Stone st) {
		
		this.s = st;
//		if (mb.getMoveNumber() % 2 == 0) {
//			s = Stone.RED;
//		}
//		else {
//			s = Stone.YELLOW;
//		}
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

	@Override
	public Coordinate getMove(Board b) {
		// TODO Auto-generated method stub

		Coordinate c;
		Random rd = new Random();
		if (b.getMoveNumber() == 0) {
			c = new MyCoordinate(9, 9);
		} 
		else if (b.getMoveNumber() == 2) {
			while (true) {
				int x = rd.nextInt(19);
				int y = rd.nextInt(19);
				if (!(x > 6 && x < 12 && y > 6 && y < 12)) {
					c = new MyCoordinate(x, y);
					break;
				}
			}
		}
		else {
//			do {
//				int x = rd.nextInt(19);
//				int y = rd.nextInt(19);
//				c = new MyCoordinate(x, y);
//			} while(b.pieceAt(c) != Stone.EMPTY);
			
			c = checkBoard(b);
		}
			
		return c;
	}

	@Override
	public Stone getStone() {
		// TODO Auto-generated method stub
		return s;
	}

	
	private Coordinate checkBoard(Board b) {
		Coordinate c;
		Coordinate potential = null;
		int max = -100;
		// check horizontal row 
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				c = new MyCoordinate(i, j);
				if (b.pieceAt(c) != Stone.EMPTY) {
					if (b.pieceAt(c) != this.s) {
						int count = checkRow(b, c);
						if (count > 1 || count == -1) {
							if (b.pieceAt(new MyCoordinate(i, j+count)) == Stone.EMPTY)
								return new MyCoordinate(i, j+count);
						}

					}
				}
			}
		}
		
		// check vertical row
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				c = new MyCoordinate(i, j);
				if (b.pieceAt(c) != Stone.EMPTY) {
					if (b.pieceAt(c) != this.s) {
						int count = checkCol(b, c);
						if (count > 1 || count == -1) {
							if (b.pieceAt(new MyCoordinate(i+count, j)) == Stone.EMPTY)
								return new MyCoordinate(i+count, j);
						}

					}
				}
			}
		}
		
		// check right diagonal row
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				c = new MyCoordinate(i, j);
				if (b.pieceAt(c) != Stone.EMPTY) {
					if (b.pieceAt(c) != this.s) {
						int count = checkDiag1(b, c);
						if (count > 1 || count == -1) {
							if (b.pieceAt(new MyCoordinate(i+count, j+count)) == Stone.EMPTY)
								return new MyCoordinate(i+count, j+count);
						}

					}
				}
			}
		}
		
		// check left diagonal row
		for (int i = 2; i < 19; i++) {
			for (int j = 0; j < 17; j++) {
				c = new MyCoordinate(i, j);
				if (b.pieceAt(c) != Stone.EMPTY) {
					if (b.pieceAt(c) != this.s) {
						int count = checkDiag2(b, c);
						if (count > 1 || count == -1) {
							if (b.pieceAt(new MyCoordinate(i-count, j+count)) == Stone.EMPTY)
								return new MyCoordinate(i-count, j+count);
						}

					}
				}
			}
		}
		
		
		// make progress if no necessary to defend
		// try to place at right diagonal row first
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 17; j++) {
				c = new MyCoordinate(i, j);
				if (b.pieceAt(c) != Stone.EMPTY) {
					if (b.pieceAt(c) == this.s) {
						int count = attackDiag1(b, c);
						if (count > 0) {
							if (b.pieceAt(new MyCoordinate(i+count, j+count)) == Stone.EMPTY)
//								return new MyCoordinate(i+count, j+count);
								potential = new MyCoordinate(i+count, j+count);
								max = count;
						}
					}
				}
			}
		}
		
		// try to place stone in a row
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 17; j++) {
				c = new MyCoordinate(i, j);
				if (b.pieceAt(c) != Stone.EMPTY) {
					if (b.pieceAt(c) == this.s) {
						int count = attackRow(b, c);
						if (count > 0) {
							if (b.pieceAt(new MyCoordinate(i, j+count)) == Stone.EMPTY)
								if (count > max) {
									return new MyCoordinate(i, j+count);
								}
								else if (potential != null){
									return potential;
								}
								
						}
					}
				}
			}
		}
		
		// otherwise, just place stone randomly
		Random rd = new Random();
		do {
		int x = rd.nextInt(19);
		int y = rd.nextInt(19);
		c = new MyCoordinate(x, y);
		} while(b.pieceAt(c) != Stone.EMPTY);
		return c;
	}
	
	
	private int attackDiag1(Board b, Coordinate c) {
		int count = 0;
		int row = c.getRow();
		int col = c.getColumn();
		Stone st;
		do {
			count++;
			if (row+count > 18 || col+count > 18) {
				return -2;
			}
			Coordinate cd = new MyCoordinate(row+count, col+count);
			st = b.pieceAt(cd);
		}while(st == this.s);
		if (st != Stone.EMPTY) {
			count = 0;
		}
		return count;
	}
	
	
	private int attackRow(Board b, Coordinate c) {
		int count = 0;
		int row = c.getRow();
		int col = c.getColumn();
		Stone st;
		do {
			count++;
			if (col+count > 18) {
				return -2;
			}
			Coordinate cd = new MyCoordinate(row, col+count);
			st = b.pieceAt(cd);
		}while(st == this.s);
		if (st != Stone.EMPTY) {
			count = 0;
		}
		return count;
	}
	
	
	
	private int checkRow(Board b, Coordinate c) {
		int count = 0;
		int row = c.getRow();
		int col = c.getColumn();
		if (col == 18) {
			return 0;
		}
		Stone st;
		do {
			count++;
			if (col+count > 18 && col != 0) {
				return -1;
			}
			Coordinate cd = new MyCoordinate(row, col+count);
			st = b.pieceAt(cd);
		}while(st != Stone.EMPTY && st != this.s);
		
		if (st == this.s && col != 0) {
			count = -1;
		}
		else if (st == this.s && col == 0) {
			count = 0;
		}
		return count;
	}
	
	private int checkCol(Board b, Coordinate c) {
		int count = 0;
		int row = c.getRow();
		int col = c.getColumn();
		if (row == 18) {
			return 0;
		}
		Stone st;
		do {
			count++;
			if (row+count > 18 && row != 0) {
				return -1;
			}
			Coordinate cd = new MyCoordinate(row+count, col);
			st = b.pieceAt(cd);
		}while(st != Stone.EMPTY && st != this.s);
		
		if (st == this.s && row != 0) {
			count = -1;
		}
		else if (st == this.s && row == 0) {
			count = 0;
		}
		return count;
	}
	
	private int checkDiag1(Board b, Coordinate c) {
		int count = 0;
		int row = c.getRow();
		int col = c.getColumn();
		if (row == 18 || col == 18) {
			return 0;
		}
		Stone st;
		do {
			count++;
			if (row+count > 18 || col+count > 18) {
				return -1;
			}
			Coordinate cd = new MyCoordinate(row+count, col+count);
			st = b.pieceAt(cd);
		}while(st != Stone.EMPTY && st != this.s);
		
		if (st == this.s && row != 0 && col != 0) {
			count = -1;
		}
		else if (st == this.s && (row == 0 || col == 0)) {
			count = 0;
		}
		return count;
	}
	
	private int checkDiag2(Board b, Coordinate c) {
		int count = 0;
		int row = c.getRow();
		int col = c.getColumn();
		Stone st;
		do {
			count++;
			if (row-count < 0 || col+count > 18) {
				return -1;
			}
			Coordinate cd = new MyCoordinate(row-count, col+count);
			st = b.pieceAt(cd);
		}while(st != Stone.EMPTY && st != this.s);
		
		if (st == this.s && col != 0) {
			count = -1;
		}
		else if (st == this.s && col == 0) {
			count = 0;
		}
		return count;
	}
}

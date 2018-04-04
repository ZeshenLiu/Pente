
public class MyBoard implements Board{

	private Stone[][] board;
	private int moveNum;
	private int redCaptures;
	private int yelCaptures;
	
	
	public MyBoard() {
		board = new Stone[19][19];
		// initialize all array elements to Stone.EMPTY
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				board[i][j] = Stone.EMPTY;
			}
		}
		
		moveNum = 0;
		redCaptures = 0;
		yelCaptures = 0;
	}
	
	/**
	 * This function places a Stone S at coordinate C on the board.
	 * This function should not allow a piece to "overwrite" another
	 * piece. That is, if a coordinate on the board is not EMPTY, this
	 * function will not overwrite that piece and instead throw a 
	 * RuntimeException of some kind. Additionally throws a RuntimeException
	 * if c is out of bounds. Finally, it throws a RuntimeException if the move
	 * made is illegal (see the instructions on Tournament Rules).
	 * 
	 * @param s - the stone to be placed, either Stone.RED or Stone.YELLOW
	 * @param c - a coordinate of where on the board to place.
	 */
	@Override
	public void placeStone(Stone s, Coordinate c) {
		// do the error checking each placing
		errorChecking(c);
		
		// place the stone and increment move number
		int row = c.getRow();
		int col = c.getColumn();
		this.board[row][col] = s;
		moveNum++;
		
		// check captures and remove stones that are captured
		int count = checkCapture(s, c);
		if (count != 0) {
			if (moveNum % 2 == 1) redCaptures += count;
			else yelCaptures += count;
		}
		
 	}

	
	private void errorChecking(Coordinate c) {
		// placing a stone beyond the board
		if (isOutOfBounds(c)) {
			throw new IllegalArgumentException();
		}
		
		// placing a stone on a non-empty coordinate
		if (pieceAt(c) != Stone.EMPTY) {
			throw new IllegalArgumentException();
		}
		
		// For red's first move and it's not placing at the center
		if (moveNum == 0 && (c.getRow() != 9 || c.getColumn() != 9)) {
			throw new IllegalArgumentException();
		}
		// For red's second move and it's placing within 3 intersections of the center
		else if (moveNum == 2 && (c.getRow() > 6 && c.getRow() < 12 && c.getColumn() > 6 && c.getColumn() < 12)) {
			throw new IllegalArgumentException();
		}
	}
	
	
	private int checkCapture(Stone s, Coordinate c) {
		
		int row = c.getRow();
		int col = c.getColumn();
		int count = 0;
		
		int rowLB = row < 3? row : row-3; // left bound of row
		int rowRB = row > 15? row : row+3; // right bound of row
		int colLB = col < 3? col : col-3; // left bound of column
		int colRB = col > 15? col: col+3; // right bound of column
		
		// check horizontal captures
		if (colLB != col && board[row][colLB] == s) {
			if (board[row][col-1] != s && board[row][col-1] != Stone.EMPTY && board[row][col-2] != s && board[row][col-2] != Stone.EMPTY) {
				count++;
				board[row][col-1] = Stone.EMPTY;
				board[row][col-2] = Stone.EMPTY;
			}
		}
		else if(colRB != col && board[row][colRB] == s) {
			if (board[row][col+1] != s && board[row][col+1] != Stone.EMPTY && board[row][col+2] != s && board[row][col+2] != Stone.EMPTY) {
				count++;
				board[row][col+1] = Stone.EMPTY;
				board[row][col+2] = Stone.EMPTY;
			}
		}
		
		// check vertical captures
		if (rowLB != row && board[rowLB][col] == s) {
			if (board[row-1][col] != s && board[row-1][col] != Stone.EMPTY && board[row-2][col] != s && board[row-2][col] != Stone.EMPTY) {
				count++;
				board[row-1][col] = Stone.EMPTY;
				board[row-2][col] = Stone.EMPTY;
			}
		}
		else if(rowRB != row && board[rowRB][col] == s) {
			if (board[row+1][col] != s && board[row+1][col] != Stone.EMPTY && board[row+1][col] != s && board[row+2][col] != Stone.EMPTY) {
				count++;
				board[row+1][col] = Stone.EMPTY;
				board[row+2][col] = Stone.EMPTY;
			}
		}		
		
		// check diagonal captures
		if (colLB != col && rowLB != row && board[rowLB][colLB] == s) {
			if (board[row-1][col-1] != s && board[row-1][col-1] != Stone.EMPTY && board[row-2][col-2] != s && board[row-2][col-2] != Stone.EMPTY) {
				count++;
				board[row-1][col-1] = Stone.EMPTY;
				board[row-2][col-2] = Stone.EMPTY;
			}
		}
		else if(colRB != col && rowLB != row && board[rowLB][colRB] == s) {
			if (board[row-1][col+1] != s && board[row-1][col+1] != Stone.EMPTY && board[row-2][col+2] != s && board[row-2][col+2] != Stone.EMPTY) {
				count++;
				board[row-1][col+1] = Stone.EMPTY;
				board[row-2][col+2] = Stone.EMPTY;
			}
		}
		
		if (colLB != col && rowRB != row && board[rowRB][colLB] == s) {
			if (board[row+1][col-1] != s && board[row+1][col-1] != Stone.EMPTY && board[row+2][col-2] != s && board[row+2][col-2] != Stone.EMPTY) {
				count++;
				board[row+1][col-1] = Stone.EMPTY;
				board[row+2][col-2] = Stone.EMPTY;
			}
		}
		else if(colRB != col && rowRB != row && board[rowRB][colRB] == s) {
			if (board[row+1][col+1] != s && board[row+1][col+1] != Stone.EMPTY && board[row+2][col+2] != s && board[row+2][col+2] != Stone.EMPTY) {
				count++;
				board[row+1][col+1] = Stone.EMPTY;
				board[row+2][col+2] = Stone.EMPTY;
			}
		}		
		// return count to know how many captures this one single move have made
		return count;
	}
	
	
	/**
	 * returns the stone at C on the board
	 * @param c - the coordinate you are getting the stone from
	 * @return the stone at the coordinate C
	 */
	@Override
	public Stone pieceAt(Coordinate c) {
		// TODO Auto-generated method stub
		// Is the coordinate beyond the board
		if (isOutOfBounds(c)) {
			throw new IllegalArgumentException();
		}
		return board[c.getRow()][c.getColumn()];
	}

	
	/**
	 * Returns true of the coordinate C is out of bounds (such as [-3,37])
	 * 
	 * 
	 * @param c - the coordinate
	 * @return true/false whether coordinate is out of bounds.
	 */
	@Override
	public boolean isOutOfBounds(Coordinate c) {
		// TODO Auto-generated method stub
		int row = c.getRow();
		int col = c.getColumn();
		if (row < 0 || row > 18 || col < 0 || col > 18) {
			return true;
		}
		return false;
	}

	
	/**
	 * Returns true if the space at coordinate is Stone.EMPTY. False otherwise
	 * @param c - the coordinate
	 * @return true/false if space is empty
	 */
	@Override
	public boolean isEmpty(Coordinate c) {
		// TODO Auto-generated method stub
		if (isOutOfBounds(c)) {
			System.out.println("Out of bounds!");
		}
		if (pieceAt(c) == Stone.EMPTY) {
			return true;
		}
		return false;
	}

	
	/**
	 * Return the number of COMPLETED moves. I.e., if called before the first piece is placed,
	 * this should return 0.
	 * @return
	 */
	@Override
	public int getMoveNumber() {
		// TODO Auto-generated method stub
		return moveNum;
	}

	
	/**
	 * Get the number of times red has captures a pair of yellow pieces.
	 * @return
	 */
	@Override
	public int getRedCaptures() {
		// TODO Auto-generated method stub
		return redCaptures;
	}

	
	/**
	 * Get the number of times yellow has captures a pair of red pieces.
	 * @return
	 */
	@Override
	public int getYellowCaptures() {
		// TODO Auto-generated method stub
		return yelCaptures;
	}

	
	/**
	 * Return true if the game has ended, by either playing getting 5 in a row
	 * or capturing 5 pairs.
	 * @return
	 */
	@Override
	public boolean gameOver() {
		// TODO Auto-generated method stub
		if (redCaptures >= 5 || yelCaptures >= 5) {
			return true;
		}
		
		// check the whole board to detect whether there is a row to end the game
		
		// first, check all columns looking for five or more stones in a row
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 19; j++) {
				//if there is some stone on that grid, check its around
				if (board[i][j] != Stone.EMPTY) {
					Stone s = board[i][j];
					int k;
					
					//check the same column down
					for (k = i + 1; k < i+5; k++) {
						if (board[k][j] != s) {
							break;
						}
					}
					if (k == i + 5) {
						return true;
					}
				}
			}
		}
		
		// then check all rows
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 15; j++) {
				//if there is some stone on that grid, check its around
				if (board[i][j] != Stone.EMPTY) {
					Stone s = board[i][j];
					int k;
					// check the same row forward
					for (k = j + 1; k < j+5; k++) {
						if (board[i][k] != s) {
							break;
						}
					}
					if (k == j + 5) {
						return true;
					}
				}
			}
		}
		
		// check the diagonal ones in range of left top to right bottom
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				//if there is some stone on that grid, check its around
				if (board[i][j] != Stone.EMPTY) {
					Stone s = board[i][j];
					int k;
					
					for (k = 1; k < 5; k++) {
						if (board[i+k][j+k] != s) {
							break;
						}
					}
					if (k == 5) {
						return true;
					}
				}
			}
		}
		
		// check the diagonal ones in range of left bottom to right top
		for (int i = 4; i < 19; i++) {
			for (int j = 0; j < 15; j++) {
				//if there is some stone on that grid, check its around
				if (board[i][j] != Stone.EMPTY) {
					Stone s = board[i][j];
					int k;
					
					for (k = 1; k < 5; k++) {
						if (board[i-k][j+k] != s) {
							break;
						}
					}
					if (k == 5) {
						return true;
					}
				}
			}
		}
		return false;
	}

	
	/**
	 * Return the winner of the game (Stone.RED or Stone.YELLOW) if there is one.
	 * If there is no winner, throw a RuntimeException.
	 * @return
	 */
	@Override
	public Stone getWinner() {
		// TODO Auto-generated method stub
		// when gameover, check move number to know which stone wins
		if (gameOver()) {
			if (getMoveNumber() % 2 == 1) return Stone.RED;
			else return Stone.YELLOW;
		}
		else {
			throw new IllegalStateException();
		}
	}
	
	/**
	 * Red Stones should print as an "O" character (capital O)
	 * Yellow Stones should print as an "X" character (capital X)
	 * Empty Stones should print as a space character " "
	 */
	public String toString() {
		
//		String head = " 0123456789012345678";
//		String str = "";
		
		StringBuilder str = new StringBuilder();
		str.append(" 0123456789012345678");
		for (int i = 0; i < 19; i++) {
			str.append("\n");
			str.append(i%10);
//			str = str + "\n" + (i%10);
			for (int j = 0 ; j < 19; j++) {
				Stone s = board[i][j];
				if (s == Stone.RED) {
//					str += "O";
					str.append("O");
				}
				else if (s == Stone.YELLOW) {
//					str += "X";
					str.append("X");
				}
				else {
//					str += ".";
					str.append(" ");
				}
			}
		}
		return str.append("\n").toString();
	}



}

import java.util.Scanner;

public class Game {
	
	private MyBoard mb;
	private HumanPlayer hp;  
 	private ZeshenPlayer zs;
//	private HumanPlayer zs;
	private Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game gm = new Game();	
		gm.run();
	}
	
	/*
	 * run method covers main body of the game
	 */
	public void run() {
		// Construct a new board
		mb = new MyBoard();
		// c1 is the first player's coordinate, c2 is for the second
		Coordinate c1;
		Coordinate c2;
		
		// AIfirst noting whether the computer goes first or not
		boolean AIfirst;	
		// s is the stone for human player, s2 is for computer
		Stone s = chooseStone();
		Stone s2;
		hp = new HumanPlayer(s);
		// construct computer player with the left stone color
		if (s == Stone.RED)  {
			AIfirst = false;
			s2 = Stone.YELLOW;
		}
		else {
			AIfirst = true;
			s2 = Stone.RED;
		}

		zs = new ZeshenPlayer(s2);
//			zs = new HumanPlayer(s2);
		
		// if game is not over
		while(!mb.gameOver()) {
			// print out the board first
			System.out.println(mb);
			
			// in this case, computer goes first
			if (AIfirst) {
				// computer choose coordinate to place stone first
				c1 = zs.getMove(mb);
				mb.placeStone(zs.getStone(), c1);
				System.out.println(mb);
				// check whether the game is over
				if (mb.gameOver()) {
					break;
				}
				// human player begin to place stone
				while (true) {
					c2 = hp.getMove(mb);				
					// if input is illegal, ask player to input again
					try {
						mb.placeStone(hp.getStone(), c2);
						break;
					}catch(Exception e) {
						System.out.println("Please input legal move!");
						continue;
					}
				}
				System.out.println(mb);
			}
			
			// human player goes first
			else {
				while (true) {
					c1 = hp.getMove(mb);
					try {
						mb.placeStone(hp.getStone(), c1);
						break;
					}catch(Exception e) {
						System.out.println("Please input legal move!");
						continue;
					}
				}

				System.out.println(mb);
				if (mb.gameOver()) {
					break;
				}
				c2 = zs.getMove(mb);
				mb.placeStone(zs.getStone(), c2);
				System.out.println(mb);
			}
			
		}
		
		// when game is over, there will be a winner
		Stone winner = mb.getWinner();
		System.out.println(winner + " is the winner! Congratulations!");
		System.out.println("Red got " + mb.getRedCaptures() + " captures!");
		System.out.println("Yellow got " + mb.getYellowCaptures() + " captures!");

	}
	
	/*
	 * ask user to choose stone
	 * going first means to have Stone.RED
	 * otherwise, user will use Stone.YELLOW
	 */
	private Stone chooseStone() {

		System.out.println("Do you want to go first? (Y/N)");
		String input;
		
		while(true){	
			input = sc.nextLine();
			input = input.trim().toUpperCase();
			if(input.startsWith("Y")){
				return Stone.RED;
			}
			else if(input.startsWith("N")){
				return Stone.YELLOW;
			}
			// illegal input and ask again
			else{
				System.out.println("Please input Y or N.");
			}
		}	
	}
	
	

}

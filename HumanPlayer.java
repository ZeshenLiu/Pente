import java.util.Scanner;

public class HumanPlayer implements Player{
	
	private Stone s;
//	private Scanner sc = new Scanner(System.in);

	
	public HumanPlayer(Stone st) {
		
		this.s = st;
//		s = chooseStone();
//		if (mb.getMoveNumber() % 2 == 0) {
//			s = Stone.RED;
//		}
//		else {
//			s = Stone.YELLOW;
//		}
	}

	@Override
	public Coordinate getMove(Board b) {
		// TODO Auto-generated method stub
			
 		Coordinate c;
		int[] input = new int[2];
		input = input();
		
		c = new MyCoordinate(input[0], input[1]);	
		
		return c;
	}
	
	
	@Override
	public Stone getStone() {
		// TODO Auto-generated method stub
		return s;
	}
	
	
	private int[] input() {
		int[] input = new int[2];

		while(true){
			System.out.println("Enter row and column number you want to place your stone at:(from 0 to 18)");
			Scanner sc = new Scanner(System.in);
			try {
				input[0] = sc.nextInt();
				input[1] = sc.nextInt(); 
				if(input[0] >= 0 && input[0] < 19 && input[1] >= 0 && input[1] < 19){
//					sc.close();
					return input; 
				}
			}catch (Exception e) {
				System.out.println("Please input legal coordinate!(from 0 to 18)");
			}
//			input[0] = sc.nextInt();
//			input[1] = sc.nextInt(); 
//			if(input[0] >= 0 && input[0] < 19 && input[1] >= 0 && input[1] < 19){
////				sc.close();
//				return input; 
//			}
		} 
	}

	
//	private Stone chooseStone() {
//		System.out.println("Do you want to go first? (Y/N)");
//		String input;
////		sc = new Scanner(System.in);
//		while(true){	
//			input = sc.nextLine();
//			input = input.trim().toUpperCase();
//			if(input.startsWith("Y")){
//				//sc.close();
//				return Stone.RED;
//			}
//			else if(input.startsWith("N")){
//				//sc.close();
//				return Stone.YELLOW;
//			}
//			else{
//				System.out.println("Please input Y or N.");
//			}
//		}	
//	}
	

}


public class MyCoordinate implements Coordinate{
	
	private int row;
	private int col;
	
	public MyCoordinate(int r, int c) {
		this.row = r;
		this.col = c;
	}
	

	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return this.row;
	}

	
	@Override
	public int getColumn() {
		// TODO Auto-generated method stub
		return this.col;
	}
	
	
	@Override
	public String toString() {
		String s = "(" + this.row + ", " + this.col + ")";
		return s;
	}

	
//	public static void main(String[] args) {
//	// TODO Auto-generated method stub
//		MyCoordinate mc = new MyCoordinate(2, 2);
//		System.out.println(mc);
//	}
	
}

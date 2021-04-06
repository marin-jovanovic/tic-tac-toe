package source.backend;

public class Board {
	private Tile[][] tiles = new Tile[3][3];
	
	public Board() {	
		tiles[0][0] = new Tile(Owner.NONE, Position.DOWNLEFT);
		tiles[0][1] = new Tile(Owner.NONE, Position.DOWNCENTER);
		tiles[0][2] = new Tile(Owner.NONE, Position.DOWNRIGHT);
		
		tiles[1][0] = new Tile(Owner.NONE, Position.MIDDLELEFT);
		tiles[1][1] = new Tile(Owner.NONE, Position.MIDDLECENTER);
		tiles[1][2] = new Tile(Owner.NONE, Position.MIDDLERIGHT);
		
		tiles[2][0] = new Tile(Owner.NONE, Position.UPLEFT);
		tiles[2][1] = new Tile(Owner.NONE, Position.UPCENTER);
		tiles[2][2] = new Tile(Owner.NONE, Position.UPRIGHT);
	}
	
	public void setBoardTile(int positionOfTile, Owner owner) {
		/**
		 * 0 00
		 * 1 01
		 * 2 02
		 * 3 10
		 * 4 11
		 * 5 12
		 * 6 20
		 * 7 21
		 * 8 22
		 */
		tiles[positionOfTile / 3][positionOfTile % 3].setOwner(owner);
	}
	public Tile getBoardTile(int x, int y) {
		return tiles[x][y];
	}
	public Tile getBoardTile(int positionOfTile) {
		return tiles[positionOfTile / 3][positionOfTile % 3];
	}
	public boolean isTileAvailable(int position) {
		return (this.getBoardTile(position).getOwner() == Owner.NONE) ;
	}
	
	public void printBoard() {
		for (int i = 0; i < 3; i++) {
			System.out.print("[");
			for (int j = 0; j < 3; j++) {
				System.out.print(this.getBoardTile(i, j));
			}
			System.out.println("]");
		}
	}
	public void printBoardAndManual() {
		for (int i = 0; i < 3; i++) {
			System.out.print("[");
			for (int j = 0; j < 3; j++) {
				System.out.print(this.getBoardTile(i, j));
			}
			System.out.print("] [");
			for (int j = 0; j < 3; j++) {
				System.out.print(i*3+j);
			}
			System.out.println("]");
			
		}
	}
	
	
	
	private  static Board b;
	//private Board b2;
	public static void main(String[] args) {
		Board board = new Board();
		board.printBoard();
		b =new Board();
		b.printBoard();
	
	
		
	}
	
	int howManyMovesAreLeft() {
		int ret = 0;
		boolean isAnyTileAvailable = false;
		for (int i = 0; i < 9; i++) {
			if(isTileAvailable(i)) {
				isAnyTileAvailable = true;
				ret++;
			}
		}
		if(!isAnyTileAvailable) {
			return -1;
		}
		else {
			return ret;
		}
	}
	
	boolean isWinner(Owner owner) {
		/**
		 * XXx
		 * x00
		 * x00
		 */
		if(this.getBoardTile(0).getOwner() == owner) {
			if(this.getBoardTile(1).getOwner() == owner
			 && this.getBoardTile(2).getOwner() == owner) {
					return true;
				}		
			if(this.getBoardTile(3).getOwner() == owner
					&&	this.getBoardTile(6).getOwner() == owner) {
					return true;
				}	
		}
		/**
		 * 00x
		 * 00x
		 * xxX
		 */
		if(this.getBoardTile(8).getOwner() == owner) {
			if(this.getBoardTile(7).getOwner() == owner
					&& this.getBoardTile(6).getOwner() == owner) {
				return true;
			}	
			if (this.getBoardTile(5).getOwner() == owner 
					&& this.getBoardTile(2).getOwner() == owner) {
				return true;
			}
		}
		/**
		 * 0x0
		 * xXx
		 * 0x0
		 * 
		 * x0x
		 * 0x0
		 * x0x
		 *
		 * 012
		 * 345
		 * 678
		 * 
		 */
		if(this.getBoardTile(4).getOwner() == owner) {
			if (this.getBoardTile(0).getOwner() == owner 
					&& this.getBoardTile(8).getOwner() == owner) {
				return true;
			}
			if (this.getBoardTile(1).getOwner() == owner 
					&& this.getBoardTile(7).getOwner() == owner) {
				return true;
			}
			if (this.getBoardTile(2).getOwner() == owner 
					&& this.getBoardTile(6).getOwner() == owner) {
				return true;
			}
			if (this.getBoardTile(3).getOwner() == owner 
					&& this.getBoardTile(5).getOwner() == owner) {
				return true;
			}
		}		
		return false;
	}
}

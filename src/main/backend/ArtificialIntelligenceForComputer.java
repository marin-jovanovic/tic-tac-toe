package source.backend;



public class ArtificialIntelligenceForComputer {
	public static int losComputersTurn(Board board) {
		
		
		//ako postoji win move onda ga igraj
		
		//ako se moze blokirat lose move onda ga blokiraj
		for (int i = 0; i < polje.length; i++) {
			polje[i] = 0;
		}
			 minimax(board, Owner.COMPUTER, 0,0);
		
		int maxValue = -100000;
		int index = 0;
		for (int i = 0; i < polje.length; i++) {
			if (polje[i] > 0) {
				maxValue = polje[i];
				index = i;
				break;
			}
		}
		if (maxValue != -1) {
			for (int i = index; i < polje.length; i++) {
				if (polje[i] > maxValue && polje[i] != 0) {
					maxValue = polje[i];
					index = i;
				}
			}
			return index;
		}
		return -1;
		
	}

	private static int[] polje = new int[9];
	private static int koji;
	private static void minimax(Board board, Owner whoseTurnIs, int depth, int zaKojiProvjeravamo) {

		if(board.isWinner(Owner.COMPUTER)) {
			polje[koji]++;
			return ;
		}
		else if (board.isWinner(Owner.USER)) {
			polje[koji]--;
			return ;
		}
		else if(board.howManyMovesAreLeft() == -1){
			polje[koji] ++;
			return ;
		}
		
		
		for (int i = 0; i < 9; i++) {
			if(board.isTileAvailable(i)) {

				Board b2 = new Board();
				
				for (int j = 0; j < 9; j++) {
					b2.setBoardTile(j, board.getBoardTile(j).getOwner());
				}
				
				b2.setBoardTile(i, whoseTurnIs);

				
				Owner temp = Owner.COMPUTER;
				if(whoseTurnIs.equals(Owner.COMPUTER)) {
					temp = Owner.USER;
				}
				if(depth == 0) {
					koji = i;
					
				}
				int depthTemp = depth +1;
			
				 minimax(b2, temp, depthTemp, koji);

				
			}
		}
	
		return ; 
	}


	
	
	public static int computersTurn(Board b) {
		int bestScore = -100000000;
		int move = -1;
		
		
		
		for (int i = 0; i < 9; i++) {
			
			
			if(b.isTileAvailable(i)){
			
			
				b.setBoardTile(i, Owner.COMPUTER);
				int score = minimax(b, false);
				b.setBoardTile(i, Owner.NONE);
		
				if(score > bestScore) {
					bestScore = score;
					move = i;
				}
			}
		}
		return move;
	}
	private static int minimax(Board b, boolean isMaximizing) {
		if(b.isWinner(Owner.COMPUTER)) {
		
			return 10;
		}
		else if (b.isWinner(Owner.USER)) {
	
			return -10;
		}
		else if(b.howManyMovesAreLeft() == -1){
			return 0;
		}
		
		if(isMaximizing) {
			int bestscore = -100000000;
			for (int i = 0; i < 9; i++) {
				
				if(b.isTileAvailable(i)) {
					
					b.setBoardTile(i, Owner.COMPUTER);
				
					int score = minimax(b, false);
					
					b.setBoardTile(i, Owner.NONE);
					
					bestscore = score > bestscore ? score : bestscore;
				}
			}
			return bestscore;
		}
		else {
			int bestscore = 100000000;
			for (int i = 0; i < 9 ; i++) {
				if (b.isTileAvailable(i)) {
					b.setBoardTile(i, Owner.USER);
					int score = minimax(b,  true);
					b.setBoardTile(i, Owner.NONE);
					bestscore = score > bestscore ? bestscore : score;
				}
			}
			return bestscore;
		}
		
		
	}
	public static void main(String[] args) {
		SettingsAndOptions.setMarkers(Marker.o);
		Board b = new Board();
		/**
		 * xxu
		 * ucu
		 * cuc
		 * 
		 */
	//	b.setBoardTile(2, Owner.USER);
	//	b.setBoardTile(3, Owner.USER);
	//	b.setBoardTile(4, Owner.COMPUTER);
	//	b.setBoardTile(5, Owner.USER);
	//	b.setBoardTile(6, Owner.COMPUTER);
	//	b.setBoardTile(7, Owner.USER);
	//	b.setBoardTile(8, Owner.COMPUTER);
	//	b.setBoardTile(1, Owner.COMPUTER);
		b.setBoardTile(4, Owner.USER);
		b.setBoardTile(0, Owner.COMPUTER);
		b.setBoardTile(3, Owner.USER);
		b.setBoardTile(5, Owner.NONE);
		b.printBoardAndManual();
		System.out.println();
		
		
		
		
		System.out.println("lala");
		
	
	}

	
	
	
}

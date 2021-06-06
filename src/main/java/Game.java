import java.nio.channels.Pipe;
import java.util.Scanner;

public class Game implements Winnable {

	private final int xAxisLength = 3;
	private final int yAxisLength = 3;

	private final boolean isUserX = true;
	private final Tile[][] tiles;

	/**
	 * len = 4, wid = 2
	 * ....
	 * ....
	 */
	public Game() {

		this.tiles = new Tile[yAxisLength][xAxisLength];

		for (int y = 0; y < yAxisLength; y++) {
			for (int x = 0; x < xAxisLength; x++) {
				tiles[y][x] = new Tile();
			}
		}
	}

	record MinimaxResult(Point whereTo, int result) {
		@Override
		public String toString() {
			return whereTo + " -> " + result;
		}

	}

	public static void main(String[] args) {
		Game g = new Game();

		g.tiles[0][0].setOwner(Owner.USER_1);
		g.tiles[1][0].setOwner(Owner.USER_1);
		g.tiles[2][1].setOwner(Owner.COMPUTER);

//		g.tiles[1][1].setOwner(Owner.COMPUTER);
//		g.tiles[1][2].setOwner(Owner.USER_1);
//
//		g.tiles[2][0].setOwner(Owner.USER_1);

		g.printBoard();
//		System.out.println();

		Point p = g.computerMove();

		g.setTile(p, Owner.COMPUTER);

//          todo check if other won
		if (g.isGameWon(p, Owner.COMPUTER)) {
			System.out.println("game won");
		}



//		Scanner sc = new Scanner(System.in);


//
//		while (true) {
//
//			g.printBoard();
//
////			            computer move
//            Point p = g.computerMove();
//
//            g.setTile(p, Owner.COMPUTER);
//
////          todo check if other won
//			if (g.isGameWon(p, Owner.COMPUTER)) {
//				break;
//			}
//
//			System.out.print("Enter x: ");
//			int x = Integer.parseInt(sc.nextLine());
//			System.out.print("Enter y: ");
//			int y = Integer.parseInt(sc.nextLine());
//			g.setTile(new Point(x, y), Owner.USER_1);
//
////          todo check if other won
//			if (g.isGameWon(new Point(x, y), Owner.USER_1)) {
//				break;
//			}
//
//			System.out.println();
//		}

		System.out.println("end of game");
	}

	@Override
	public int getXAxisLength() {
		return xAxisLength;
	}

	@Override
	public int getYAxisLength() {
		return yAxisLength;
	}

	@Override
	public Tile getTile(int x, int y) {
		return tiles[y][x];
	}

	/**
	 * minmax alg driver for computer move
	 *
	 * @return true if move is made else false
	 */
	public Point computerMove() {

		MinimaxResult a =  minimax(Owner.COMPUTER, 1);

		System.out.println(a);

		return a.whereTo();
	}

	private static void printFormatted(Object val, int offset) {
		String buffer = "";
		for (int  i = 1; i < offset; i++) {
			buffer += "\t";
		}

		System.out.println(buffer + val);
	}

	private MinimaxResult minimax(Owner turn, int depth) {

		int m;
//		todo catch
		int bestX = -1;
		int bestY = -1;

		if (turn == Owner.COMPUTER) {
			m = Integer.MIN_VALUE;
		} else {
			m = Integer.MAX_VALUE;
		}

		boolean isSomethingPlaced = false;
		for (int x = 0; x < xAxisLength; x++) {
			for (int y = 0; y < yAxisLength; y++) {
				if (tiles[y][x].isTileEmpty()) {

					int sum = 0;
					isSomethingPlaced = true;

					Point p = new Point(x, y);

					setTile(p, turn);

					if (depth == 1) {
						System.out.println();
						printBoard(depth);

					}

					if (isGameWon(p, turn)) {
						if (depth == 1) {
							printFormatted("game won " + turn, depth);
						}
						if (turn == Owner.COMPUTER) {
							sum++;
						} else if (turn == Owner.USER_1) {
							sum--;
						}
					} else {
						if (turn == Owner.COMPUTER) {
							MinimaxResult r = minimax(Owner.USER_1, depth+1);
							sum += r.result();

						} else if (turn == Owner.USER_1) {
							MinimaxResult r = minimax(Owner.COMPUTER, depth+1);
							sum += r.result();
						}
					}

					setTile(p, Owner.NONE);
//todo randomize
					if (turn == Owner.COMPUTER) {
						if (sum > m) {
							m = sum;
							bestX = x;
							bestY = y;

							if (depth == 1) {

								printFormatted("new best move " + m + " " + new Point(bestX, bestY), depth);
							}
						}
					} else {
						if (sum < m) {
							m = sum;
							bestX = x;
							bestY = y;
							if (depth == 1) {

								printFormatted("new best move " + m + " " + new Point(bestX, bestY), depth);
							}
						}
					}

				}
			}
		}
		if (! isSomethingPlaced		) {
			m = 0;
			if (depth == 1) {
				printFormatted("tie" , depth);
				printFormatted("returning " + m, depth);
				printFormatted("best move " + new Point(bestX, bestY), depth);
				System.out.println();

			}
			return new MinimaxResult(new Point(bestX, bestY), m);
		}
		if (depth == 1) {
			printFormatted("returning " + m, depth);
			printFormatted("best move " + new Point(bestX, bestY), depth);
			System.out.println();

		}
		return new MinimaxResult(new Point(bestX, bestY), m);
	}


	public boolean setTile(Point p, Owner owner) {
		if (p.getX() >= xAxisLength) {
			System.out.println("x to big");
			return false;
		} else if (p.getY() >= yAxisLength) {
			System.out.println("y to big");
			return false;
		}

//		System.out.println("updating  " + p.getX() + " " + p.getY());

		tiles[p.getY()][p.getX()].setOwner(owner);

		return true;
	}

	public void printBoard(int offset) {
		for (int y = 0; y < yAxisLength; y++) {
			String buffer = "";

			for (int  i = 0; i < offset; i++) {
				buffer += "\t";
			}

			for (int x = 0; x < xAxisLength; x++) {
				if (isUserX) {
					if (tiles[y][x].getOwner() == Owner.USER_1) {
						buffer += "x ";
					} else if (tiles[y][x].getOwner() == Owner.COMPUTER) {
						buffer += "o ";
					} else if (tiles[y][x].getOwner() == Owner.NONE) {
						buffer += ". ";
					}

				} else {
					if (tiles[y][x].getOwner() == Owner.USER_1) {
						buffer += "o ";
					} else if (tiles[y][x].getOwner() == Owner.COMPUTER) {
						buffer += "x ";
					} else if (tiles[y][x].getOwner() == Owner.NONE) {
						buffer += ". ";
					}
				}
			}
			System.out.println(buffer);

		}
	}



	public void printBoard() {
		for (int y = 0; y < yAxisLength; y++) {
			String buffer = "";

			for (int x = 0; x < xAxisLength; x++) {
				if (isUserX) {
					if (tiles[y][x].getOwner() == Owner.USER_1) {
						buffer += "x ";
					} else if (tiles[y][x].getOwner() == Owner.COMPUTER) {
						buffer += "o ";
					} else if (tiles[y][x].getOwner() == Owner.NONE) {
						buffer += ". ";
					}

				} else {
					if (tiles[y][x].getOwner() == Owner.USER_1) {
						buffer += "o ";
					} else if (tiles[y][x].getOwner() == Owner.COMPUTER) {
						buffer += "x ";
					} else if (tiles[y][x].getOwner() == Owner.NONE) {
						buffer += ". ";
					}
				}
			}
			System.out.println(buffer);

		}
	}


}

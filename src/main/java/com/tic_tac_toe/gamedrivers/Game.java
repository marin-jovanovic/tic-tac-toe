package com.tic_tac_toe.gamedrivers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game implements Winnable {
	// solid principle, single principle,
	// interface sagregation
	/// more client specific  then general purpose

	private final int xAxisLength = 3;
	private final int yAxisLength = 3;


	private final boolean isUserX = true;
	private final Tile[][] tiles;
	private List<Point> bestMovesLayerOne;

	public Game() {

		this.tiles = new Tile[yAxisLength][xAxisLength];

		for (int y = 0; y < yAxisLength; y++) {
			for (int x = 0; x < xAxisLength; x++) {
				tiles[y][x] = new Tile();
			}
		}
	}

	private static void printFormatted(Object val, int offset) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 1; i < offset; i++) {
			buffer.append("\t");
		}

		System.out.println(buffer.toString() + val);
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

		bestMovesLayerOne = new ArrayList<>();
		MinimaxResult a = minimax(TileOwner.COMPUTER, 1);

		if (a.getIsWinningMove()) {
			return a.getWhereTo();
		}

		bestMovesLayerOne.add(a.getWhereTo());
		Random rand = new Random();
		Point randomElement = bestMovesLayerOne.get(rand.nextInt(bestMovesLayerOne.size()));
		return randomElement;
	}

	//	todo check if more than n elems are placed (needed for win)
	private MinimaxResult minimax(TileOwner turn, int depth) {

		int m;
//		todo catch
		int bestX = -1;
		int bestY = -1;

		Point bestPoint = null;

		if (turn == TileOwner.COMPUTER) {
			m = Integer.MIN_VALUE;
		} else {
			m = Integer.MAX_VALUE;
		}

//determinate if needed
/*		for (int x = 0; x < xAxisLength; x++) {
			for (int y = 0; y < yAxisLength; y++) {
				if (tiles[y][x].isTileEmpty()) {
					Point p = new Point(x, y);

					setTile(p, turn);
					if (isGameWon(p, turn)) {

						printFormatted("game won " + turn, depth);

//						win on first try
						if (depth == 1) {
							System.out.println("auto win");
							return new MinimaxResult(p, m, true);
						}
					}
				}
			}
		}
*/

		boolean isSomethingPlaced = false;


		for (int x = 0; x < xAxisLength; x++) {
			for (int y = 0; y < yAxisLength; y++) {
				if (tiles[y][x].isTileEmpty()) {

					int sum = 0;
					isSomethingPlaced = true;

					Point p = new Point(x, y);

					setTile(p, turn);

					if (isGameWon(p, turn)) {

//						printFormatted("game won " + turn, depth);

//						win on first try
						if (depth == 1) {
//							System.out.println("auto win");
							return new MinimaxResult(p, m, true);
						}

						if (turn == TileOwner.COMPUTER) {
							sum++;
						} else if (turn == TileOwner.USER_1) {
							sum--;
						}
					} else {
						if (turn == TileOwner.COMPUTER) {
							MinimaxResult r = minimax(TileOwner.USER_1, depth + 1);
							sum += r.getResult();

						} else if (turn == TileOwner.USER_1) {
							MinimaxResult r = minimax(TileOwner.COMPUTER, depth + 1);
							sum += r.getResult();
						}
					}

					setTile(p, TileOwner.NONE);

					//					check if best move
					if ((turn == TileOwner.COMPUTER && sum >= m) || (turn == TileOwner.USER_1 && sum <= m)) {

						if (sum == m && depth == 1) {
							bestMovesLayerOne.add(new Point(x, y));

						} else {
							m = sum;
							bestX = x;
							bestY = y;
							bestPoint = p;
							bestMovesLayerOne.clear();
						}


//						printFormatted("new best move " + m + " " + bestPoint, depth);

					}
				}
			}
		}
		if (!isSomethingPlaced) {
			m = 0;
//			printFormatted("tie", depth);
//			printFormatted("returning " + m, depth);
//			printFormatted("best move " + bestPoint, depth);
//			System.out.println();

			return new MinimaxResult(bestPoint, m);
		}
//		printFormatted("returning " + m, depth);
//		printFormatted("best move " + new Point(bestX, bestY), depth);
//		System.out.println();

		return new MinimaxResult(bestPoint, m);
	}

	public boolean setTile(Point p, TileOwner tileOwner) {
		if (p.getX() >= xAxisLength) {
			System.out.println("x to big");
			return false;
		} else if (p.getY() >= yAxisLength) {
			System.out.println("y to big");
			return false;
		}

		tiles[p.getY()][p.getX()].setOwner(tileOwner);

		return true;
	}

	public void printBoard(int offset) {
		for (int y = 0; y < yAxisLength; y++) {
			StringBuilder buffer = new StringBuilder();

			for (int i = 0; i < offset; i++) {
				buffer.append("\t");
			}

			for (int x = 0; x < xAxisLength; x++) {
				if (isUserX) {
					if (tiles[y][x].getOwner() == TileOwner.USER_1) {
						buffer.append("x ");
					} else if (tiles[y][x].getOwner() == TileOwner.COMPUTER) {
						buffer.append("o ");
					} else if (tiles[y][x].getOwner() == TileOwner.NONE) {
						buffer.append(". ");
					}

				} else {
					if (tiles[y][x].getOwner() == TileOwner.USER_1) {
						buffer.append("o ");
					} else if (tiles[y][x].getOwner() == TileOwner.COMPUTER) {
						buffer.append("x ");
					} else if (tiles[y][x].getOwner() == TileOwner.NONE) {
						buffer.append(". ");
					}
				}
			}
			System.out.println(buffer);

		}
	}

	class MinimaxResult {
		private final Point whereTo;
		private final int result;
		private final boolean isWinningMove;

		MinimaxResult(Point whereTo, int result) {
			this.whereTo = whereTo;
			this.result = result;
			this.isWinningMove = false;
		}

		MinimaxResult(Point whereTo, int result, boolean isWinningMove) {
			this.whereTo = whereTo;
			this.result = result;
			this.isWinningMove = isWinningMove;
		}

		@Override
		public String toString() {
			return whereTo + " -> " + result;
		}

		public int getResult() {
			return result;
		}

		public Point getWhereTo() {
			return whereTo;
		}

		public boolean getIsWinningMove() {
			return isWinningMove;
		}
	}

}

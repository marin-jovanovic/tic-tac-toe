package com.tic_tac_toe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game implements Winnable {

	private final int xAxisLength = 3;
	private final int yAxisLength = 3;


	private final boolean isUserX = true;
	private final Tile[][] tiles;

	public Game() {

		this.tiles = new Tile[yAxisLength][xAxisLength];

		for (int y = 0; y < yAxisLength; y++) {
			for (int x = 0; x < xAxisLength; x++) {
				tiles[y][x] = new Tile();
			}
		}
	}

	class MinimaxResult {
		private Point whereTo;
		private int result;

		MinimaxResult(Point whereTo, int result) {
			this.whereTo = whereTo;
			this.result = result;
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
		MinimaxResult a =  minimax(Owner.COMPUTER, 1);

		System.out.println(a);
		System.out.println("best moves");
		bestMovesLayerOne.forEach(e -> System.out.println(e));
		bestMovesLayerOne.add(a.getWhereTo());
		Random rand = new Random();
		Point randomElement = bestMovesLayerOne.get(rand.nextInt(bestMovesLayerOne.size()));
		System.out.println("chosen" + randomElement);
		return randomElement;
//		return a.getWhereTo();
	}

	private static void printFormatted(Object val, int offset) {
		String buffer = "";
		for (int  i = 1; i < offset; i++) {
			buffer += "\t";
		}

		System.out.println(buffer + val);
	}

	private List<Point> bestMovesLayerOne;
//	= new ArrayList<>();


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

					if (isGameWon(p, turn)) {
						printFormatted("game won " + turn, depth);

						if (turn == Owner.COMPUTER) {
							sum++;
						} else if (turn == Owner.USER_1) {
							sum--;
						}
					} else {
						if (turn == Owner.COMPUTER) {
							MinimaxResult r = minimax(Owner.USER_1, depth+1);
							sum += r.getResult();

						} else if (turn == Owner.USER_1) {
							MinimaxResult r = minimax(Owner.COMPUTER, depth+1);
							sum += r.getResult();
						}
					}

					setTile(p, Owner.NONE);
//todo randomize
//					check if best move
					if ((turn == Owner.COMPUTER && sum >= m) || (turn == Owner.USER_1 && sum <= m)) {
//						System.out.println(
//								"depth=" + depth
//						);
						if (sum == m && depth == 1) {
							bestMovesLayerOne.add(new Point(x, y));

						} else  {
							m = sum;
							bestX = x;
							bestY = y;
							bestMovesLayerOne.clear();
						}


						printFormatted("new best move " + m + " " + new Point(bestX, bestY), depth);

					}
				}
			}
		}
		if (! isSomethingPlaced		) {
			m = 0;
				printFormatted("tie" , depth);
				printFormatted("returning " + m, depth);
				printFormatted("best move " + new Point(bestX, bestY), depth);
				System.out.println();

			return new MinimaxResult(new Point(bestX, bestY), m);
		}
			printFormatted("returning " + m, depth);
			printFormatted("best move " + new Point(bestX, bestY), depth);
			System.out.println();

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

}

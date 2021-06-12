package com.tic_tac_toe.gamedrivers.board;

import com.tic_tac_toe.gamedrivers.GameMode;
import com.tic_tac_toe.gamedrivers.Winnable;
import com.tic_tac_toe.gamedrivers.minimax.Minimax;
import com.tic_tac_toe.gamedrivers.minimax.MinimaxResult;
import com.tic_tac_toe.gamedrivers.point.Point;
import com.tic_tac_toe.gamedrivers.tile.Tile;
import com.tic_tac_toe.gamedrivers.tile.TileOwner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game implements Winnable, MinimaxBasicImplementation {
	// solid principle, single principle,
	// interface sagregation
	/// more client specific  then general purpose

	private int xAxisLength;
	private int yAxisLength;

	private final boolean isUserX = true;

	private final Tile[][] tiles;


//	for minimax
//	private List<Point> bestMovesLayerOne;

	private GameMode gameMode;
	private Minimax minimaxAlgorithm;

	public Game() {
		this.xAxisLength = 3;
		this.yAxisLength = 3;

//		this.minimaxAlgorithm = new BasicMinimax();


//		todo
		this.gameMode = GameMode.USER_VS_COMPUTER;

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

	public GameMode getGameMode() {
		return gameMode;
	}

//	for winnable interface
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

	@Override
	public boolean isGameWon(Point p, TileOwner tileOwner) {
		return Winnable.super.isGameWon(p, tileOwner);
	}

//
	/**
	 * minmax alg driver for computer move
	 *
	 * @return true if move is made else false
	 */
//	@Override
//	public Point computerMove() {
//
//		bestMovesLayerOne = new ArrayList<>();
//
//		/*
//		strategy desing patern
//
//		interface if {
//			racunajmax
//		}
//
//		class minimax impl if{
//			@override
//			racunajmax
//		}
//
//		class minimaxbolji impl if{
//		}
//		...
//
//		class game {
//			tip alg;
//
//			game() {
//				alg = minimax/minimaxnajbolji
//			}
//
//		}
//
//			MinimaxResult a = alg.racunajmax(TileOwner.COMPUTER, 1);
//
//		 */
//
//		MinimaxResult a = minimax(TileOwner.COMPUTER, 1);
//
//		if (a.getIsWinningMove()) {
//			return a.getWhereTo();
//		}
//
//		bestMovesLayerOne.add(a.getWhereTo());
//		Random rand = new Random();
//		Point randomElement = bestMovesLayerOne.get(rand.nextInt(bestMovesLayerOne.size()));
//		return randomElement;
//	}

	//	todo check if more than n elems are placed (needed for win)
//	private MinimaxResult minimax(TileOwner turn, int depth) {
//
//		int m;
////		todo catch
//		int bestX = -1;
//		int bestY = -1;
//
//		Point bestPoint = null;
//
//		if (turn == TileOwner.COMPUTER) {
//			m = Integer.MIN_VALUE;
//		} else {
//			m = Integer.MAX_VALUE;
//		}
//
////determinate if needed
///*		for (int x = 0; x < xAxisLength; x++) {
//			for (int y = 0; y < yAxisLength; y++) {
//				if (tiles[y][x].isTileEmpty()) {
//					Point p = new Point(x, y);
//
//					setTile(p, turn);
//					if (isGameWon(p, turn)) {
//
//						printFormatted("game won " + turn, depth);
//
////						win on first try
//						if (depth == 1) {
//							System.out.println("auto win");
//							return new MinimaxResult(p, m, true);
//						}
//					}
//				}
//			}
//		}
//*/
//
//		boolean isSomethingPlaced = false;
//
//
//		for (int x = 0; x < xAxisLength; x++) {
//			for (int y = 0; y < yAxisLength; y++) {
//				if (tiles[y][x].isTileEmpty()) {
//
//					int sum = 0;
//					isSomethingPlaced = true;
//
//					Point p = new Point(x, y);
//
//					setTile(p, turn);
//
//					if (isGameWon(p, turn)) {
//
////						printFormatted("game won " + turn, depth);
//
////						win on first try
//						if (depth == 1) {
////							System.out.println("auto win");
//							return new MinimaxResult(p, m, true);
//						}
//
//						if (turn == TileOwner.COMPUTER) {
//							sum++;
//						} else if (turn == TileOwner.USER_1) {
//							sum--;
//						}
//					} else {
//						if (turn == TileOwner.COMPUTER) {
//							MinimaxResult r = minimax(TileOwner.USER_1, depth + 1);
//							sum += r.getResult();
//
//						} else if (turn == TileOwner.USER_1) {
//							MinimaxResult r = minimax(TileOwner.COMPUTER, depth + 1);
//							sum += r.getResult();
//						}
//					}
//
//					setTile(p, TileOwner.NONE);
//
//					//					check if best move
//					if ((turn == TileOwner.COMPUTER && sum >= m) || (turn == TileOwner.USER_1 && sum <= m)) {
//
//						if (sum == m && depth == 1) {
//							bestMovesLayerOne.add(new Point(x, y));
//
//						} else {
//							m = sum;
//							bestX = x;
//							bestY = y;
//							bestPoint = p;
//							bestMovesLayerOne.clear();
//						}
//
//
////						printFormatted("new best move " + m + " " + bestPoint, depth);
//
//					}
//				}
//			}
//		}
//		if (!isSomethingPlaced) {
//			m = 0;
////			printFormatted("tie", depth);
////			printFormatted("returning " + m, depth);
////			printFormatted("best move " + bestPoint, depth);
////			System.out.println();
//
//			return new MinimaxResult(bestPoint, m);
//		}
////		printFormatted("returning " + m, depth);
////		printFormatted("best move " + new Point(bestX, bestY), depth);
////		System.out.println();
//
//		return new MinimaxResult(bestPoint, m);
//	}


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

}

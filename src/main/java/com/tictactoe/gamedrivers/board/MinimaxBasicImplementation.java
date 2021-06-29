package com.tictactoe.gamedrivers.board;

import com.tictactoe.gamedrivers.minimax.MinimaxResult;
import com.tictactoe.gamedrivers.point.Point;
import com.tictactoe.gamedrivers.tile.Tile;
import com.tictactoe.gamedrivers.tile.TileOwner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface MinimaxBasicImplementation extends MinimaxBase{

	/**
	 * base algorithm
	 *
	 * @param turn
	 * @param depth
	 * @return
	 */
	//	todo check if more than n elems are placed (needed for win)
	@Override
	default MinimaxResult minimax(TileOwner turn, int depth) {

		int m;
//		todo catch
//		int bestX = -1;
//		int bestY = -1;

		Point bestPoint = null;

		if (turn == TileOwner.USER_2) {
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

		for (int x = 0; x < getXAxisLength(); x++) {
			for (int y = 0; y < getYAxisLength(); y++) {
				if (getTile(x, y).isTileEmpty()) {
					int sum = 0;
					isSomethingPlaced = true;

					Point p = new Point(x, y);

					setTile(p, turn);

					if (isGameWon(p, turn)) {

//						win on first try
						if (depth == 1) {
							return new MinimaxResult(p, m, true);
						}

						if (turn == TileOwner.USER_2) {
							sum++;
						} else if (turn == TileOwner.USER_1) {
							sum--;
						}

					} else {
						if (turn == TileOwner.USER_1 || turn == TileOwner.USER_2) {
							MinimaxResult r = minimax(turn.getOppositeTileOwner(), depth + 1);
							sum += r.getResult();
						}
					}

					setTile(p, TileOwner.NONE);

					//					check if best move
					if ((turn == TileOwner.USER_2 && sum >= m) || (turn == TileOwner.USER_1 && sum <= m)) {

						if (sum == m && depth == 1) {
							bestMovesLayerOne.add(new Point(x, y));

						} else {
							m = sum;
//							bestX = x;
//							bestY = y;
							bestPoint = p;
							bestMovesLayerOne.clear();
						}

					}
				}
			}
		}

//		printFormatted("best move " + new Point(bestX, bestY), depth);

		if (!isSomethingPlaced) {
			m = 0;
//			printFormatted("tie", depth);
//			printFormatted("returning " + m, depth);
//			printFormatted("best move " + bestPoint, depth);
//			System.out.println();
//			System.out.println("is winnable " + isWinnableOrLosable);

			return new MinimaxResult(bestPoint, m, false);
		}

//		printFormatted("returning " + m, depth);
//		printFormatted("best move " + new Point(bestX, bestY), depth);
//		System.out.println();

		return new MinimaxResult(bestPoint, m, false);
	}


	Tile getTile(int x, int y);

	void printBoard(int offset);

	boolean isGameWon(Point p, TileOwner turn);

	boolean setTile(Point p, TileOwner turn);

	//    helpers
	int getXAxisLength();

	int getYAxisLength();


}

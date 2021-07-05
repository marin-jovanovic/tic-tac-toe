package com.tictactoe.gamedrivers.board.largegame;

import com.tictactoe.gamedrivers.board.base.Game;
import com.tictactoe.gamedrivers.board.base.MinimaxBase;
import com.tictactoe.gamedrivers.minimax.MinimaxResult;
import com.tictactoe.gamedrivers.point.Point;
import com.tictactoe.gamedrivers.tile.Tile;
import com.tictactoe.gamedrivers.tile.TileOwner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MinimaxTimeImpl extends MinimaxBase {

	default boolean checkSymmetryY() {

		for (int x = 0; x < getXAxisLength(); x++) {

			for (int y = 0; y < getYAxisLength() / 2; y++) {

//				System.out.println("(" +
//							x
//							+ ", " +
//							y
//							+ ") -> (" +
//							(x)
//							+ ", " +
//							(getYAxisLength() - 1 - y)
//							+ ")");

				if (getTile(x, y).getOwner() != getTile(x,
						getYAxisLength() - 1 - y).getOwner()) {
//						System.out.println("NOT same");
					return false;
				}

			}
		}

		return true;

//		return false;
	};

	default boolean checkSymmetryX() {

		for (int x = 0; x < getXAxisLength() / 2; x++) {

			for (int y = 0; y < getYAxisLength() ; y++) {

//				System.out.println("(" +
//						x
//						+ ", " +
//						y
//						+ ") -> (" +
//						(getXAxisLength() - 1 - x)
//						+ ", " +
//						(y)
//						+ ")");

				if (getTile(x, y).getOwner() != getTile(getXAxisLength()- 1 -x, y).getOwner()) {
//					System.out.println("NOT same");
					return false;
				}

			}
		}

		return true;
	};

//	todo check for 5 x 5 and 6 x 6


	//	checks if symetric by main diagonal
	default boolean checkSymmetryNonMainDiagonal() {

		for (int x = 0; x < getXAxisLength(); x++) {

			for (int y = 0; y < getYAxisLength(); y++) {

//					skip main diagonal
				if (x + y == getXAxisLength() - 1) {
//					System.out.println("same " + x + " " + y);
					continue;
				}

//					System.out.println("(" +
//							x
//							+ ", " +
//							y
//							+ ") -> (" +
//							(getYAxisLength() - y - 1)
//							+ ", " +
//							(getXAxisLength() - x - 1)
//							+ ")");

				if (getTile(x, y).getOwner() != getTile(getYAxisLength() - y - 1,
						getXAxisLength() - x - 1).getOwner()) {
//						System.out.println("NOT same");
					return false;
				}

			}
		}

		return true;

	}
	//	checks if symetric by main diagonal
	default boolean checkSymmetryMainDiagonal() {

		for (int x = 0; x < getXAxisLength(); x++) {

			for (int y = 0; y < getYAxisLength(); y++) {

//					skip main diagonal
				if (x == y) {
					continue;
				}

				//					q.out.println("(" + x + ", " + y + ") -> (" + y + ", " + x + ")");

				if (getTile(x, y).getOwner() != getTile(y, x).getOwner()) {
//						System.out.println("NOT same");
					return false;
				}


			}
		}

		return true;

	}

	default boolean checkForSymmetry() {
		System.out.println("main diagonal");
		if (checkSymmetryMainDiagonal()) {
			System.out.println("same");
		} else {
			System.out.println("not same");
		}

		System.out.println("other diagonal");
		if (checkSymmetryNonMainDiagonal()) {
			System.out.println("same");
		} else {
			System.out.println("not same");
		}

		System.out.println("x axis");
		if (checkSymmetryX()) {
			System.out.println("same");
		} else {
			System.out.println("not same");
		}

		System.out.println("y axis");
		if (checkSymmetryY()) {
			System.out.println("same");
		} else {
			System.out.println("not same");
		}

//		todo
		return false;
	}


//	fixme not working properly
	Map<String, MinimaxResult> dpTable = new HashMap<>();

	default MinimaxResult enhancedMinimax(TileOwner turn, int depth, int alpha, int beta, Point prevPointPlaced) {


		String hash = Arrays.deepToString(getTiles());
//		System.out.println("hash " + hash);

		if (dpTable.containsKey(hash)) {
//			System.out.println("i have this " + hash);

			return dpTable.get(hash);

		}

		if (prevPointPlaced != null) {
			if (isGameWon(prevPointPlaced, turn.getOppositeTileOwner())) {

				int result = 0;

				if (turn.getOppositeTileOwner() == TileOwner.USER_1) {
					result = -1;
				} else if (turn.getOppositeTileOwner() == TileOwner.USER_2) {
					result = 1;
				}

				return new MinimaxResult(prevPointPlaced, result, true);
			}
		}

		int m = 0;

		if (turn == TileOwner.USER_1) {
			m = beta;
		} else if (turn == TileOwner.USER_2) {
			m = alpha;
		}

		Point bestPoint = null;
		boolean isSomethingPlaced = false;

		for (int x = 0; x < getXAxisLength(); x++) {
			for (int y = 0; y < getYAxisLength(); y++) {
				if (getTile(x, y).isTileEmpty()) {

					isSomethingPlaced = true;

					Point p = new Point(x, y);

					setTile(p, turn);

					MinimaxResult r = enhancedMinimax(turn.getOppositeTileOwner(), depth + 1,
							alpha, beta, p);

					setTile(p, TileOwner.NONE);

					int oldM = m;

					if (turn == TileOwner.USER_1) {
						m = Math.min(m, r.getResult());

						if (m <= alpha) {
//							printFormatted(bestPoint.toString(), depth);

//							if (dpTable.containsKey(getTiles())) {
//								System.out.println("in");
//								System.out.println("in "+ dpTable.get(getTiles()));
//								System.out.println("cr " + bestPoint);
//							} else {
//								System.out.println("adding " + Arrays.deepToString(getTiles()));
//								dpTable.put(getTiles(), new MinimaxResult(r.getWhereTo(),
//										m, false));
//							}

							return new MinimaxResult(bestPoint, alpha, false);
						}

					} else  if (turn == TileOwner.USER_2) {
						m = Math.max(m, r.getResult());

						if (m >= beta) {
//							printFormatted(bestPoint.toString(), depth);

//							if (dpTable.containsKey(getTiles())) {
//								System.out.println("in");
//								System.out.println("in "+ dpTable.get(getTiles()));
//								System.out.println("cr " + bestPoint);
//							} else {
//								System.out.println("adding " + Arrays.deepToString(getTiles()));
//								dpTable.put(getTiles(), new MinimaxResult(r.getWhereTo()
//										, m, false));
//							}

							return new MinimaxResult(bestPoint, beta, false);
						}
					}

					if (m != oldM) {
						bestPoint = p;
					}


				}
			}
		}

		MinimaxResult minimaxResult = new MinimaxResult(bestPoint, isSomethingPlaced? m : 0, false);


		if (bestPoint != null) {

			hash = Arrays.deepToString(getTiles());

			if (dpTable.containsKey(hash)) {
//				System.out.println("in");
//				System.out.println(Arrays.deepToString(getTiles()));
//				System.out.println("in "+ dpTable.get(hash));
//				System.out.println("cr " + bestPoint);

			} else {
//				System.out.println("adding " + Arrays.deepToString(getTiles()));
//				System.out.println(bestPoint);
				dpTable.put(hash, minimaxResult);
			}
		}



		return minimaxResult;
	}

	default MinimaxResult basicNewMinimax(TileOwner turn, int depth, int alpha, int beta, Point prevPointPlaced) {
		if (prevPointPlaced != null) {
			if (isGameWon(prevPointPlaced, turn.getOppositeTileOwner())) {

				int result = 0;

				if (turn.getOppositeTileOwner() == TileOwner.USER_1) {
					result = 1;
				} else if (turn.getOppositeTileOwner() == TileOwner.USER_2) {
					result = -1;
				}

				result = -result;
				return new MinimaxResult(prevPointPlaced, result, true);
			}
		}

		int m;

		if (turn == TileOwner.USER_1) {
			m = Integer.MAX_VALUE;
		} else if (turn == TileOwner.USER_2) {
			m = Integer.MIN_VALUE;
		} else {
			m = 0;
		}

		Point bestPoint = null;
		boolean isSomethingPlaced = false;

		for (int x = 0; x < getXAxisLength(); x++) {
			for (int y = 0; y < getYAxisLength(); y++) {
				if (getTile(x, y).isTileEmpty()) {

					isSomethingPlaced = true;

					Point p = new Point(x, y);

					setTile(p, turn);

					MinimaxResult r = basicNewMinimax(turn.getOppositeTileOwner(), depth + 1,
							0, 0, p);

					int oldM = m;

					if (turn == TileOwner.USER_1) {
						m = Math.min(m, r.getResult());
					} else  if (turn == TileOwner.USER_2) {
						m = Math.max(m, r.getResult());
					}

					if (m != oldM) {
						bestPoint = p;
					}

					setTile(p, TileOwner.NONE);

				}
			}
		}

		return new MinimaxResult(bestPoint, isSomethingPlaced? m : 0, false);

	}

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

		MinimaxResult r =  enhancedMinimax(turn, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, null);


		MinimaxResult rBasic =  basicNewMinimax(turn, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, null);
		if (r.equals(rBasic)) {
			System.out.println("not same");
			System.exit(0);
		}

		return r;
	}

//	todo add iswinnable check after winnable number of tiles are placed

	Game getGame();

	Tile getTile(int x, int y);

	void printBoard(int offset);

	boolean isGameWon(Point p, TileOwner turn);

	boolean setTile(Point p, TileOwner turn);

	//    helpers
	int getXAxisLength();

	int getYAxisLength();

	Tile[][] getTiles();

}
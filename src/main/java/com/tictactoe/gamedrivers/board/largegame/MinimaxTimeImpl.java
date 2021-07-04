package com.tictactoe.gamedrivers.board.largegame;

import com.tictactoe.gamedrivers.board.base.MinimaxBase;
import com.tictactoe.gamedrivers.minimax.MinimaxResult;
import com.tictactoe.gamedrivers.point.Point;
import com.tictactoe.gamedrivers.tile.Tile;
import com.tictactoe.gamedrivers.tile.TileOwner;

import java.util.ArrayList;
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

//					System.out.println("(" + x + ", " + y + ") -> (" + y + ", " + x + ")");

				if (getTile(x, y).getOwner() != getTile(y, x).getOwner()) {
//						System.out.println("NOT same");
					return false;
				}


			}
		}

		return true;

	}

	default MinimaxResult payload(TileOwner turn, int depth) throws InterruptedException {
//		Thread.sleep(2000); // Simulate some delay

		MinimaxResult minimaxResult = minimaxDriver(turn, depth, depth);

//		return minimaxResult;
		return minimaxResult;

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


	Map<Integer, Integer> abp = new HashMap<>();



	default MinimaxResult maxValue(int depth, Point prevPointPlaced) {
		printBoard(depth);
		if (prevPointPlaced != null) {
			if (isGameWon(prevPointPlaced, TileOwner.USER_1)) {
				printFormatted("-1", depth);
				System.out.println();
				setTile(prevPointPlaced, TileOwner.NONE);
				return new MinimaxResult(prevPointPlaced, -1, true);
			}
		}

		int m = Integer.MIN_VALUE;

		boolean isSomethingPlaced = false;
		Point bestPoint = null;

		for (int x = 0; x < getXAxisLength(); x++) {
			for (int y = 0; y < getYAxisLength(); y++) {
				if (getTile(x, y).isTileEmpty()) {

					isSomethingPlaced = true;

					Point p = new Point(x, y);

					setTile(p, TileOwner.USER_2);

					int oldM = m;

					MinimaxResult r = minValue(depth +1, p);

					m = Math.max(m, r.getResult());

					if (oldM != m) {
						printFormatted("new best " + p, depth);
						bestPoint = p;
					}

					setTile(p, TileOwner.NONE);

				}
			}
		}

		return new MinimaxResult(bestPoint, isSomethingPlaced ? m : 0, false);

	}

	default MinimaxResult minValue(int depth, Point prevPointPlaced) {
		printBoard(depth);

		if (prevPointPlaced != null) {
			if (isGameWon(prevPointPlaced, TileOwner.USER_2)) {
				printFormatted("1", depth);
				System.out.println();
				setTile(prevPointPlaced, TileOwner.NONE);
				return new MinimaxResult(prevPointPlaced, 1, true);
			}
		}

		int m = Integer.MAX_VALUE;

		boolean isSomethingPlaced = false;
		Point bestPoint = null;

		for (int x = 0; x < getXAxisLength(); x++) {
			for (int y = 0; y < getYAxisLength(); y++) {
				if (getTile(x, y).isTileEmpty()) {

					isSomethingPlaced = true;

					Point p = new Point(x, y);

					setTile(p, TileOwner.USER_1);

					int oldM = m;

					MinimaxResult r = maxValue(depth + 1, p);

					m = Math.min(m, r.getResult());

					if (oldM != m) {
						printFormatted("new best " + p, depth);
						bestPoint = p;
					}

					setTile(p, TileOwner.NONE);

				}
			}
		}

		return new MinimaxResult(bestPoint, isSomethingPlaced ? m : 0, false);

	}


	default MinimaxResult enhandedMinimax(TileOwner turn, int depth, int alpha, int beta, Point prevPointPlaced) {

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

					MinimaxResult r = enhandedMinimax(turn.getOppositeTileOwner(), depth + 1,
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


//		return maxValue(depth, null);


//		if (prevPointPlaced != null) {
//			if (isGameWon(prevPointPlaced, turn.getOppositeTileOwner())) {
//
//				int m = 0;
//
//				if (turn == TileOwner.USER_1) {
//					m = 1;
//				} else if (turn == TileOwner.USER_2) {
//					m = -1;
//				}
//				return new MinimaxResult(prevPointPlaced, m, true);
//			}
//		}
//
//		int m;
//
//		if (turn == TileOwner.USER_1) {
//			m = Integer.MAX_VALUE;
//		} else {
//			m = Integer.MIN_VALUE;
//		}
//
//		Point bestPoint = null;
//		boolean isSomethingPlaced = false;
//
//		for (int x = 0; x < getXAxisLength(); x++) {
//			for (int y = 0; y < getYAxisLength(); y++) {
//				if (getTile(x, y).isTileEmpty()) {
//
//					isSomethingPlaced = true;
//
//					Point p = new Point(x, y);
//
//					setTile(p, turn);
//
//					if (turn == TileOwner.USER_1) {
//						int oldM = m;
//
//						MinimaxResult r = enhandedMinimax(turn.getOppositeTileOwner(), depth + 1, alpha, m, p);
//
//						m  = Math.min(m, r.getResult());
//
//						if (oldM != m) {
//							bestPoint = p;
//						}
//
//					} else if (turn == TileOwner.USER_2) {
//						int oldM = m;
//
//						MinimaxResult r = enhandedMinimax(turn.getOppositeTileOwner(), depth + 1, m, beta, p);
//
//						m = Math.max(m, r.getResult());
//
//						if (oldM != m) {
//							bestPoint = p;
//						}
//
//					}
//
//					setTile(p, TileOwner.NONE);
//
//				}
//			}
//		}
//
//		return new MinimaxResult(bestPoint, isSomethingPlaced ? m : 0, false);


	}

//	default MinimaxResult enhandedMinimax(TileOwner turn, int depth, int alpha, int beta, Point prevPointPlaced) {
//
//		printBoard(depth);
//		System.out.println();
//
//		if (prevPointPlaced != null) {
//			if (isGameWon(prevPointPlaced, turn.getOppositeTileOwner())) {
//
//				int m = 0;
//
//				if (turn == TileOwner.USER_1) {
//					m = 1;
//				} else if (turn == TileOwner.USER_2) {
//					m = -1;
//				}
//				System.out.println(m);
//				return new MinimaxResult(prevPointPlaced, m, true);
//			}
//		}
//
//
//		int m;
//
//		if (turn == TileOwner.USER_1) {
//			m = beta;
//		} else {
//			m = alpha;
//		}
//
//		Point bestPoint = null;
//		boolean isSomethingPlaced = false;
//
//		for (int x = 0; x < getXAxisLength(); x++) {
//			for (int y = 0; y < getYAxisLength(); y++) {
//				if (getTile(x, y).isTileEmpty()) {
//
//					isSomethingPlaced = true;
//
//					Point p = new Point(x, y);
//
//					setTile(p, turn);
//
//					if (turn == TileOwner.USER_1) {
//						int oldM = m;
//
//						MinimaxResult r = enhandedMinimax(turn.getOppositeTileOwner(), depth + 1, alpha, m, p);
//
//						if (r.isSomethingPlaced()) {
//							m  = Math.min(m, r.getResult());
//
//
//							if (oldM != m) {
//								bestPoint = p;
//							}
//
//							if (m <= alpha) {
//								setTile(p, TileOwner.NONE);
//								return new MinimaxResult(p, alpha, false, true);
//							}
//
//						}
//
//
//					} else if (turn == TileOwner.USER_2) {
//						int oldM = m;
//
//						MinimaxResult r = enhandedMinimax(turn.getOppositeTileOwner(), depth + 1, m, beta, p);
//
//						if (r.isSomethingPlaced()) {
//							m = Math.max(m, r.getResult());
//
//							if (oldM != m) {
//								bestPoint = p;
//							}
//
//							if (m >= beta) {
//								setTile(p, TileOwner.NONE);
//								return new MinimaxResult(p, beta, false, true);
//							}
//
//						}
//
//					}
//
//					setTile(p, TileOwner.NONE);
//
//				}
//			}
//		}
//
//		return new MinimaxResult(bestPoint, isSomethingPlaced ? m : 0, false, isSomethingPlaced);
//
//
//	}

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

		MinimaxResult r =  enhandedMinimax(turn, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, null);

		System.out.println("r " + r);

		return r;
	}


/*
//		try {
//			return minimaxDriver(turn, depth, depth);
//
//		} catch (Exception e) {
//
//		}
//		System.out.println("time impl activated");
//
//		final ExecutorService service = Executors.newSingleThreadExecutor();
//
//		try {
//			final Future<Object> f = service.submit(() -> {
//
//				MinimaxResult payload = payload(turn, depth);
//
////				payload
//				return null;
//			});
//
////			todo extract time
//			System.out.println(f.get(3, TimeUnit.SECONDS));
//		} catch (final TimeoutException e) {
//			System.err.println("Calculation took to long");
//		} catch (final Exception e) {
//			throw new RuntimeException(e);
//		} finally {
//			service.shutdown();
//		}
//
////
//		checkForSymmetry();
		try {
			MinimaxResult r = minimaxDriver(turn, depth, depth);
		} catch (Exception e) {
		}
		return null;
 */


//	todo equivalent state check add
//	todo add iswinnable check after winnable number of tiles are placed

	/*
	 	ooox
	 	ooox
	 	ooox
	 	xxxx
	 	is same as
	 	xooo
	 	xooo
	 	xooo
	 	xxxx
	 */

	Point bestCurrMove = null;

	//	layer -> vals
	Map<Integer, List<Integer>> alphaBetaPruningTable = new HashMap<>();


	/**
	 * default depth = 1
	 * default currDepth = 1
	 */
	default MinimaxResult minimaxDriver(TileOwner turn, int depth, int currDepth) throws InterruptedException {

		int m;
//		todo catch

		Point bestPoint = null;

		if (turn == TileOwner.USER_2) {
			m = Integer.MIN_VALUE;
		} else {
			m = Integer.MAX_VALUE;
		}

//		todo alpha beta pruning

		boolean isSomethingPlaced = false;

		for (int x = 0; x < getXAxisLength(); x++) {
			for (int y = 0; y < getYAxisLength(); y++) {
				if (getTile(x, y).isTileEmpty()) {
					int sum = 0;
					isSomethingPlaced = true;

					Point p = new Point(x, y);

					setTile(p, turn);

					if (isGameWon(p, turn)) {


//						setTile(p, TileOwner.NONE);
//
//						return new MinimaxResult(p, m, true);

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
							MinimaxResult r = minimaxDriver(turn.getOppositeTileOwner(),
									depth + 1, currDepth + 1);

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
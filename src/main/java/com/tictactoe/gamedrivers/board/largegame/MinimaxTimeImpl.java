package com.tictactoe.gamedrivers.board.largegame;

import com.tictactoe.gamedrivers.board.base.MinimaxBase;
import com.tictactoe.gamedrivers.minimax.MinimaxResult;
import com.tictactoe.gamedrivers.point.Point;
import com.tictactoe.gamedrivers.tile.Tile;
import com.tictactoe.gamedrivers.tile.TileOwner;

public interface MinimaxTimeImpl extends MinimaxBase {

	default MinimaxResult payload(TileOwner turn, int depth) throws InterruptedException {
//		Thread.sleep(2000); // Simulate some delay

		MinimaxResult minimaxResult = minimaxDriver(turn, depth, depth);

//		return minimaxResult;
		return minimaxResult;

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
//
//		checkSymmetryMainDiagonal();

		return null;
	}

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

	default MinimaxResult minimaxDriver(TileOwner turn, int depth, int currDepth) throws InterruptedException {
		/**
		 * default depth = 1
		 * default currDepth = 1
		 */

//		if (currDepth == 1) {
//			bestCurrMove
//		}

		int m;
//		todo catch
//		int bestX = -1;
//		int bestY = -1;

//		todo remove random

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

//		todo alpha beta pruning

		boolean isSomethingPlaced = false;

		for (int x = 0; x < getXAxisLength(); x++) {
			for (int y = 0; y < getYAxisLength(); y++) {
				if (getTile(x, y).isTileEmpty()) {
					int sum = 0;
					isSomethingPlaced = true;

					if(depth == 1) {
						System.out.println("check one");
					}

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

//package com.tic_tac_toe.gamedrivers.minimax;
//
//import com.tic_tac_toe.gamedrivers.point.Point;
//import com.tic_tac_toe.gamedrivers.tile.Tile;
//import com.tic_tac_toe.gamedrivers.tile.TileOwner;
//
//public class BasicMinimax implements Minimax{
//
//	//	todo check if more than n elems are placed (needed for win)
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
//		for (int x = 0; x < getXAxisLength(); x++) {
//			for (int y = 0; y < getYAxisLength(); y++) {
////				if (tiles[y][x].isTileEmpty()) {
//				if (getTile(x, y).isTileEmpty()) {
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
//
//	@Override
//	public int getYAxisLength() {
//		System.out.println("wrong get y len impl");
//		return 0;
//	}
//
//	@Override
//	public int getXAxisLength() {
//		System.out.println("wrong get x len impl");
//		return 0;
//	}
//
//	@Override
//	public Tile getTile(int x, int y) {
//		System.out.println("wrong get tile impl");
//		return null;
//	}
//
//	@Override
//	public MinimaxResult getResult(TileOwner turn, int depth) {
//		return minimax(turn);
//	}
//}

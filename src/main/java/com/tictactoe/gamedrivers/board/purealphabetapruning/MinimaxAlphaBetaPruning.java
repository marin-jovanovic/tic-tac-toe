package com.tictactoe.gamedrivers.board.purealphabetapruning;

import com.tictactoe.gamedrivers.board.base.MinimaxBase;
import com.tictactoe.gamedrivers.minimax.MinimaxResult;
import com.tictactoe.gamedrivers.point.Point;
import com.tictactoe.gamedrivers.tile.Tile;
import com.tictactoe.gamedrivers.tile.TileOwner;

public interface MinimaxAlphaBetaPruning extends MinimaxBase {

	default MinimaxResult enhancedMinimax(TileOwner turn, int depth, int alpha, int beta, Point prevPointPlaced) {

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

					int oldM = m;

					if (turn == TileOwner.USER_1) {
						m = Math.min(m, r.getResult());

						if (m <= alpha) {
							setTile(p, TileOwner.NONE);
							return new MinimaxResult(bestPoint, alpha, false);
						}

					} else  if (turn == TileOwner.USER_2) {
						m = Math.max(m, r.getResult());

						if (m >= beta) {
							setTile(p, TileOwner.NONE);
							return new MinimaxResult(bestPoint, beta, false);
						}
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

	@Override
	default MinimaxResult minimax(TileOwner turn, int depth) {
		return enhancedMinimax(turn, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, null);
	}

	Tile getTile(int x, int y);

	boolean isGameWon(Point p, TileOwner turn);

	boolean setTile(Point p, TileOwner turn);

	//    helpers
	int getXAxisLength();

	int getYAxisLength();

}

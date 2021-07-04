package com.tictactoe.gamedrivers.board.base;

import com.tictactoe.gamedrivers.minimax.MinimaxResult;
import com.tictactoe.gamedrivers.point.Point;
import com.tictactoe.gamedrivers.tile.TileOwner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface MinimaxBase {

	List<Point> bestMovesLayerOne = new ArrayList<>();

	/**
	 * wrapper function for minimax
	 * @return computer move
	 */
	default Point computerMove() {

		bestMovesLayerOne.clear();

		MinimaxResult a = minimax(TileOwner.USER_2, 1);

		if (a == null) {
			return null;
		}

		if (a.getIsWinningMove()) {
			return a.getWhereTo();
		}

		bestMovesLayerOne.add(a.getWhereTo());
		Random rand = new Random();
		return bestMovesLayerOne.get(rand.nextInt(bestMovesLayerOne.size()));
	}

	default void printFormatted(String content, int depth) {
		StringBuilder s = new StringBuilder();

		for (int i = 0; i < depth ; i ++) {
			s.append(" ");
		}

		System.out.println(s + " " + content);
	};

	MinimaxResult minimax(TileOwner turn, int depth);

}

package com.tictactoe.gamedrivers.board.base;

import com.tictactoe.gamedrivers.minimax.MinimaxResult;
import com.tictactoe.gamedrivers.point.Point;
import com.tictactoe.gamedrivers.tile.TileOwner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface MinimaxBase {

			/*
		strategy desing patern

		interface if {
			racunajmax
		}

		class minimax impl if{
			@override
			racunajmax
		}

		class minimaxbolji impl if{
		}
		...

		class game {
			tip alg;

			game() {
				alg = minimax/minimaxnajbolji
			}

		}

			MinimaxResult a = alg.racunajmax(TileOwner.COMPUTER, 1);

		 */


	List<Point> bestMovesLayerOne = new ArrayList<>();

	/**
	 * wrapper function for minimax
	 * @return
	 */
	default Point computerMove() {

		bestMovesLayerOne.clear();

		MinimaxResult a = minimax(TileOwner.USER_2, 1);

		if (a == null) {
			System.out.println("result is null");
			return new Point(0, 0);
		}

		if (a.getIsWinningMove()) {
			return a.getWhereTo();
		}

		bestMovesLayerOne.add(a.getWhereTo());
		Random rand = new Random();
		Point randomElement = bestMovesLayerOne.get(rand.nextInt(bestMovesLayerOne.size()));
		return randomElement;
	}

	default void printFormatted(String tie, int depth) {
		StringBuilder s = new StringBuilder();

		for (int i = 0; i < depth ; i ++) {
			s.append(" ");
		}

		System.out.println(s + " " + tie);
	};

//todo change all to dp algs

	MinimaxResult minimax(TileOwner turn, int depth);

}

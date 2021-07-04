package com.tictactoe.gamedrivers.board.purealphabetapruning;

import com.tictactoe.gamedrivers.GameMode;
import com.tictactoe.gamedrivers.point.Point;

public class Game extends com.tictactoe.gamedrivers.board.base.Game implements MinimaxAlphaBetaPruning {

	public Game(int xAxisLength, int yAxisLength, GameMode gameMode) {
		super(xAxisLength, yAxisLength, gameMode);
	}

	@Override
	public Point getComputerMove() {
		return computerMove();
	}

}

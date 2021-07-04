package com.tictactoe.gamedrivers.board.largegame;

import com.tictactoe.gamedrivers.GameMode;
import com.tictactoe.gamedrivers.board.base.Game;
import com.tictactoe.gamedrivers.point.Point;

public class LargeGame extends Game implements MinimaxTimeImpl {

	public LargeGame(int xAxisLength, int yAxisLength, GameMode gameMode) {
		super(xAxisLength, yAxisLength, gameMode);
	}

	@Override
	public Point getComputerMove() {
		return computerMove();
	}

}

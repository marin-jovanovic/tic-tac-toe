package com.tictactoe.gamedrivers.board.smallgame;

import com.tictactoe.gamedrivers.GameMode;
import com.tictactoe.gamedrivers.board.base.Game;
import com.tictactoe.gamedrivers.point.Point;

public class SmallGame extends Game implements MinimaxBasicImplementation {


	public SmallGame(int xAxisLength, int yAxisLength, GameMode gameMode) {
		super(xAxisLength, yAxisLength, gameMode);
	}

	@Override
	public Point getComputerMove() {
		System.out.println("smallgame; computermove");
		return computerMove();
	}
}

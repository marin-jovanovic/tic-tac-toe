package com.tictactoe.gui.gamepanel.move;

import com.tictactoe.gamedrivers.board.Game;
import com.tictactoe.gamedrivers.point.Point;

public class ComputerMove extends Move {

	public ComputerMove(Game game) {
		super(game);
	}

	@Override
	public Point getMove() {
		return game.computerMove();
	}
}

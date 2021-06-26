package com.tictactoe.gui.gamepanel.move;

import com.tictactoe.gamedrivers.board.Game;
import com.tictactoe.gamedrivers.point.Point;

public class ComputerMove extends Move {

	public ComputerMove(Game game) {
		super(game);
	}

	@Override
	public Point makeMove() {

		System.out.println("computer move");

		Point computerMove = game.computerMove();

		System.out.println(computerMove);

		return computerMove;
	}
}

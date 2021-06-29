package com.tictactoe.gamedrivers.move;

import com.tictactoe.gamedrivers.board.base.Game;
import com.tictactoe.gamedrivers.point.Point;

public abstract class Move {
	Game game;

	Move(Game game) {
		this.game = game;
	}

	public abstract Point getMove();
}

package com.tictactoe.gui.gamepanel.move;

import com.tictactoe.gamedrivers.board.Game;
import com.tictactoe.gamedrivers.point.Point;

public abstract class Move {
	Game game;

	Move(Game game) {
		this.game = game;
	}

	public abstract Point makeMove();
}

package com.tictactoe.gamedrivers.move;

import com.tictactoe.gamedrivers.board.base.Game;
import com.tictactoe.gamedrivers.point.Point;

public class User2Move extends Move {

	public User2Move(Game game) {
		super(game);
	}

	@Override
	public Point getMove() {

		System.out.println("user 2 move");

		return null;
	}
}

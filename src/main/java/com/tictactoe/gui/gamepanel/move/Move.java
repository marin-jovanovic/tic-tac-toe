package com.tictactoe.gui.gamepanel.move;

import com.tictactoe.gamedrivers.board.Game;
import com.tictactoe.gamedrivers.board.MinimaxBase;
import com.tictactoe.gamedrivers.board.MinimaxBasicImplementation;
import com.tictactoe.gamedrivers.point.Point;
import com.tictactoe.gamedrivers.tile.Tile;
import com.tictactoe.gamedrivers.tile.TileOwner;

public abstract class Move {
	Game game;

	Move(Game game) {
		this.game = game;
	}

	public abstract Point getMove();
}

package com.tictactoe.gamedrivers.minimax;

import com.tictactoe.gamedrivers.tile.Tile;
import com.tictactoe.gamedrivers.tile.TileOwner;

public interface Minimax {

	int getYAxisLength();

	int getXAxisLength();

	Tile getTile(int x, int y);

	MinimaxResult getResult(TileOwner turn, int depth);

}

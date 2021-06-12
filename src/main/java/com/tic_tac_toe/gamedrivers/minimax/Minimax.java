package com.tic_tac_toe.gamedrivers.minimax;

import com.tic_tac_toe.gamedrivers.tile.Tile;
import com.tic_tac_toe.gamedrivers.tile.TileOwner;

public interface Minimax {

	 int getYAxisLength();

	int getXAxisLength();

	Tile getTile(int x, int y);

	MinimaxResult getResult(TileOwner turn, int depth);

}

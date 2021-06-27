package com.tictactoe.gamedrivers.tile;

public enum TileOwner {
	USER_2,
	USER_1,
	NONE;

	public TileOwner getOppositeTileOwner() {
		if (this == USER_1) {
			return USER_2;
		} else if (this == USER_2) {
			return USER_1;
		} else {
			return NONE;
		}
	}
}

package com.tic_tac_toe.gamedrivers.minimax;

import com.tic_tac_toe.gamedrivers.point.Point;

public class MinimaxResult {
	private final Point whereTo;
	private final int result;
	private final boolean isWinningMove;

	public MinimaxResult(Point whereTo, int result) {
		this.whereTo = whereTo;
		this.result = result;
		this.isWinningMove = false;
	}

	public MinimaxResult(Point whereTo, int result, boolean isWinningMove) {
		this.whereTo = whereTo;
		this.result = result;
		this.isWinningMove = isWinningMove;
	}

	@Override
	public String toString() {
		return whereTo + " -> " + result;
	}

	public int getResult() {
		return result;
	}

	public Point getWhereTo() {
		return whereTo;
	}

	public boolean getIsWinningMove() {
		return isWinningMove;
	}
}

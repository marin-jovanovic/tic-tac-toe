package com.tictactoe.gamedrivers.minimax;

import com.tictactoe.gamedrivers.point.Point;

public class MinimaxResult {
	private final Point whereTo;
	private final int result;
	private final boolean isWinningMove;

	public MinimaxResult(Point whereTo, int result, boolean isWinningMove) {
		this.whereTo = whereTo;
		this.result = result;
		this.isWinningMove = isWinningMove;
	}

//	public MinimaxResult(Point whereTo, int result, boolean isWinningMove, boolean isSomethingPlaced) {
//		this.whereTo = whereTo;
//		this.result = result;
//		this.isWinningMove = isWinningMove;
//		this.isSomethingPlaced = isSomethingPlaced;
//	}


//	private boolean isSomethingPlaced;
//
//	public boolean isSomethingPlaced() {
//		return isSomethingPlaced;
//	}

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

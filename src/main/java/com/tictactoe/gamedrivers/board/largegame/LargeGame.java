package com.tictactoe.gamedrivers.board.largegame;

import com.tictactoe.gamedrivers.board.base.Game;
import com.tictactoe.gamedrivers.point.Point;

public class LargeGame extends Game implements MinimaxTimeImpl {

	public LargeGame(int xAxisLength, int yAxisLength) {
		super(xAxisLength, yAxisLength);
	}

	@Override
	public Point getComputerMove() {
		System.out.println("largegame; computermove");
//		System.out.println("wrong computer move called; game");
		return computerMove();
	}

	//	for minimax
//	@Override
//	public boolean setTile(Point p, TileOwner tileOwner) {
//		if (p.getX() >= getXAxisLength()) {
//			System.out.println("x to big");
//			return false;
//		} else if (p.getY() >= getYAxisLength()) {
//			System.out.println("y to big");
//			return false;
//		}
//
//		getTile(p.getX(), p.getY()).setOwner(tileOwner);
////		tiles[p.getY()][p.getX()].setOwner(tileOwner);
//
//		return true;
//	}

}

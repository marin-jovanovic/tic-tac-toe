package com.tictactoe.gamedrivers.board.base;

import com.tictactoe.gamedrivers.GameMode;
import com.tictactoe.gamedrivers.Winnable;
import com.tictactoe.gamedrivers.point.Point;
import com.tictactoe.gamedrivers.tile.Tile;
import com.tictactoe.gamedrivers.tile.TileOwner;

public class Game implements Winnable {

	// solid principle, single principle,
	// interface sagregation
	/// more client specific  then general purpose


	@Override
	public String toString() {
		return "Game{" +
				"string: " + string+  "}";
	}

	private String string;

	private final boolean isUserX = true;
	private final Tile[][] tiles;
	private final int xAxisLength;
	private final int yAxisLength;

	private final GameMode gameMode;



	public Tile[][] getTiles() {
		return tiles;
	}

	public void stringify() {
		string = "";
		for (int y = 0; y < yAxisLength; y++) {
			for (int x = 0; x < xAxisLength; x++) {
				if (tiles[y][x].getOwner() == TileOwner.NONE) {
					string += "_";

				} else if (tiles[y][x].getOwner() == TileOwner.USER_1) {
					string += "x";

				}else if (tiles[y][x].getOwner() == TileOwner.USER_2) {
					string += "o";

				}
			}
			string += " ";
		}
	}

	public Game(int xAxisLength, int yAxisLength, GameMode gameMode) {

		this.xAxisLength = xAxisLength;
		this.yAxisLength = yAxisLength;

		this.gameMode = gameMode;

		this.tiles = new Tile[yAxisLength][xAxisLength];

		for (int y = 0; y < yAxisLength; y++) {
			for (int x = 0; x < xAxisLength; x++) {
				tiles[y][x] = new Tile();
			}
		}
	}

	public void restart() {
		for (int y = 0; y < yAxisLength; y++) {
			for (int x = 0; x < xAxisLength; x++) {
				tiles[y][x].setOwner(TileOwner.NONE);
			}
		}
	}

	//	for winnable interface

	@Override
	public int getXAxisLength() {
		return xAxisLength;
	}

	@Override
	public int getYAxisLength() {
		return yAxisLength;
	}

	@Override
	public Tile getTile(int x, int y) {
		return tiles[y][x];
	}

//	for minimax time implementation
	public Game getGame() {
		return this;
	}

	@Override
	public boolean isGameWon(Point p, TileOwner tileOwner) {
		return Winnable.super.isGameWon(p, tileOwner);
	}

	public boolean setTile(Point p, TileOwner tileOwner) {
		if (p.getX() >= getXAxisLength()) {
			System.out.println("x to big");
			return false;
		} else if (p.getY() >= getYAxisLength()) {
			System.out.println("y to big");
			return false;
		}

		getTile(p.getX(), p.getY()).setOwner(tileOwner);

		return true;
	}

	public void printBoard(int offset) {
		for (int y = 0; y < yAxisLength; y++) {
			StringBuilder buffer = new StringBuilder();

			for (int i = 0; i < offset; i++) {
				buffer.append("\t");
			}

			for (int x = 0; x < xAxisLength; x++) {
				if (isUserX) {
					if (tiles[y][x].getOwner() == TileOwner.USER_1) {
						buffer.append("x ");
					} else if (tiles[y][x].getOwner() == TileOwner.USER_2) {
						buffer.append("o ");
					} else if (tiles[y][x].getOwner() == TileOwner.NONE) {
						buffer.append(". ");
					}

				} else {
					if (tiles[y][x].getOwner() == TileOwner.USER_1) {
						buffer.append("o ");
					} else if (tiles[y][x].getOwner() == TileOwner.USER_2) {
						buffer.append("x ");
					} else if (tiles[y][x].getOwner() == TileOwner.NONE) {
						buffer.append(". ");
					}
				}
			}
			System.out.println(buffer);

		}
	}

	public Point getComputerMove() {
		System.out.println("wrong computer move called; game");
		return null;
	}
}

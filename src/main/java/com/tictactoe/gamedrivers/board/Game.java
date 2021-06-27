package com.tictactoe.gamedrivers.board;

import com.tictactoe.gamedrivers.GameMode;
import com.tictactoe.gamedrivers.Winnable;
import com.tictactoe.gamedrivers.point.Point;
import com.tictactoe.gamedrivers.tile.Tile;
import com.tictactoe.gamedrivers.tile.TileOwner;

public class Game implements Winnable, MinimaxBasicImplementation {
	/**
	 * 012
	 * 345
	 * 678
	 *
	 * 1, 4, 2
	 *
	 *
	 */

	// solid principle, single principle,
	// interface sagregation
	/// more client specific  then general purpose

	private final boolean isUserX = true;
	private final Tile[][] tiles;
	private final int xAxisLength;
	private final int yAxisLength;


	private final GameMode gameMode;

	public Game() {
		this.xAxisLength = 3;
		this.yAxisLength = 3;

//		todo
		this.gameMode = GameMode.USER_VS_COMPUTER;

		this.tiles = new Tile[yAxisLength][xAxisLength];

		for (int y = 0; y < yAxisLength; y++) {
			for (int x = 0; x < xAxisLength; x++) {
				tiles[y][x] = new Tile();
			}
		}
	}

	private static void printFormatted(Object val, int offset) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 1; i < offset; i++) {
			buffer.append("\t");
		}

		System.out.println(buffer.toString() + val);
	}

	public GameMode getGameMode() {
		return gameMode;
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

	@Override
	public boolean isGameWon(Point p, TileOwner tileOwner) {
		return Winnable.super.isGameWon(p, tileOwner);
	}

	@Override
	public boolean setTile(Point p, TileOwner tileOwner) {
		if (p.getX() >= xAxisLength) {
			System.out.println("x to big");
			return false;
		} else if (p.getY() >= yAxisLength) {
			System.out.println("y to big");
			return false;
		}

		tiles[p.getY()][p.getX()].setOwner(tileOwner);

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

	public void restart() {
		for (int y = 0; y < yAxisLength; y++) {
			for (int x = 0; x < xAxisLength; x++) {
				tiles[y][x].setOwner(TileOwner.NONE);
//				tiles[y][x] = new Tile();
			}
		}
	}
}

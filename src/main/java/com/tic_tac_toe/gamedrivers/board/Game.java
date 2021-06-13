package com.tic_tac_toe.gamedrivers.board;

import com.tic_tac_toe.gamedrivers.GameMode;
import com.tic_tac_toe.gamedrivers.Winnable;
import com.tic_tac_toe.gamedrivers.minimax.Minimax;
import com.tic_tac_toe.gamedrivers.point.Point;
import com.tic_tac_toe.gamedrivers.tile.Tile;
import com.tic_tac_toe.gamedrivers.tile.TileOwner;

public class Game implements Winnable, MinimaxBasicImplementation {
    // solid principle, single principle,
    // interface sagregation
    /// more client specific  then general purpose

    private final boolean isUserX = true;
    private final Tile[][] tiles;
    private final int xAxisLength;
    private final int yAxisLength;


//	for minimax
//	private List<Point> bestMovesLayerOne;
    private final GameMode gameMode;
    private Minimax minimaxAlgorithm;

    public Game() {
        this.xAxisLength = 3;
        this.yAxisLength = 3;

//		this.minimaxAlgorithm = new BasicMinimax();

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
                    } else if (tiles[y][x].getOwner() == TileOwner.COMPUTER) {
                        buffer.append("o ");
                    } else if (tiles[y][x].getOwner() == TileOwner.NONE) {
                        buffer.append(". ");
                    }

                } else {
                    if (tiles[y][x].getOwner() == TileOwner.USER_1) {
                        buffer.append("o ");
                    } else if (tiles[y][x].getOwner() == TileOwner.COMPUTER) {
                        buffer.append("x ");
                    } else if (tiles[y][x].getOwner() == TileOwner.NONE) {
                        buffer.append(". ");
                    }
                }
            }
            System.out.println(buffer);

        }
    }

}

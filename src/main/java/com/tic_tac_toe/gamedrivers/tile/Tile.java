package com.tic_tac_toe.gamedrivers.tile;

public class Tile {
    private TileOwner tileOwner;

    public Tile() {
        this.tileOwner = TileOwner.NONE;
    }

    public TileOwner getOwner() {
        return tileOwner;
    }

    public void setOwner(TileOwner tileOwner) {
        this.tileOwner = tileOwner;
    }

    public boolean isTileEmpty() {
        return this.tileOwner == TileOwner.NONE;
    }


}

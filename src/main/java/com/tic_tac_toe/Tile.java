package com.tic_tac_toe;

public class Tile {
	private Owner owner;

	public Tile() {
		this.owner = Owner.NONE;
	}

	public Owner getOwner() {
		return owner;
	}

	/**
	 * @param owner who owns this tile
	 * @return true if field is updated else false
	 */
	public boolean setOwner(Owner owner) {
//		if (this.owner != com.tic_tac_toe.Owner.NONE) {
//			System.out.println("owner already set, err");
//			return false;
//		}

		this.owner = owner;
		return true;
	}

	public boolean isTileEmpty() {
		return this.owner == Owner.NONE;
	}


}

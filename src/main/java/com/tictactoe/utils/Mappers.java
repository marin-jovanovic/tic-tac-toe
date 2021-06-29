package com.tictactoe.utils;

import com.tictactoe.eventhandler.example.EventSubtype;
import com.tictactoe.gamedrivers.tile.TileOwner;


/**
 * convert from one type to another, ad hoc solutions for now
 */
public class Mappers {
	

	public static EventSubtype mapTileOwnerToEventSubType(TileOwner tileOwner) {
		if (tileOwner == TileOwner.USER_1) {
			return EventSubtype.USER_1;
		} else if (tileOwner == TileOwner.USER_2) {
			return EventSubtype.USER_2;
		} else {
			throw new IllegalArgumentException("unknown tile owner");
		}
	}

}

package com.tictactoe.gui.gamepanel.gamemode;

import com.tictactoe.Utils;
import com.tictactoe.eventhandler.EventListener;
import com.tictactoe.eventhandler.EventManager;
import com.tictactoe.eventhandler.EventType;
import com.tictactoe.eventhandler.example.EventSubtype;
import com.tictactoe.gamedrivers.board.Game;
import com.tictactoe.gamedrivers.point.Point;
import com.tictactoe.gamedrivers.tile.TileOwner;
import com.tictactoe.gui.gamepanel.GamePanel;
import com.tictactoe.gui.gamepanel.TileButton;
import com.tictactoe.gui.gamepanel.move.Move;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GameModeBasic implements EventManager {
	static TileOwner turn;
	static String tileState = "o";
	GamePanel gamePanel;
	Game game;
	Move move;
	Map<EventType, List<EventListener>> listeners;

	boolean IS_GAME_WINNABLE_HINTING_ENABLED_CONSTANT = true;

	GameModeBasic(GamePanel gamePanel, Game game) {
		listeners = new HashMap<>();

		this.gamePanel = gamePanel;

		turn = TileOwner.USER_1;

		this.game = game;
	}

	static void switchTileState() {
		if (tileState.equals("o")) {
			tileState = "x";
		} else {
			tileState = "o";
		}
	}

	static void changeTurn() {
		if (turn == TileOwner.USER_1) {
			turn = TileOwner.USER_2;
		} else if (turn == TileOwner.USER_2) {
			turn = TileOwner.USER_1;
		}
	}

	@Override
	public Map<EventType, List<EventListener>> getListeners() {
		return listeners;
	}

	EventSubtype mapTileOwnerToEventSubType(TileOwner tileOwner) {
		if (tileOwner == TileOwner.USER_1) {
			return EventSubtype.USER_1;
		} else if (tileOwner == TileOwner.USER_2) {
			return EventSubtype.USER_2;
		} else {
			throw new IllegalArgumentException("unknown tile owner");
		}
	}

	void checkGameWon(Point point) {

		if (game.isGameWon(point, turn)) {
			System.out.println("game won " + turn);

			notify(EventType.GAME_ENDED, mapTileOwnerToEventSubType(turn));
		}
	}

	void clickButton(TileButton button, String icon) {
		button.setEnabled(false);
		button.setDisabledIcon(Utils.getImageIcon(icon));
		button.setIcon(Utils.getImageIcon(icon));
	}

	public void setMove(Move move) {
		this.move = move;
	}

}

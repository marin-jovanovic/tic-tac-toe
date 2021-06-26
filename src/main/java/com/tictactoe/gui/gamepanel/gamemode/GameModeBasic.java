package com.tictactoe.gui.gamepanel.gamemode;

import com.tictactoe.Utils;
import com.tictactoe.gamedrivers.board.Game;
import com.tictactoe.gamedrivers.point.Point;
import com.tictactoe.gamedrivers.tile.TileOwner;
import com.tictactoe.gui.gamepanel.GamePanel;
import com.tictactoe.gui.gamepanel.TileButton;
import com.tictactoe.gui.gamepanel.move.Move;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class GameModeBasic {
	static TileOwner turn;

	GamePanel gamePanel;
	Game game;
	Move move;

	static String tileState = "o";

	static void switchTileState() {
		if (tileState.equals("o")) {
			tileState = "x";
		} else {
			tileState = "o";
		}
	}

	GameModeBasic(GamePanel gamePanel) {
		this.gamePanel = gamePanel;

		turn = TileOwner.USER_1;
	}

	GameModeBasic( GamePanel gamePanel, Game game) {
		this(gamePanel);

		this.game = game;
	}

	static void changeTurn() {
		if (turn == TileOwner.USER_1) {
			turn = TileOwner.COMPUTER;
		} else if (turn == TileOwner.COMPUTER) {
			turn = TileOwner.USER_1;
		}
	}

	void checkGameWon(Point point) {
		if (game.isGameWon(point, turn)) {
			System.out.println("game won " + turn);

//				gamePanel.notify(EventType.GAME_ENDED, "data todo");
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

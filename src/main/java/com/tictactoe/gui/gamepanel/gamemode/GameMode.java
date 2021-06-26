package com.tictactoe.gui.gamepanel.gamemode;

import com.tictactoe.Utils;
import com.tictactoe.gamedrivers.board.Game;
import com.tictactoe.gamedrivers.point.Point;
import com.tictactoe.gamedrivers.tile.TileOwner;
import com.tictactoe.gui.gamepanel.GamePanel;
import com.tictactoe.gui.gamepanel.move.Move;
import com.tictactoe.gui.gamepanel.TileButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class GameMode implements ActionListener {
	static TileOwner turn;
	TileButton button;
	GamePanel gamePanel;
	Game game;
	Move move;

	public void setButton(TileButton button) {
		this.button = button;
	}

	static String tileState = "o";

	static void switchTileState() {
		if (tileState.equals("o")) {
			tileState = "x";
		} else {
			tileState = "o";
		}
	}

	GameMode(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		turn = TileOwner.USER_1;
	}

	GameMode(TileButton button, GamePanel gamePanel, Game game) {
		this(gamePanel);

		this.button = button;

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

	@Override
	public void actionPerformed(ActionEvent e) {

		clickButton(this.button, tileState);
		switchTileState();

//			logic
		game.setTile(button.getPoint().reverse(), turn);

		game.printBoard(0);

		checkGameWon(button.getPoint().reverse());

		changeTurn();

		Point computerMove = move.getMove();

		if (computerMove != null) {
//			logic
			game.setTile(computerMove, turn);

			TileButton button = (TileButton) gamePanel.getButton(computerMove.getX(), computerMove.getY());

			clickButton(button, tileState);
			switchTileState();

//            todo determinate which line (horizontal, diagonal ...) should be placed over tiles

			checkGameWon(computerMove);

			changeTurn();

			game.printBoard(0);
			System.out.println();

		}

	}
}

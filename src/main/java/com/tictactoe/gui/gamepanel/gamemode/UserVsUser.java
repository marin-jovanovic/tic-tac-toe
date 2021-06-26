package com.tictactoe.gui.gamepanel.gamemode;

import com.tictactoe.gamedrivers.board.Game;
import com.tictactoe.gui.gamepanel.GamePanel;
import com.tictactoe.gui.gamepanel.TileButton;
import com.tictactoe.gui.gamepanel.move.User2Move;

import java.awt.event.ActionEvent;

public class UserVsUser extends GameMode {

	static String tileState = "o";

	public UserVsUser(TileButton button, GamePanel gamePanel, Game game) {
		super(button, gamePanel, game);

		setMove(new User2Move(game));
	}

	static void switchTileState() {
		if (tileState.equals("o")) {
			tileState = "x";
		} else {
			tileState = "o";
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		clickButton(this.button, tileState);

//			logic
		game.setTile(button.getPoint().reverse(), turn);

		game.printBoard(0);

		checkGameWon(button.getPoint().reverse());

		switchTileState();

		changeTurn();

		System.out.println();

//			Point computerMove = move.getMove();
//
////			logic
//			game.setTile(computerMove, TileOwner.COMPUTER);
//
//			TileButton button = (TileButton) gamePanel.getButton(computerMove.getX(), computerMove.getY());
//
//			clickButton(button, "x");
//
////            todo determinate which line (horizontal, diagonal ...) should be placed over tiles
//
//			checkGameWon(computerMove);
//
//			changeTurn();
//
//			game.printBoard(0);
//			System.out.println();
	}

}

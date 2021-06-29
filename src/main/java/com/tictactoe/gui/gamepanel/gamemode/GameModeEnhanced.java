package com.tictactoe.gui.gamepanel.gamemode;

import com.tictactoe.gamedrivers.point.Point;
import com.tictactoe.gamedrivers.tile.TileOwner;
import com.tictactoe.gui.gamepanel.TileButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameModeEnhanced implements ActionListener {
	TileButton button;
	GameModeBasic gameModeBasic;

	public GameModeEnhanced(GameModeBasic gameModeBasic, TileButton button) {
		super();
		this.gameModeBasic = gameModeBasic;
		this.button = button;
	}

	//	fixme
	public void setButton(TileButton button) {
		this.button = button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		gameModeBasic.clickButton(this.button, GameModeBasic.tileState);

//			logic
		gameModeBasic.game.setTile(button.getPoint(), GameModeBasic.turn);

		gameModeBasic.checkGameWon(button.getPoint());


		GameModeBasic.switchTileState();
		gameModeBasic.game.printBoard(0);
		System.out.println();

		GameModeBasic.changeTurn();

		Point computerMove = gameModeBasic.move.getMove();

		if (computerMove != null) {
//			logic
			gameModeBasic.game.setTile(computerMove, GameModeBasic.turn);

			TileButton button = (TileButton) gameModeBasic.gamePanel.getButton(computerMove.getX(),
					computerMove.getY());


			gameModeBasic.clickButton(button, GameModeBasic.tileState);
			GameModeBasic.switchTileState();

//            todo determinate which line (horizontal, diagonal ...) should be placed over tiles

			gameModeBasic.checkGameWon(computerMove);

			GameModeBasic.changeTurn();

			gameModeBasic.game.printBoard(0);
			System.out.println();

		}

		if (gameModeBasic.IS_GAME_WINNABLE_HINTING_ENABLED_CONSTANT) {
			System.out.println("checking if win or los can be achieved");

//			gameModeBasic.game.canIWin()

			System.out.println("can i win? " + gameModeBasic.game.canIWin(TileOwner.USER_1));

			System.out.println("------------------------------------------------------------------------------------");

//			iterate over all combination for user1 and then check for user2 with this
			System.out.println("can i lose? " + gameModeBasic.game.canIWin(TileOwner.USER_2));

		}
	}
}

package com.tictactoe.gui.gamepanel.gamemode;

import com.tictactoe.gamedrivers.board.Game;
import com.tictactoe.gui.gamepanel.GamePanel;
import com.tictactoe.gui.gamepanel.move.ComputerMove;

public class UserVsComputerBasic extends GameModeBasic {

	public UserVsComputerBasic(GamePanel gamePanel, Game game) {
		super(gamePanel, game);

		setMove(new ComputerMove(game));
	}

}

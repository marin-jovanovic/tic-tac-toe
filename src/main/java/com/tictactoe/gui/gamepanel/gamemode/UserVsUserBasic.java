package com.tictactoe.gui.gamepanel.gamemode;

import com.tictactoe.gamedrivers.board.base.Game;
import com.tictactoe.gui.gamepanel.GamePanel;
import com.tictactoe.gamedrivers.move.User2Move;

public class UserVsUserBasic extends GameModeBasic {
	public UserVsUserBasic(GamePanel gamePanel, Game game) {
		super(gamePanel, game);

		setMove(new User2Move(game));
	}

}

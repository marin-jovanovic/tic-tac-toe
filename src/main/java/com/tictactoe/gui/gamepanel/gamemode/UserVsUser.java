package com.tictactoe.gui.gamepanel.gamemode;

import com.tictactoe.gamedrivers.board.Game;
import com.tictactoe.gui.gamepanel.GamePanel;
import com.tictactoe.gui.gamepanel.TileButton;
import com.tictactoe.gui.gamepanel.move.User2Move;

public class UserVsUser extends GameMode {

	public UserVsUser(TileButton button, GamePanel gamePanel, Game game) {
		super(button, gamePanel, game);

		setMove(new User2Move(game));
	}

}

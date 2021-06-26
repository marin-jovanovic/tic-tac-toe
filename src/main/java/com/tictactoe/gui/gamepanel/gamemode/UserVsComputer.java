package com.tictactoe.gui.gamepanel.gamemode;

import com.tictactoe.gamedrivers.board.Game;
import com.tictactoe.gui.gamepanel.move.ComputerMove;
import com.tictactoe.gui.gamepanel.GamePanel;
import com.tictactoe.gui.gamepanel.TileButton;

public class UserVsComputer extends GameMode {

	public UserVsComputer(TileButton button, GamePanel gamePanel, Game game) {
		super(button, gamePanel, game);

		setMove(new ComputerMove(game));
	}

}

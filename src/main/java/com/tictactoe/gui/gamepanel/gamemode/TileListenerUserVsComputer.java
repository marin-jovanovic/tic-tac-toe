package com.tictactoe.gui.gamepanel.gamemode;

import com.tictactoe.Utils;
import com.tictactoe.gamedrivers.board.Game;
import com.tictactoe.gamedrivers.point.Point;
import com.tictactoe.gamedrivers.tile.TileOwner;
import com.tictactoe.gui.gamepanel.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TileListenerUserVsComputer implements ActionListener {
	private final Point point;
	private final Game game;
	private final GamePanel gui;

	public TileListenerUserVsComputer(Game game, Point point, GamePanel gamePanel) {
		this.point = point;
		this.game = game;
		this.gui = gamePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		game.setTile(this.point, TileOwner.USER_1);

		JButton thisButton = gui.getButton(this.point.getX(), this.point.getY());
		thisButton.setEnabled(false);
		thisButton.setDisabledIcon(Utils.getImageIcon("o"));
		thisButton.setIcon(Utils.getImageIcon("o"));

		if (game.isGameWon(this.point, TileOwner.USER_1)) {
			System.out.println("game won");
//				todo fire event
			this.gui.disableButtons();
		}

		System.out.println();

		Point computerMove = game.computerMove();

		game.setTile(new Point(computerMove.getX(), computerMove.getY()), TileOwner.COMPUTER);
		JButton button = gui.getButton(computerMove.getX(), computerMove.getY());
		button.setEnabled(false);
		button.setIcon(Utils.getImageIcon("x"));
		button.setDisabledIcon(Utils.getImageIcon("x"));

//            todo determinate which line (horizontal, diagonal ...) should be placed over tiles

		if (game.isGameWon(computerMove, TileOwner.COMPUTER)) {
			System.out.println("game lost");
//				todo fire event
			this.gui.disableButtons();
		}

		game.printBoard(0);
	}
}

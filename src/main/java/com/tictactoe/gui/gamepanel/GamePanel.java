package com.tictactoe.gui.gamepanel;

import com.tictactoe.Utils;
import com.tictactoe.eventhandler.EventListener;
import com.tictactoe.eventhandler.EventManager;
import com.tictactoe.eventhandler.EventType;
import com.tictactoe.gamedrivers.board.Game;
import com.tictactoe.gamedrivers.point.Point;
import com.tictactoe.gamedrivers.tile.TileOwner;
import com.tictactoe.gui.gamepanel.gamemode.TileListenerUserVsComputer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamePanel extends JPanel implements EventManager, EventListener {
// todo buttons to factory
// every design pattern likes solid principles

	private Game game;
	private final TileButton[][] buttons;

	ActionListener tileListener;

	public Game getGame() {
		return game;
	}

	void initLogic() {
		game = new Game();
	}

	abstract class GameMode implements ActionListener {
		TileButton button;

		TileOwner turn;

		GamePanel gamePanel;

		GameMode(GamePanel gamePanel) {
			this.gamePanel = gamePanel;
		}

		GameMode(TileButton button, GamePanel gamePanel) {
			this.button = button;

			this.gamePanel = gamePanel;

			turn = TileOwner.USER_1;
		}

		void checkGameWon() {

			if (game.isGameWon(button.getPoint(), turn)) {
				System.out.println("game won " + turn);
//				todo fire event
//				this.gui.disableButtons();
				gamePanel.notify(EventType.GAME_ENDED, "data todo");
			}
		}

		void changeTurn() {
			if (turn == TileOwner.USER_1) {
				turn = TileOwner.COMPUTER;
			} else if (turn == TileOwner.COMPUTER) {
				turn = TileOwner.USER_1;
			}
		}
	}

	class UserVsComputer extends GameMode {

		UserVsComputer(TileButton button, GamePanel gamePanel) {
			super(button, gamePanel);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.button.setEnabled(false);
			this.button.setDisabledIcon(Utils.getImageIcon("o"));
			this.button.setIcon(Utils.getImageIcon("o"));

//			logic
			game.setTile(button.getPoint().reverse(), TileOwner.USER_1);

			checkGameWon();

			changeTurn();

			System.out.println("computer move");

			Point computerMove = game.computerMove();

			System.out.println(computerMove);
//			logic
			game.setTile(computerMove, TileOwner.COMPUTER);

			JButton button = gamePanel.getButton(computerMove.getX(), computerMove.getY());
			button.setEnabled(false);
			button.setIcon(Utils.getImageIcon("x"));
			button.setDisabledIcon(Utils.getImageIcon("x"));

//            todo determinate which line (horizontal, diagonal ...) should be placed over tiles

			checkGameWon();

			changeTurn();

			game.printBoard(0);
		}
	}

	class UserVsUser extends GameMode {

		UserVsUser(TileButton button, GamePanel gamePanel) {
			super(button, gamePanel);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.button.setEnabled(false);
			this.button.setDisabledIcon(Utils.getImageIcon("o"));
			this.button.setIcon(Utils.getImageIcon("o"));

			System.out.println("user2 move");
		}
	}


	public GamePanel() {
		listeners = new HashMap<>();

		initLogic();

//        map logic to gui fields
		int yLength = game.getYAxisLength();
		int xLength = game.getXAxisLength();

		setLayout(new GridLayout(0, game.getXAxisLength()));

//        init buttons

		buttons = new TileButton[yLength][xLength];

		for (int y = 0; y < yLength; y++) {
			for (int x = 0; x < xLength; x++) {

//				todo check if user vs computer or user vs user
				buttons[y][x] = new TileButton(new Point(y, x));

////                determinate game mode
				GameMode gameMode = new UserVsComputer(buttons[y][x], this);

				buttons[y][x].addActionListener(gameMode);
				this.add(buttons[y][x]);

			}
		}


	}

	public JButton getButton(int x, int y) {
		return buttons[y][x];
	}

	Map<EventType, List<EventListener>> listeners;

	@Override
	public Map<EventType, List<EventListener>> getListeners() {
		return listeners;
	}

	@Override
	public void update(String action) {
		System.out.println("restart; " + action);

		newGame();
	}

	void newGame() {
		restartButtons();
		game = new Game();
	}

	void restartButtons() {
		for (int y = 0; y < game.getYAxisLength(); y++) {
			for (int x = 0; x < game.getXAxisLength(); x++) {
				buttons[y][x].setEnabled(true);
				buttons[y][x].setText(x + " " + y);
				buttons[y][x].setIcon(null);
			}
		}
	}

	enum Command {
		GAME_WON_PLAYER_1,
		GAME_WON_PLAYER_2
	}

	public void disableButtons() {
		for (int y = 0; y < game.getYAxisLength(); y++) {
			for (int x = 0; x < game.getXAxisLength(); x++) {
				buttons[y][x].setEnabled(false);
			}
		}
	}

}

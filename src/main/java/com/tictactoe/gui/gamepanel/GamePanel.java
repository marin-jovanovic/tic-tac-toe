package com.tictactoe.gui.gamepanel;

import com.tictactoe.eventhandler.EventListener;
import com.tictactoe.eventhandler.EventType;
import com.tictactoe.eventhandler.example.EventSubtype;
import com.tictactoe.gamedrivers.GameMode;
import com.tictactoe.gamedrivers.board.base.Game;
import com.tictactoe.gamedrivers.board.largegame.LargeGame;
import com.tictactoe.gamedrivers.board.smallgame.SmallGame;
import com.tictactoe.gamedrivers.point.Point;
import com.tictactoe.gui.gamepanel.gamemode.GameModeBasic;
import com.tictactoe.gui.gamepanel.gamemode.GameModeEnhanced;
import com.tictactoe.gui.gamepanel.gamemode.UserVsComputerBasic;
import com.tictactoe.gui.gamepanel.gamemode.UserVsUserBasic;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamePanel extends JPanel implements EventListener {

	/**
	 * 00
	 * 02
	 * 20
	 * 11
	 */

	private final TileButton[][] buttons;
	Map<EventType, List<EventListener>> listeners;
	int yLength;
	int xLength;
	GameModeBasic gameModeBasic;
	private Game game;

	public GamePanel() {
		listeners = new HashMap<>();

//		create new game
		initLogic();

//      map logic to gui fields
		yLength = game.getYAxisLength();
		xLength = game.getXAxisLength();

		setLayout(new GridLayout(0, game.getXAxisLength()));

//        init buttons
		buttons = new TileButton[yLength][xLength];

		gameModeBasic = new UserVsComputerBasic(this, game);
//		gameModeBasic = new UserVsUserBasic(this, game);


		switchGameMode(gameModeBasic);

		gameModeBasic.subscribe(EventType.GAME_ENDED, this);
	}

	public GameModeBasic getGameModeBasic() {
		return gameModeBasic;
	}

	void switchGameMode(GameModeBasic gameModeBasic) {
		for (int y = 0; y < yLength; y++) {
			for (int x = 0; x < xLength; x++) {

				buttons[y][x] = new TileButton(new Point(x, y));

				GameModeEnhanced gameMode = new GameModeEnhanced(gameModeBasic, buttons[y][x]);

				buttons[y][x].addActionListener(gameMode);

				this.add(buttons[y][x]);

			}
		}

	}

	public Game getGame() {
		return game;
	}

	void initLogic() {

//		todo define x and y lens

		game = new LargeGame(5, 7, GameMode.USER_VS_COMPUTER);
//		game = new LargeGame(4, 4);
//
//		game = new SmallGame(3, 3);

//		game = new Game();
	}

	public JButton getButton(int x, int y) {
		return buttons[y][x];
	}

	@Override
	public Map<EventType, List<EventListener>> getListeners() {
		return listeners;
	}

	@Override
	public void update(EventSubtype eventSubtype) {

		if (eventSubtype == EventSubtype.TIE ||
				eventSubtype == EventSubtype.USER_1 ||
				eventSubtype == EventSubtype.USER_2) {

			disableButtons();

		} else if (eventSubtype == EventSubtype.NONE) {
			System.out.println("restart; " + eventSubtype);

			notify(EventType.NEW_GAME, EventSubtype.NONE);

			newGame();
		} else {
			throw new IllegalArgumentException("unknown command");
		}

	}

	void newGame() {
		restartButtons();

		System.out.println(game);

		game.restart();
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

	public void disableButtons() {
		for (int y = 0; y < game.getYAxisLength(); y++) {
			for (int x = 0; x < game.getXAxisLength(); x++) {
				buttons[y][x].setEnabled(false);
			}
		}
	}

}

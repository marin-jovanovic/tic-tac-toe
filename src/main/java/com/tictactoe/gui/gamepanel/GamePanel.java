package com.tictactoe.gui.gamepanel;

import com.tictactoe.eventhandler.EventListener;
import com.tictactoe.eventhandler.EventType;
import com.tictactoe.gamedrivers.board.Game;
import com.tictactoe.gamedrivers.point.Point;
import com.tictactoe.gui.gamepanel.gamemode.GameMode;
import com.tictactoe.gui.gamepanel.gamemode.UserVsComputer;
import com.tictactoe.gui.gamepanel.gamemode.UserVsUser;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamePanel extends JPanel implements EventListener {

	private final TileButton[][] buttons;
	Map<EventType, List<EventListener>> listeners;
	private Game game;

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

				buttons[y][x] = new TileButton(new Point(y, x));

//              determinate game mode
				GameMode gameMode = new UserVsComputer(buttons[y][x], this, game);
//				GameMode gameMode = new UserVsUser(buttons[y][x], this, game);

				buttons[y][x].addActionListener(gameMode);

				this.add(buttons[y][x]);

			}
		}
	}

	public Game getGame() {
		return game;
	}

	void initLogic() {
		game = new Game();
	}

	public JButton getButton(int x, int y) {
		return buttons[y][x];
	}

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

	public void disableButtons() {
		for (int y = 0; y < game.getYAxisLength(); y++) {
			for (int x = 0; x < game.getXAxisLength(); x++) {
				buttons[y][x].setEnabled(false);
			}
		}
	}

}

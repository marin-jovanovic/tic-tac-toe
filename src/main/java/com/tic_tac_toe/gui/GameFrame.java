package com.tic_tac_toe.gui;

import com.tic_tac_toe.gamedrivers.Game;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameFrame extends JFrame {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(GameFrame::new);
	}

	private Game game;
	private final JButton[][] buttons;
	private final JButton restartButton;

	public GameFrame() {
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		game = new Game();
		int yLength = game.getYAxisLength();
		int xLength = game.getXAxisLength();

		setLayout(new GridLayout(0, game.getXAxisLength()));
//		todo
		setSize(500,500);

		buttons = new JButton[yLength][xLength];

		for (int y = 0; y < yLength; y++) {
			for (int x = 0; x < xLength; x++) {

//				todo check if user vs computer or user vs user
				buttons[y][x] = new TileButton(x + " " + y);
				this.add(buttons[y][x]);

			}
		}

		restartButton = new RestartButton();
		add(restartButton);

	}

//	public ImageIcon getImageIcon(String val) {
//		return new ImageIcon(Objects.requireNonNull(TestingGui.class.getResource("/images/" + val + ".png")));
//	}

}

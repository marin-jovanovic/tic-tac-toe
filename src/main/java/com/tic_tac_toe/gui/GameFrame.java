package com.tic_tac_toe.gui;

import com.tic_tac_toe.Utils;
import com.tic_tac_toe.gamedrivers.board.Game;
import com.tic_tac_toe.gamedrivers.point.Point;
import com.tic_tac_toe.gamedrivers.tile.TileOwner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

//public class GameFrame implements PropertyChangeListener extends JFrame {
public class GameFrame extends JFrame implements PropertyChangeListener {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(GameFrame::new);
	}

	private final PropertyChangeSupport support;

	private Game game;
	private final TileButton[][] buttons;
	private final JButton restartButton;
	private final JTextField jTextField;
	ActionListener tileListener;

	public JButton getButton(int x, int y) {
		return buttons[y][x];
	}

	public GameFrame() {
		support = new PropertyChangeSupport(this);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		game = new Game();
		int yLength = game.getYAxisLength();
		int xLength = game.getXAxisLength();

//		game mode determinated
//		tileListener = new TileListenerUserVsComputer();

		setLayout(new GridLayout(0, game.getXAxisLength()));
//		todo
		setSize(500,500);

		buttons = new TileButton[yLength][xLength];

		for (int y = 0; y < yLength; y++) {
			for (int x = 0; x < xLength; x++) {

//				todo check if user vs computer or user vs user
//				buttons[y][x] = new TileButton(x + " " + y);
				buttons[y][x] = new TileButton(game, new Point(y, x));

				buttons[y][x].addActionListener(new TileListenerUserVsComputer(game, new Point(x, y), this));

//				this.addListener(buttons[x][y]);
				buttons[y][x].addListener(this);


//				buttons[y][x].addActionListener(new TileListener(game, new Point(y, x)));

//				buttons[y][x].addActionListener(e -> {
//					game.setTile(new Point(x, y), TileOwner.USER_1);
//
//					//			set tile in logic board
//					game.printBoard(0);
//				});

				this.add(buttons[y][x]);

			}
		}

		restartButton = new RestartButton();
		add(restartButton);

		jTextField = new JTextField();
		add(jTextField);

	}

	enum Command {
		GAME_WON_PLAYER_1,
		GAME_WON_PLAYER_2
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("property change in gameframe");
		System.out.println(evt.getNewValue());
//		if (evt.getNewValue() == Command.RESTART_MAINFRAME) {
//
//			restartSequence();
//
//		} else {
//			System.out.println("non good var in mainframe ");
//		}


	}


	public void addListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	public void removeListener(PropertyChangeListener propertyChangeListener) {
		support.removePropertyChangeListener(propertyChangeListener);
	}



	class TileListenerUserVsComputer implements ActionListener {
		private Point point;
		private Game game;
		private GameFrame gui;

		TileListenerUserVsComputer(Game game, Point point, GameFrame gameFrame) {
			this.point = point;
			this.game = game;
			this.gui = gameFrame;
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
//				jTextField.setText("VICTORY");
				for (int y = 0; y < game.getYAxisLength(); y++) {
					for (int x = 0; x < game.getXAxisLength(); x++) {
						this.gui.getButton(x, y).setEnabled(false);
						this.gui.getButton(x, y).setIcon(null);
					}
				}
			}

			System.out.println();

			Point computerMove = game.computerMove();

			game.setTile(new Point(computerMove.getX(), computerMove.getY()), TileOwner.COMPUTER);
			JButton button = gui.getButton(computerMove.getX(), computerMove.getY());
			button.setEnabled(false);
			button.setIcon(Utils.getImageIcon("x"));
			button.setDisabledIcon(Utils.getImageIcon("x"));

			if (game.isGameWon(this.point, TileOwner.COMPUTER)) {
				System.out.println("game lost");
//				todo fire event
//				jTextField.setText("VICTORY");
//				for (int i = 0; i < game.getYAxisLength(); i++) {
//					for (int j = 0; j < game.getXAxisLength(); j++) {
//						buttons[i][j].setEnabled(false);
//						buttons[i][j].setIcon(null);
//					}
//				}
			}

			game.printBoard(0);
		}
	}

}

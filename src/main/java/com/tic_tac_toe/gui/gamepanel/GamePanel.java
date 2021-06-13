package com.tic_tac_toe.gui.gamepanel;

import com.tic_tac_toe.Utils;
import com.tic_tac_toe.gamedrivers.board.Game;
import com.tic_tac_toe.gamedrivers.point.Point;
import com.tic_tac_toe.gamedrivers.tile.TileOwner;
import com.tic_tac_toe.gui.Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GamePanel extends JPanel implements PropertyChangeListener, Listener {
// todo buttons to factory
// every design pattern likes solid principles

	private final PropertyChangeSupport support;
    private final TileButton[][] buttons;
    ActionListener tileListener;
    private final Game game;

    public GamePanel() {
        support = new PropertyChangeSupport(this);

        game = new Game();
        int yLength = game.getYAxisLength();
        int xLength = game.getXAxisLength();

//		game mode determinated
//		tileListener = new TileListenerUserVsComputer();

        setLayout(new GridLayout(0, game.getXAxisLength()));
//		todo

        buttons = new TileButton[yLength][xLength];

        for (int y = 0; y < yLength; y++) {
            for (int x = 0; x < xLength; x++) {

//				todo check if user vs computer or user vs user
                buttons[y][x] = new TileButton(game, new Point(y, x));

                buttons[y][x].addActionListener(new TileListenerUserVsComputer(game, new Point(x, y), this));

                buttons[y][x].addListener(this);

                this.add(buttons[y][x]);

            }
        }

    }

    public JButton getButton(int x, int y) {
        return buttons[y][x];
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

    @Override
    public PropertyChangeSupport getSupport() {
        return this.support;
    }

//    public void addListener(PropertyChangeListener listener) {
//        support.addPropertyChangeListener(listener);
//    }
//
//    public void removeListener(PropertyChangeListener propertyChangeListener) {
//        support.removePropertyChangeListener(propertyChangeListener);
//    }
//
    enum Command {
        GAME_WON_PLAYER_1,
        GAME_WON_PLAYER_2
    }

    public void disableButtons() {
        for (int y = 0; y < game.getYAxisLength(); y++) {
            for (int x = 0; x < game.getXAxisLength(); x++) {
                buttons[y][x].setEnabled(false);
//                buttons[y][x].setIcon();
//                this.gui.getButton(x, y).setEnabled(false);
//                this.gui.getButton(x, y).setIcon(null);
            }
        }
    }

    class TileListenerUserVsComputer implements ActionListener {
        private final Point point;
        private final Game game;
        private final GamePanel gui;

        TileListenerUserVsComputer(Game game, Point point, GamePanel gamePanel) {
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

}

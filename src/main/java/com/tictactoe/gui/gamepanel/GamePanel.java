package com.tictactoe.gui.gamepanel;

import com.tictactoe.eventhandler.EventListener;
import com.tictactoe.eventhandler.EventManager;
import com.tictactoe.eventhandler.EventType;
import com.tictactoe.gamedrivers.board.Game;
import com.tictactoe.gamedrivers.point.Point;
import com.tictactoe.gui.gamepanel.gamemode.TileListenerUserVsComputer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamePanel extends JPanel implements EventManager, EventListener {
// todo buttons to factory
// every design pattern likes solid principles

    private final Game game;
    private final TileButton[][] buttons;

    ActionListener tileListener;

    public GamePanel() {
        listeners = new HashMap<>();

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
    public void update(String filename) {
        restartButtons();
    }


    void restartButtons() {
        for (int y = 0; y < game.getYAxisLength(); y++) {
            for (int x = 0; x < game.getXAxisLength(); x++) {
                buttons[y][x].setEnabled(true);
                buttons[y][x].setText(x + " " + y);
                buttons[y][x].setIcon(null);

                buttons[y][x].addActionListener(new TileListenerUserVsComputer(game, new Point(x, y), this));

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

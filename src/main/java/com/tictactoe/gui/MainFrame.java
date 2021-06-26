package com.tictactoe.gui;

import com.tictactoe.eventhandler.EventType;
import com.tictactoe.gui.gamepanel.GamePanel;
import com.tictactoe.gui.utilspanel.UtilsPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

//    enum Event {
//
//    }
//
//    interface Mediator {
//        void notify(JComponent sender, Event event);
//    }
//
//    class BasicMediator implements Mediator {
//
//        @Override
//        public void notify(JComponent sender, Event event) {
//
//        }
//    }

    public MainFrame() {
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1));
        setSize(500, 500);

        UtilsPanel utilsPanel = new UtilsPanel();
        GamePanel gamePanel = new GamePanel();

        add(utilsPanel);
        add(gamePanel);

        utilsPanel.getRestartButton().subscribe(EventType.RESTART_BUTTON_PRESSED, gamePanel);

    }

}

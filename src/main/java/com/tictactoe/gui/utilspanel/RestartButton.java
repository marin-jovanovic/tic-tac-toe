package com.tictactoe.gui.utilspanel;

import com.tictactoe.eventhandler.EventListener;
import com.tictactoe.eventhandler.EventManager;
import com.tictactoe.eventhandler.EventType;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestartButton extends JButton implements EventManager {

    public RestartButton() {

        this.setText("restart");

        this.addActionListener(event -> {

            notify(EventType.RESTART_BUTTON_PRESSED, "");

            System.out.println("restart pressed");
        });

    }

    Map<EventType, List<EventListener>> listeners = new HashMap<>();

    @Override
    public Map<EventType, List<EventListener>> getListeners() {
        return listeners;
    }

}

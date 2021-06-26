package com.tictactoe.gui.utilspanel;

import com.tictactoe.eventhandler.EventListener;
import com.tictactoe.eventhandler.EventManager;
import com.tictactoe.eventhandler.EventType;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilsPanel extends JPanel implements EventManager {

	JTextField jTextField;
	RestartButton restartButton;
	//    for event manager
	Map<EventType, java.util.List<EventListener>> listeners;

	public UtilsPanel() {
		listeners = new HashMap<>();

		setLayout(new GridLayout(0, 5));

		jTextField = new JTextField();
		add(jTextField);

		restartButton = new RestartButton();
		add(restartButton);

	}

	public JTextField getjTextField() {
		return jTextField;
	}

	public RestartButton getRestartButton() {
		return restartButton;
	}

	@Override
	public Map<EventType, List<EventListener>> getListeners() {
		return listeners;
	}

}

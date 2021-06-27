package com.tictactoe.gui.utilspanel;

import com.tictactoe.eventhandler.EventListener;
import com.tictactoe.eventhandler.EventManager;
import com.tictactoe.eventhandler.EventType;
import com.tictactoe.eventhandler.example.EventSubtype;
import org.w3c.dom.events.EventException;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilsPanel extends JPanel implements EventListener {

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

	@Override
	public void update(EventSubtype eventSubtype) {
		System.out.println("UtilsPanel; update; action=" + eventSubtype);

		if (eventSubtype == EventSubtype.USER_1) {
			jTextField.setText("1 won");
		} else if (eventSubtype == EventSubtype.USER_2) {
			jTextField.setText("2 won");
		} else if (eventSubtype == EventSubtype.NONE) {
			jTextField.setText(null);
		} else if (eventSubtype == EventSubtype.TIE) {
			jTextField.setText("tie");
		} else {
			System.out.println("unknown command");
		}

	}

}

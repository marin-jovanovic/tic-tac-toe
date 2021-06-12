package com.tic_tac_toe.gui;

import javax.swing.*;

public class RestartButton extends JButton {

	public RestartButton() {
		this.setText("restart");

		this.addActionListener(event -> {
			System.out.println("restart pressed");
		});
	}



}

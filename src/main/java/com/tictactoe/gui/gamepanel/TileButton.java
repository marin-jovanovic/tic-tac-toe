package com.tictactoe.gui.gamepanel;

import com.tictactoe.gamedrivers.point.Point;

import javax.swing.*;

public class TileButton extends JButton {

	private final Point point;

	public TileButton(Point point) {
		this.point = point;

		this.setText(point.getX() + " " + point.getY());

	}


	public Point getPoint() {
		return point;
	}

	public void restart() {
		this.setEnabled(true);
		this.setIcon(null);
	}

}

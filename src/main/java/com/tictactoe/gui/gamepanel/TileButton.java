package com.tictactoe.gui.gamepanel;

import com.tictactoe.gamedrivers.point.Point;

import javax.swing.*;

public class TileButton extends JButton {
//    private final PropertyChangeSupport support;
//    private final Game game;

	//todo remove this
	private final Point point;

	public TileButton(Point point) {
//        support = new PropertyChangeSupport(this);

//        this.game = game;
		this.point = point;

		this.setText(point.getX() + " " + point.getY());

	}

	//    @Override
//    public int getX() {
//        return point.getX();
//    }
//
//    @Override
//    public int getY() {
//        return point.getY();
//    }

	public Point getPoint() {
		return point;
	}

	public void restart() {
		this.setEnabled(true);
		this.setIcon(null);
	}

}

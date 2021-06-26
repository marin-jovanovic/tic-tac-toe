package com.tictactoe.gui.gamepanel;

import com.tictactoe.gamedrivers.board.Game;
import com.tictactoe.gamedrivers.point.Point;

import javax.swing.*;
import java.beans.PropertyChangeSupport;

public class TileButton extends JButton  {
//    private final PropertyChangeSupport support;
//    private final Game game;

//todo remove this
    private final Point point;

    public Point getPoint() {
        return point;
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

    public TileButton(Point point) {
//        support = new PropertyChangeSupport(this);

//        this.game = game;
        this.point = point;

        this.setText(point.getX() + " " + point.getY());

    }

    public void restart() {
        this.setEnabled(true);
        this.setIcon(null);
    }

}

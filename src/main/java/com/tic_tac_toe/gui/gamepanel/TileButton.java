package com.tic_tac_toe.gui.gamepanel;

import com.tic_tac_toe.gamedrivers.board.Game;
import com.tic_tac_toe.gamedrivers.point.Point;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TileButton extends JButton implements PropertyChangeListener {
    private final PropertyChangeSupport support;
    private final Game game;
    private final Point point;

    public TileButton(Game game, Point point) {
        support = new PropertyChangeSupport(this);

        this.game = game;
        this.point = point;

        this.setText(point.getX() + " " + point.getY());

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("catched property change in tile button");

        System.out.println(evt.getNewValue());

//		this.setEnabled(false);
//		this.setIcon(null);
    }

    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removeListener(PropertyChangeListener p) {
        support.removePropertyChangeListener(p);
    }

    public void restart() {
        this.setEnabled(true);
        this.setIcon(null);
    }

    //	todo
    class playerVsComputerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }


}

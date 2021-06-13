package com.tic_tac_toe.gui.utilspanel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RestartButton extends JButton implements PropertyChangeListener {

    private final PropertyChangeSupport support;

    public RestartButton() {
        support = new PropertyChangeSupport(this);

        this.setText("restart");

        this.addActionListener(event -> {
            support.firePropertyChange("", null, "restart button pressed");

            System.out.println("restart pressed");
        });
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

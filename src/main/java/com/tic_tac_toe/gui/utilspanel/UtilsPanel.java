package com.tic_tac_toe.gui.utilspanel;

import com.tic_tac_toe.gui.utilspanel.RestartButton;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UtilsPanel extends JPanel implements PropertyChangeListener {

    JTextField jTextField;
    JButton restartButton = new RestartButton();
    private final PropertyChangeSupport support;

    public UtilsPanel() {
        support = new PropertyChangeSupport(this);
        setLayout(new GridLayout(0, 5));

        jTextField = new JTextField();
        add(jTextField);

        restartButton = new RestartButton();
        add(restartButton);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("utils listener activated" + evt.getNewValue());
    }
}

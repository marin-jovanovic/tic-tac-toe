package com.tictactoe.gui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public interface Listener {

//    PropertyChangeSupport support = new PropertyChangeSupport();

    PropertyChangeSupport getSupport();

    default void addListener(PropertyChangeListener listener) {
        getSupport().addPropertyChangeListener(listener);
    }

    default void removeListener(PropertyChangeListener propertyChangeListener) {
        getSupport().removePropertyChangeListener(propertyChangeListener);
    }

}

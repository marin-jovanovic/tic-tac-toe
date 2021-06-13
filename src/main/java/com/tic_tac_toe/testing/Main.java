package com.tic_tac_toe.testing;

import javax.swing.event.EventListenerList;
import java.awt.*;
import java.util.EventListener;
import java.util.EventObject;

public class Main {

    public static void main(String[] args) {

    }

    class ClassOne  {
        private EventListenerList listenerList = new EventListenerList();

        public void fireEvent(Event event) {
            Object[] listeners = listenerList.getListenerList();

            for (int i = 0; i < listeners.length; i += 2) {
                if(listeners[i] == Listener.class) {
                    ((Listener)listeners[i+1]).EventOccured(event);
                }
            }
        }

        public void addListener(Listener listener) {
            listenerList.add(Listener.class, listener);
        }

        public void removeListener(Listener listener) {
            listenerList.remove(Listener.class, listener);
        }
    }

    class Event extends EventObject {

        private String command;

        public Event(Object source, String command) {
            super(source);

            this.command = command;

        }

        public String getCommand() {
            return command;
        }

    }

    interface Listener extends EventListener {

        public void EventOccured(Event event);

    }
//    public void EventOccured(Event event);

}
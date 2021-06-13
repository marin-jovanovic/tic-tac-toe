package com.tic_tac_toe.testing;

import javax.swing.event.EventListenerList;
import java.util.EventListener;
import java.util.EventObject;

public class OldListenerImpl {

    public static void main(String[] args) {
        OldListenerImpl oldListenerImpl = new OldListenerImpl();
        oldListenerImpl.launcher();
    }

    public void launcher() {
        ClassOne classOne = new ClassOne();
        ClassTwo classTwo = new ClassTwo();

        CustomListenerOne customListenerOne = new CustomListenerOne();

        classOne.addListener(customListenerOne);
        classOne.fireEvent(new Event(this, "gameOver"));

        classOne.removeListener(customListenerOne);
        classOne.fireEvent(new Event(this, "gameOver"));

    }

    class CustomListenerOne implements Listener {

        @Override
        public void eventOccured(Event event) {
            System.out.println("listener 1");
            System.out.println(event);
            System.out.println(event.getSource());
            System.out.println(event.getCommand());
        }
    }

    class ClassOne  {

        private EventListenerList listenerList = new EventListenerList();

        public void fireEvent(Event event) {
            Object[] listeners = listenerList.getListenerList();

            for (Object listener : listeners) {
                if (listener instanceof Listener) {
                    ((Listener) listener).eventOccured(event);
                    return;
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


    class ClassTwo {
        private EventListenerList listenerList = new EventListenerList();

        public void fireEvent(Event event) {
            Object[] listeners = listenerList.getListenerList();

            for (Object listener : listeners) {
                if (listener instanceof Listener) {
                    ((Listener) listener).eventOccured(event);
                    return;
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

        void eventOccured(Event event);

    }

}
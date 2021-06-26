package com.tictactoe.eventhandler.example;

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

	interface Listener extends EventListener {

		void eventOccured(Event event);

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

	class ClassOne {

		private final EventListenerList listenerList = new EventListenerList();

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
		private final EventListenerList listenerList = new EventListenerList();

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

		private final String command;

		public Event(Object source, String command) {
			super(source);
			this.command = command;
		}

		public String getCommand() {
			return command;
		}

	}

}
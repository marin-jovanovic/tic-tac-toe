package com.tictactoe.eventhandler.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * code design from https://refactoring.guru/design-patterns/observer
 */

public class NewListenerImpl {
	public static void main(String[] args) {
		NewListenerImpl newListener = new NewListenerImpl();
		newListener.launcher();
	}

	void launcher() {
		Application application = new Application();
		application.config();
	}

	enum EventType {
		OPEN,
		SAVE
	}

	interface EventListener {
		void update(String filename);
	}

	class EventManager {
		Map<EventType, List<EventListener>> listeners;

		EventManager() {
			listeners = new HashMap<>();
		}

		void subscribe(EventType eventType, EventListener listener) {

			if (listeners.containsKey(eventType)) {
				listeners.get(eventType).add(listener);
			} else {
				listeners.put(eventType, new ArrayList<EventListener>() {{
					add(listener);
				}});
			}
		}

		boolean unsubscribe(EventType eventType, EventListener listener) {
			if (!listeners.get(eventType).contains(listener)) {
				System.out.println("listener unsubscribe error");

				return false;
			}

			listeners.get(eventType).remove(listener);
			return true;

		}

		void notify(EventType eventType, String data) {

			for (EventListener eventListener : listeners.get(eventType)) {
				eventListener.update(data);
			}

		}
	}

	class EditorOne {
		EventManager events;

		EditorOne() {
			events = new EventManager();
		}

		void openFile() {
			System.out.println("file opened");

			events.notify(EventType.OPEN, "file 1");
		}

		void saveFile() {
			System.out.println("file saved");

			events.notify(EventType.SAVE, "save 1");
		}
	}

	class EditorTwo {
		EventManager events;

		EditorTwo() {
			events = new EventManager();
		}

		void openFile() {
			System.out.println("file opened");

			events.notify(EventType.OPEN, "file 2");
		}

		void saveFile() {
			System.out.println("file saved");

			events.notify(EventType.SAVE, "save 2");
		}
	}

	class LoggingListener implements EventListener {


		@Override
		public void update(String filename) {
			System.out.println("LoggingListener update; " + filename);
		}
	}

	class EmailAlertListener implements EventListener {

		@Override
		public void update(String filename) {
			System.out.println("EmailAlertListener update; " + filename);
		}

	}

	class Application {
		void config() {
			EditorOne editorOne = new EditorOne();
			EditorTwo editorTwo = new EditorTwo();

			LoggingListener logger = new LoggingListener();
			editorOne.events.subscribe(EventType.OPEN, logger);
			editorTwo.events.subscribe(EventType.OPEN, logger);

			EmailAlertListener emailAlert = new EmailAlertListener();
			editorOne.events.subscribe(EventType.SAVE, emailAlert);

			editorOne.openFile();
			System.out.println("-------");

			editorTwo.openFile();
			System.out.println("-------");

			editorOne.events.unsubscribe(EventType.OPEN, logger);

			editorOne.openFile();
			System.out.println("-------");

			editorTwo.openFile();
			System.out.println("-------");

			editorOne.saveFile();
			System.out.println("-------");


		}

	}

}

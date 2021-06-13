package com.tic_tac_toe.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	class EventManager {
		Map<EventType, List<EventListener>> listeners;

		EventManager() {
			listeners = new HashMap<>();
		}

		void subscribe(EventType eventType, EventListener listener) {

			if (listeners.containsKey(eventType)) {
				listeners.get(eventType).add(listener);
 			} else {
				listeners.put(eventType, new ArrayList<EventListener>() {{add(listener);}});
			}
		}

		boolean unsubscribe(EventType eventType, EventListener listener) {
			if (! listeners.get(eventType).contains(listener)) {
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

	class Editor {
		EventManager events;
		int fileCounter;

		Editor() {
			events = new EventManager();
			fileCounter = 0;
		}

		void openFile() {
			System.out.println("file opened");

			events.notify(EventType.OPEN, "file " + fileCounter++);
		}

		void saveFile() {
			System.out.println("file saved");

			events.notify(EventType.SAVE, "save 1" + (fileCounter-1));
		}
	}

	interface EventListener {
		void update(String filename);
	}

	class LoggingListener implements EventListener {
		String log;
		String message;

		LoggingListener(String logFilename, String message) {
			this.log = logFilename;
			this.message = message;
		}

		@Override
		public void update(String filename) {
			System.out.println("LoggingListener update; " + log + " " + message);
		}
	}

	class EmailAlertListener implements EventListener {
		String email;

		EmailAlertListener(String email) {
			this.email = email;
		}

		@Override
		public void update(String filename) {
			System.out.println("EmailAlertListener update; " + email);
		}

	}

	class Application {
		void config() {
			Editor editor = new Editor();

			LoggingListener logger = new LoggingListener("filename temp", "msg 1");
			editor.events.subscribe(EventType.OPEN, logger);

			EmailAlertListener emailAlert = new EmailAlertListener("email t");
			editor.events.subscribe(EventType.SAVE, emailAlert);

			editor.openFile();
			editor.events.unsubscribe(EventType.OPEN, logger);

			editor.openFile();

			editor.saveFile();

		}

	}

}

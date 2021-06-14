package com.tictactoe.designpatterns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// code based on pseudo-code from https://refactoring.guru/design-patterns/observer

public class Observer {



	enum EventType {
		OPEN,
		SAVE
	}

	interface EventManager {

		Map<EventType, List<EventListener>> getListeners();

		default void subscribe(EventType eventType, EventListener listener) {
			Map<EventType, List<EventListener>> listeners = getListeners();


			if (listeners.containsKey(eventType)) {
				listeners.get(eventType).add(listener);
			} else {
				listeners.put(eventType, new ArrayList<EventListener>() {{add(listener);}});
			}
		}

		default boolean unsubscribe(EventType eventType, EventListener listener) {
			Map<EventType, List<EventListener>> listeners = getListeners();

			if (! listeners.get(eventType).contains(listener)) {
				System.out.println("listener unsubscribe error");

				return false;
			}

			listeners.get(eventType).remove(listener);
			return true;

		}

		default void notify(EventType eventType, String data) {

			for (EventListener eventListener : getListeners().get(eventType)) {
				eventListener.update(data);
			}

		}
	}


	class EditorOne implements EventManager{

		void openFile() {
			System.out.println("file opened 1");

			notify(EventType.OPEN, "file 1");
		}

		void saveFile() {
			System.out.println("file saved 1");

			notify(EventType.SAVE, "save 1");
		}

		Map<EventType, List<EventListener>> listeners = new HashMap<>();

		@Override
		public Map<EventType, List<EventListener>> getListeners() {
			return listeners;
		}
	}

	class EditorTwo implements EventManager{

		void openFile() {
			System.out.println("file opened 2");

			notify(EventType.OPEN, "file 2");
		}

		void saveFile() {
			System.out.println("file saved 2");

			notify(EventType.SAVE, "save 2");
		}

		Map<EventType, List<EventListener>> listeners = new HashMap<>();

		@Override
		public Map<EventType, List<EventListener>> getListeners() {
			return listeners;
		}
	}

	interface EventListener {
		void update(String filename);
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

	public static void main(String[] args) {
		Observer observer = new Observer();
		observer.launcher();
	}

	void launcher() {

		EditorOne editorOne = new EditorOne();
		EditorTwo editorTwo = new EditorTwo();

		LoggingListener logger = new LoggingListener();
		editorOne.subscribe(EventType.OPEN, logger);
		editorTwo.subscribe(EventType.OPEN, logger);

		EmailAlertListener emailAlert = new EmailAlertListener();
		editorOne.subscribe(EventType.SAVE, emailAlert);

		editorOne.openFile();
		System.out.println("-------");

		editorTwo.openFile();
		System.out.println("-------");

		editorOne.unsubscribe(EventType.OPEN, logger);

		editorOne.openFile();
		System.out.println("-------");

		editorTwo.openFile();
		System.out.println("-------");

		editorOne.saveFile();
		System.out.println("-------");

	}


}

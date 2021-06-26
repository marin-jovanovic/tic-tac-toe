package com.tictactoe.eventhandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface EventManager {
//			Map<EventType, List<EventListener>> listeners = new HashMap<>();
//
//		@Override
//		public Map<EventType, List<EventListener>> getListeners() {
//			return listeners;
//		}

	Map<EventType, List<EventListener>> getListeners();

	default void subscribe(EventType eventType, EventListener listener) {
		Map<EventType, List<EventListener>> listeners = getListeners();

		if (listeners.containsKey(eventType)) {
			listeners.get(eventType).add(listener);
		} else {
			listeners.put(eventType, new ArrayList<EventListener>() {{
				add(listener);
			}});
		}

	}

	default boolean unsubscribe(EventType eventType, EventListener listener) {
		Map<EventType, List<EventListener>> listeners = getListeners();

		if (!listeners.get(eventType).contains(listener)) {
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

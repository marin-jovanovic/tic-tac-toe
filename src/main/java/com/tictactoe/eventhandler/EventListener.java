package com.tictactoe.eventhandler;

import com.tictactoe.eventhandler.example.EventSubtype;

public interface EventListener extends EventManager {
	void update(EventSubtype eventSubtype);
}

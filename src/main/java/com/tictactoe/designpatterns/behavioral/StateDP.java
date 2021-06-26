package com.tictactoe.designpatterns.behavioral;

import javax.swing.*;

public class StateDP {

	class UserInterface {

		public Button lockButton;
		public Button playButton;
		public Button nextButton;
		public Button prevButton;

		class Button {
			void onClick() {

			}
		}
	}

	class AudioPlayer {
		State state;
		UserInterface UI;

		int volume;
		int playlist;
		int currentSong;

		AudioPlayer() {
			this.state = new ReadyState(this);

			UI = new UserInterface();
//			UI.lockButton.onClick(this.clickLock);
//			UI.playButton.onClick(this.clickPlay);
//			UI.nextButton.onClick(this.clickNext);
//			UI.prevButton.onClick(this.clickPrevious);
		}

		void changeState(State state) {
			this.state = state;
		}

		void clickLock() {
			state.clickLock();
		}

		void clickPlay() {
			state.clickPlay();
		}

		void clickNext() {
			state.clickNext();
		}

		void clickPrevious() {
			state.clickPrevious();
		}

		void startPlayback() { }
		void stopPlayback() {}
		void nextSong() {}
		void previousSong() {}
		void fastForward(int time) {}
		void rewind(int time) {}
	}

	abstract class State {
		AudioPlayer player;

		State(AudioPlayer player) {
			this.player = player;
		}

		abstract void clickLock();
		abstract void clickPlay();
		abstract void clickNext();
		abstract void clickPrevious();
	}

	class LockedState extends State {

		LockedState(AudioPlayer player) {
			super(player);
		}

		@Override
		void clickLock() {
//			if (player.playing)
//				player.changeState(new PlayingState(player));
//			else
//				player.changeState(new ReadyState(player));

		}

		@Override
		void clickPlay() {

		}

		@Override
		void clickNext() {

		}

		@Override
		void clickPrevious() {

		}
	}

	class ReadyState extends State {
		ReadyState(AudioPlayer player) {
			super(player);
		}

		@Override
		void clickLock() {
			player.changeState(new LockedState(player));
		}

		@Override
		void clickPlay() {
			player.startPlayback();
			player.changeState(new PlayingState(player));
		}

		@Override
		void clickNext() {
			player.nextSong();
		}

		@Override
		void clickPrevious() {
			player.previousSong();
		}

	}

	class PlayingState extends State {

		PlayingState(AudioPlayer player) {
			super(player);
		}

		@Override
		void clickLock() {
			player.changeState(new LockedState(player));
		}

		@Override
		void clickPlay() {
			player.stopPlayback();
			player.changeState(new ReadyState(player));
		}

		@Override
		void clickNext() {
//			if (event.doubleclick)
			if (true)
				player.nextSong();
			else
				player.fastForward(5);
		}

		@Override
		void clickPrevious() {
//			if (event.doubleclick)
//			if (true)
//				player.previous();
//			else
//				player.rewind(5);
		}
	}
}

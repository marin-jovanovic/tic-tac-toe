package com.tictactoe.designpatterns.behavioral;

public class MementoDP {

	class Editor {
		String text;
		int curX;
		int curY;
		int selectionWidth;

		void setText(String text) {
			this.text = text;
		}

		void setCursor(int x, int y) {
			this.curX = x;
			this.curY = y;
		}

		void setSelectionWidth(int width) {
			this.selectionWidth = width;
		}

		Snapshot createSnapshot() {
			return new Snapshot(this, text, curX, curY, selectionWidth);
		}
	}

	class Snapshot {
		Editor editor;
		String text;
		int curX;
		int curY;
		int selectionWidth;

		Snapshot(Editor editor, String text, int curX, int curY, int selectionWidth) {
			this.editor = editor;
			this.text = text;
			this.curX = curX;
			this.curY = curY;
			this.selectionWidth = selectionWidth;
		}

		void restore() {
			editor.setText(text);
			editor.setCursor(curX, curY);
			editor.setSelectionWidth(selectionWidth);
		}
	}

	class Command {
		Snapshot backup;

		Editor editor;

		void makeBackup() {
			backup = editor.createSnapshot();
		}

		void undo() {
			if (backup != null) {
				backup.restore();
			}
		}
	}

}

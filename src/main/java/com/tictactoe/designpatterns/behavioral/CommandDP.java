package com.tictactoe.designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

public class CommandDP {

	abstract class Command {
		Application app;
		Editor editor;
		String backup;

		Command(Application app, Editor editor){
			this.app = app;
			this.editor = editor;
		}

		void saveBackup(){
			this.backup = editor.text;
		}

		void undo() {
			editor.text = this.backup;
		}

		abstract boolean execute();
	}

	class CopyCommand extends Command {

		CopyCommand(Application app, Editor editor) {
			super(app, editor);
		}

		@Override
		boolean execute() {
			app.clipboard = editor.getSelection();
			return false;
		}
	}

	class CutCommand extends Command {
		CutCommand(Application app, Editor editor) {
			super(app, editor);
		}

		@Override
		boolean execute() {
			saveBackup();

			app.clipboard = editor.getSelection();

			editor.deleteSelection();
			return false;
		}
	}


	class PasteCommand extends Command {
		PasteCommand(Application app, Editor editor) {
			super(app, editor);
		}

		@Override
		boolean execute() {
			saveBackup();

			editor.replaceSelection(app.clipboard);
			return false;
		}
	}

	class UndoCommand extends Command {
		UndoCommand(Application app, Editor editor) {
			super(app, editor);
		}

		@Override
		boolean execute() {
			app.undo();
			return false;
		}
	}

	class CommandHistory {
		List<Command> history = new ArrayList<>();
		Command latest = null;

		void push(Command c) {
			history.add(c);
			latest = c;
		}

		Command pop() {
			history.add(latest);
			return latest;
		}
	}

	class Editor {
		String text;

		String getSelection() {
			return "selected text";
		}

		void deleteSelection() {

		}

		void replaceSelection(String text) {

		}

	}


	class Application {
		String clipboard;
		List<Editor> editors;
		Editor activeEditor;
		CommandHistory history;

		void createUI() {
//			 copy = function() { executeCommand(
//            new CopyCommand(this, activeEditor)) }
//        copyButton.setCommand(copy)
//        shortcuts.onKeyPress("Ctrl+C", copy)
//
//        cut = function() { executeCommand(
//            new CutCommand(this, activeEditor)) }
//        cutButton.setCommand(cut)
//        shortcuts.onKeyPress("Ctrl+X", cut)
//
//        paste = function() { executeCommand(
//            new PasteCommand(this, activeEditor)) }
//        pasteButton.setCommand(paste)
//        shortcuts.onKeyPress("Ctrl+V", paste)
//
//        undo = function() { executeCommand(
//            new UndoCommand(this, activeEditor)) }
//        undoButton.setCommand(undo)
//        shortcuts.onKeyPress("Ctrl+Z", undo)


		}

		void executeCommand(Command command) {
			if (command.execute()) {
				history.push(command);
			}
		}

		void undo() {
			Command command = history.pop();

			if (command != null) {
				command.undo();
			}
		}

	}
}

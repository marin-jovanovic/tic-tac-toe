package com.tictactoe.designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

public class ChainOfResponsibilityDP {

	interface ComponentWithContextualHelp {
		void showHelp();
	}

	static abstract class Component implements ComponentWithContextualHelp {
		String tooltipText;

		Container container;

		@Override
		public void showHelp() {
			if (tooltipText != null) {
				System.out.println("Component; showHelp;");
			} else {
				container.showHelp();
			}
		}
	}

	static abstract class Container extends Component {
		List<Component> children = new ArrayList<>();

		void add(Component child) {
			children.add(child);
			child.container = this;
		}
	}

	static class Button extends Component {

	}

	static class Panel extends Container {
		String modalHelpText;

		public void showHelp() {
			if (modalHelpText != null) {
				System.out.println("Panel; showHelp;");
			} else {
				super.showHelp();
			}
		}
	}

	static class Dialog extends Container {
		String wikiPageURL;

		public void showHelp() {
			if (wikiPageURL != null) {
				System.out.println("Dialog; showHelp;");
			} else {
				super.showHelp();
			}
		}
	}

	static class Application {

		Button ok = new Button();

		public static void main(String[] args) {
			Application application = new Application();
			application.createUI();
			application.onF1KeyPress(application.ok);
		}

		void createUI() {
			Dialog dialog = new Dialog();
			dialog.wikiPageURL = "https://";

			Panel panel = new Panel();
			panel.modalHelpText = "this panel does...";

			ok.tooltipText = "This is an OK button that...";
			Button cancel = new Button();
			// ...
			panel.add(ok);
			panel.add(cancel);
			dialog.add(panel);

		}

		void onF1KeyPress(Component component) {
			component.showHelp();
		}
	}

}

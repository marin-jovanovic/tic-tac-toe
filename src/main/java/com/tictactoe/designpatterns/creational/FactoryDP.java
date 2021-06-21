package com.tictactoe.designpatterns.creational;

public class FactoryDP {

	static abstract class Dialog {
		abstract Button createButton();

		void render(int a, int b) {
			Button okButton = createButton();
			okButton.onClick(closeDialog());
			okButton.render(a, b);
		}

		private int closeDialog() {
			return -1;
		}

	}

	static class WindowsDialog extends Dialog {
		WindowsButton createButton() {
			return new WindowsButton();
		}
	}

	static class WebDialog extends Dialog {
		HTMLButton createButton() {
			return new HTMLButton();
		}
	}

	interface Button {
		void render(int a, int b);
		void onClick(int f);

	}

	static class WindowsButton implements Button {

		@Override
		public void render(int a, int b) {

		}

		@Override
		public void onClick(int f) {

		}
	}

	static class HTMLButton implements Button {

		@Override
		public void render(int a, int b) {

		}

		@Override
		public void onClick(int f) {

		}
	}

	static class Application {
		Dialog dialog;

		void initialize() {
			String configOS = "Windows";


			if(configOS.equals("Windows")) {
				dialog = new WindowsDialog();

			} else if(configOS.equals("Web")) {
				dialog = new WebDialog();
			} else{
				System.out.println("Error! Unknown operating system.");
			}
		}

		void driver() {
			int a = 1;
			int b = 1;

			this.initialize();
			this.dialog.render(a, b);

		}

		public static void main(String[] args) {
			Application application = new Application();
			application.driver();
		}

	}
}

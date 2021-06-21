package com.tictactoe.designpatterns.creational;

public class AbstractFactoryDP {

	interface GUIFactory {
		Button createButton();

		CheckBox createCheckbox();

	}

	static class WinFactory implements GUIFactory {


		@Override
		public Button createButton() {
			return new WinButton();
		}

		@Override
		public CheckBox createCheckbox() {
			return new WinCheckbox();
		}
	}

	static class MacFactory implements GUIFactory {

		@Override
		public Button createButton() {
			return new MacButton();
		}

		@Override
		public CheckBox createCheckbox() {
			return new MacCheckbox();
		}
	}

	interface Button {
		void paint();
	}

	static class WinButton implements Button {

		@Override
		public void paint() {

		}
	}

	static class MacButton implements Button {

		@Override
		public void paint() {

		}
	}

	interface CheckBox {
		void paint();
	}

	static class WinCheckbox implements CheckBox {
		@Override
		public void paint() {

		}
	}

	static class MacCheckbox implements CheckBox {

		@Override
		public void paint() {

		}
	}

	static class Application {
		GUIFactory factory;
		Button button;

		Application(GUIFactory factory) {
			this.factory = factory;
		}

		void createUI() {
			this.button = factory.createButton();
		}

		void paint() {
			this.button.paint();
		}
	}

	static class ApplicationConfigurator {
		public static void main(String[] args) {
			String configOS = "Windows";

			GUIFactory factory = null;

			if (configOS.equals("Windows")) {
				factory = new WinFactory();
			} else if (configOS.equals("Mac")) {
				factory = new MacFactory();
			} else {
				System.out.println("error in configOS");
			}

			Application app = new Application(factory);
		}
	}

}

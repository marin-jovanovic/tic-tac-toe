package com.tictactoe.designpatterns.behavioral;

import java.awt.*;

public class MediatorDP {

	interface Mediator {
		void notify(Component sender, String event);
	}

	class AuthenticationDialog implements Mediator {
		private String title;
		private Checkbox loginOrRegisterChkBx;
		private Textbox loginUsername, loginPassword;
		private Textbox registrationUsername, registrationPassword,
				registrationEmail;
		private Button okBtn, cancelBtn;

		AuthenticationDialog() {
			// Create all component objects and pass the current
			// mediator into their constructors to establish links.
		}

		// When something happens with a component, it notifies the
		// mediator. Upon receiving a notification, the mediator may
		// do something on its own or pass the request to another
		// component.
		public void notify(Component sender, String event) {

			boolean isChecked = false;

			if (sender == loginOrRegisterChkBx && event == "check") {

				if (isChecked) {
					title = "Log in";
					// 1. Show login form components.
					// 2. Hide registration form components.
				} else {
					title = "Register";
					// 1. Show registration form components.
					// 2. Hide login form components
				}
			} else if (sender == okBtn && event == "click") {
				if (isChecked) {
					// Try to find a user using login credentials.

					boolean isFound = false;
					if (!isFound) {
						// Show an error message above the login
						// field.
					}
				} else {
					// 1. Create a user account using data from the
					// registration fields.
					// 2. Log that user in.
					// ...
				}
			}

		}
	}

	// Components communicate with a mediator using the mediator
	// interface. Thanks to that, you can use the same components in
	// other contexts by linking them with different mediator
	// objects.
	class Component {
		Mediator dialog;

		Component(Mediator dialog) {
			this.dialog = dialog;
		}

		void click() {
			dialog.notify(this, "click");
		}

		void keypress() {
			dialog.notify(this, "keypress");
		}
	}

	// Concrete components don't talk to each other. They have only
	// one communication channel, which is sending notifications to
	// the mediator.
	class Button extends Component {
		Button(Mediator dialog) {
			super(dialog);
		}
		// ...
	}

	class Textbox extends Component {
		Textbox(Mediator dialog) {
			super(dialog);
		}
		// ...
	}

	class Checkbox extends Component {

		Checkbox(Mediator dialog) {
			super(dialog);
		}

		void check() {
			dialog.notify(this, "check");
			// ...
		}
	}
}

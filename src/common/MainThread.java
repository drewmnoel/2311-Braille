package common;

import simulator.SimulatorCore;

/**
 * This class acts as the client thread, which performs various actions on the
 * UI. Required since JavaFX eats the normal Main method.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-02-02
 */
public class MainThread implements Runnable {

	@Override
	public void run() {
		API api = new API();

		// Wait for the core information to be prepopulated to avoid null
		// pointers
		while (!SimulatorCore.ready()) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		}

		// Core is ready, demo setting the display to several strings
		try {
			api.setText("test");
			Thread.sleep(1500);
			api.setText("hello");
			api.setButtons(4);
			Thread.sleep(1500);
			api.setText("bye");
		} catch (InterruptedException e) {
		}
	}

}

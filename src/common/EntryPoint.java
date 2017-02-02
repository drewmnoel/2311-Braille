package common;

import javafx.application.Application;
import simulator.CreateCells;

/**
 * Entry point of the program. Launches the GUI and spins off threads as needed.
 */
public class EntryPoint {
	public static void main(String[] args) throws Exception {
		// Create a new thread to do things after the GUI starts
		Thread t = new Thread(new MainThread());
		t.start();

		// Start the GUI. Main thread is now locked here until program terminates
		Application.launch(CreateCells.class);
	}
}

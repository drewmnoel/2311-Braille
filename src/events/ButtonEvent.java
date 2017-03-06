package events;

import common.JumpButtonListener;
import common.SharedObject;
import simulator.Simulator;

public class ButtonEvent extends Event {

	@Override
	public int execute() {
		// Access the simulator singleton
		Simulator sim = Simulator.getInstance();

		// Determine how many buttons we have to figure out
		int numButtons = getDetails().split(" ").length;

		// Create a shared object to track which button gets clicked
		SharedObject sharedObject = new SharedObject();
		sharedObject.setId(-1);

		// Create the listeners and give them access to the shared object
		for (int i = 0; i < numButtons; i++) {
			sim.getButton(i).addActionListener(new JumpButtonListener(sharedObject, i));
		}

		// Wait for a button to change the shared value.
		while (sharedObject.getId() == -1) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {}
		}

		// Shared Object now contains the number of the button that was pressed.
		return Integer.parseInt(getDetails().split(" ")[sharedObject.getId()]);
	}

}
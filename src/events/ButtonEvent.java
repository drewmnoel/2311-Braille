package events;

import common.JumpButtonListener;
import common.SharedObject;
import player.PlayerException;
import simulator.Simulator;

/**
 * Representation of a Button Event in the scenario. Events are created by
 * FileParser parsing an input file. Execution of this event will wait the
 * thread until a button is clicked.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-03-05
 *
 */
public class ButtonEvent extends Event {

	@Override
	public int execute() throws PlayerException {
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
			} catch (InterruptedException e) {
				// The thread was interrupted, we should probably exit
				throw new PlayerException("The program was interrupted", e);
			}
		}

		// Shared Object now contains the number of the button that was pressed.
		return Integer.parseInt(getDetails().split(" ")[sharedObject.getId()]);
	}

}
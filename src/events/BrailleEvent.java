package events;

import simulator.Simulator;

/**
 * Representation of a Braille Event in the scenario. Events are created by
 * FileParser parsing an input file. Execution of this event will show the
 * appropriate string on the braille cells of the simulator
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-03-05
 *
 */
public class BrailleEvent extends Event {

	@Override
	public int execute() {
		Simulator sim = Simulator.getInstance();
		sim.displayString(this.getDetails());

		return 1;
	}

}
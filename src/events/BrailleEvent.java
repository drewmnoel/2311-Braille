package events;

import simulator.Simulator;

public class BrailleEvent extends Event {

	@Override
	public int execute() {
		Simulator sim = Simulator.getInstance();
		sim.displayString(this.getDetails());

		return 1;
	}

}
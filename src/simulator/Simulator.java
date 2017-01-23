package simulator;

import common.IBrailleSystem;

/**
 * Represents a simulation model of the system.
 */
public class Simulator implements IBrailleSystem {

	/**
	 * Private constructor, only allowed to be used by itself.
	 */
	public Simulator() {
		javafx.application.Application.launch(SimulatorDisplay.class);
	}

	@Override
	public void setMessage(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getResponse() {
		// TODO Auto-generated method stub
		return null;
	}
}

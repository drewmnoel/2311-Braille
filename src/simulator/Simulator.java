package simulator;

import common.IBrailleSystem;

/**
 * Simulator singleton. Represents a simulation model of the system.
 */
public final class Simulator implements IBrailleSystem {
	private static final Simulator SIMULATOR = new Simulator();

	/**
	 * Private constructor, only allowed to be used by itself.
	 */
	private Simulator() {
	}

	/**
	 * Get instance of the singleton
	 *
	 * @return An instance of the Simulator singleton
	 */
	public static Simulator getInstance() {
		return SIMULATOR;
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

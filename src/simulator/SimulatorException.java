package simulator;

/**
 * Custom exception class for clarity of exception sources
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-01-30
 *
 */
public class SimulatorException extends Exception {
	/**
	 * Exception with a brief explanation message
	 *
	 * @param message Message to show as the cause of the exception
	 */
	public SimulatorException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}

package common;

/**
 * Interface for Braille Systems to obey
 */
public interface IBrailleSystem {
	/**
	 * Set the current message of the system
	 *
	 * @param msg
	 *            Message to be displayed
	 */
	public void setMessage(String msg);

	/**
	 * Retrieve a response from the system. Waits for a new response before
	 * returning
	 *
	 * @return The latest system response
	 */
	public String getResponse();
}

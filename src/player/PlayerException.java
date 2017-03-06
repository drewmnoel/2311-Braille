package player;

/**
 * Generic exception for any exceptions tripped by Player problems. Chained such
 * that the source of the exception can still be obtained, but with a helpful
 * message about what happened.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-03-06
 */
public class PlayerException extends Exception {
	/**
	 * Create a new chained exception with a short description of the problem
	 * encountered
	 *
	 * @param string
	 *            Short text describing the issue
	 * @param e
	 *            Originating exception
	 */
	public PlayerException(String string, Exception e) {
		super(string, e);
	}

	private static final long serialVersionUID = 7676394629176382695L;

}

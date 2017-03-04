package player;

/**
 * Exception wrapper for all TTS related exceptions
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-02-12
 */
public class TTSException extends Exception {
	private static final long serialVersionUID = -4667012759573083189L;

	/**
	 * Create a chained exception with a description
	 *
	 * @param string Short description of the exception
	 * @param e Exception which triggered this exception
	 */
	public TTSException(String string, Exception e) {
		super(string, e);
	}

	/**
	 * Create an exception with a description
	 *
	 * @param string Short description of the exception
	 */
	public TTSException(String string) {
		super(string);
	}

}

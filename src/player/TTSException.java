package player;

public class TTSException extends Exception {
	private static final long serialVersionUID = -4667012759573083189L;

	public TTSException(String string, Exception e) {
		super(string, e);
	}

	public TTSException(String string) {
		super(string);
	}

}

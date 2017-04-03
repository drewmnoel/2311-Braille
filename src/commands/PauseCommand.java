package commands;

/**
 * A class to represent pause commands. Contains a string for the pause duration
 *
 * @author
 *
 */
public class PauseCommand extends PlayerCommand {

	private String waitTime;

	/***
	 * Constructor for PauseCommand.
	 * 
	 * @param waitTime
	 *            The number of seconds for the pause to wait
	 */
	public PauseCommand(String waitTime) {
		this.waitTime = waitTime;
	}

	@Override
	public String serialize() {
		return "/~pause:" + waitTime;
	}

	@Override
	public String toString() {
		return "Pause: " + waitTime;
	}

	@Override
	public String getEditLabel() {
		return "Length of time to wait";
	}

	@Override
	public String getCurrentValue() {
		return waitTime;
	}

	@Override
	public void setCurrentValue(String waitTime) {
		this.waitTime = waitTime;
	}

}

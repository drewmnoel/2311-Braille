package commands;

/**
 * A class to represent button repeating commands.
 *
 * @author
 *
 */
public class RepeatButtonCommand extends PlayerCommand {

	private String buttonNum;

	/***
	 * Constructor for PauseCommand.
	 *
	 * @param waitTime
	 *            The number of seconds for the pause to wait
	 */
	public RepeatButtonCommand(String waitTime) {
		this.buttonNum = waitTime;
	}

	@Override
	public String serialize() {
		return "/~repeat-button:" + buttonNum;
	}

	@Override
	public String toString() {
		return "Repeat Button: " + buttonNum;
	}

	@Override
	public String getEditLabel() {
		return "Button to use for repeating";
	}

	@Override
	public String getCurrentValue() {
		return buttonNum;
	}

	@Override
	public void setCurrentValue(String waitTime) {
		this.buttonNum = waitTime;
	}

}

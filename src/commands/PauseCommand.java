package commands;

/**
 * A class to represent pause commands. Contains a string for the pause duration
 *
 * @author
 *
 */
public class PauseCommand extends PlayerCommand {

	/***
	 * Constructor for PauseCommand.
	 * @param waitTime The number of seconds for the pause to wait
	 */
	public PauseCommand(String waitTime) {
		super();
		description = waitTime;
		commandType = "/~pause:";
	}

	@Override
	public String serialize() {
		// TODO Auto-generated method stub
		return null;
	}

}

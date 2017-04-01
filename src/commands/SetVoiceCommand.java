package commands;

/**
 * A class to represent set voice commands. Contains a string for which numbered text to speech voice to use
 *
 * @author
 *
 */
public class SetVoiceCommand extends PlayerCommand {

	/**
	 * Constructor to create a SetVoiceCommand object
	 *
	 * @param voice String of either 1, 2, 3, 4 representing the text to speech voice to use
	 */
	SetVoiceCommand(String voice) {
		super();
		description = voice;
		commandType = "/~set-voice:";
	}

	@Override
	public String serialize() {
		// TODO Auto-generated method stub
		return null;
	}

}

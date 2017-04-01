package commands;

/**
 * A class to represent set voice commands. Contains a string for which numbered text to speech voice to use
 *
 * @author
 *
 */
public class SetVoiceCommand extends PlayerCommand {

	private String voiceNumber;

	/**
	 * Constructor to create a SetVoiceCommand object
	 *
	 * @param voice String of either 1, 2, 3, 4 representing the text to speech voice to use
	 */
	SetVoiceCommand(String voice) {
		this.voiceNumber = voice;
	}

	@Override
	public String serialize() {
		return "/~set-voice: " + voiceNumber;
	}

	@Override
	public String toString() {
		return "Voice " + voiceNumber;
	}

}

package commands;

/**
 * Command wrapper to represent the TTS command in the player. Values are given
 * as the text that should be read aloud by the TTS.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-04-01
 */
public class TTSCommand extends PlayerCommand {

	private String textToSay = "";

	public TTSCommand(String textToSay) {
		this.textToSay = textToSay;
	}

	@Override
	public String toString() {
		return "Text to speech: " + textToSay;
	}

	@Override
	public String serialize() {
		return textToSay;
	}

	@Override
	public String getEditLabel() {
		return "Text to say";
	}

	@Override
	public String getCurrentValue() {
		return textToSay;
	}

	@Override
	public void setCurrentValue(String textToSay) {
		this.textToSay = textToSay;
	}

}

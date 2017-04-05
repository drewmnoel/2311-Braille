package commands;

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

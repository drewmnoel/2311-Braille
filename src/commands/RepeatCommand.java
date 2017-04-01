package commands;

public class RepeatCommand extends PlayerCommand {

	private String repeatedText;

	public RepeatCommand(String repeatedText) {
		this.repeatedText = repeatedText;
	}

	@Override
	public String toString() {
		return "Repeat: " + repeatedText;
	}

	@Override
	public String serialize() {
		StringBuilder sb = new StringBuilder();
		sb.append("/~repeat\n");
		sb.append(repeatedText + "\n");
		sb.append("/~endrepeat");

		return sb.toString();
	}

	@Override
	public String getEditLabel() {
		return "Text to be repeated";
	}

	@Override
	public String getCurrentValue() {
		return repeatedText;
	}

	@Override
	public void setCurrentValue(String repeatedText) {
		this.repeatedText = repeatedText;
	}

}

package commands;

public class SkipButtonCommand extends PlayerCommand {

	private String numAndIdentifier = "";

	@Override
	public String toString() {
		return "Skip button: " + numAndIdentifier;
	}

	@Override
	public String serialize() {
		return "/~skip-button: " + numAndIdentifier;
	}

	@Override
	public String getEditLabel() {
		return "Button and identifier (space separated)";
	}

	@Override
	public String getCurrentValue() {
		return numAndIdentifier;
	}

	@Override
	public void setCurrentValue(String numAndIdentifier) {
		this.numAndIdentifier = numAndIdentifier;
	}

}

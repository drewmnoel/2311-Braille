package commands;

public class UserInputCommand extends PlayerCommand {

	@Override
	public String toString() {
		return "User Input";
	}

	@Override
	public String serialize() {
		return "/~user-input";
	}

	@Override
	public String getEditLabel() {
		return "Ignored";
	}

	@Override
	public String getCurrentValue() {
		return null;
	}

	@Override
	public void setCurrentValue(String val) {
	}

}

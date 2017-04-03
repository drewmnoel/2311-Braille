package commands;

public class SetStringCommand extends PlayerCommand {

	private String str = "";

	@Override
	public String toString() {
		return "String: " + str;
	}

	@Override
	public String serialize() {
		return "/~disp-string: " + str;
	}

	@Override
	public String getEditLabel() {
		return "String to display";
	}

	@Override
	public String getCurrentValue() {
		return str;
	}

	@Override
	public void setCurrentValue(String str) {
		this.str = str;
	}

}

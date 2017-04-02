package commands;

public class CellRaiseCommand extends PlayerCommand {

	private String val = "";

	@Override
	public String toString() {
		return " " + val;
	}

	@Override
	public String serialize() {
		return "/~: " + val;
	}

	@Override
	public String getEditLabel() {
		return "";
	}

	@Override
	public String getCurrentValue() {
		return val;
	}

	@Override
	public void setCurrentValue(String val) {
		this.val = val;
	}

}

package commands;

public class ResetButtonCommand extends PlayerCommand {

	public ResetButtonCommand(String args) {
	}

	@Override
	public String toString() {
		return "Reset Buttons";
	}

	@Override
	public String serialize() {
		return "/~reset-buttons";
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

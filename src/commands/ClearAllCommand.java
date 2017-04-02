package commands;

public class ClearAllCommand extends PlayerCommand {

	@Override
	public String toString() {
		return "Clear All";
	}

	@Override
	public String serialize() {
		return "/~disp-clearAll";
	}

	@Override
	public String getEditLabel() {
		return "Ignored";
	}

	@Override
	public String getCurrentValue() {
		return "";
	}

	@Override
	public void setCurrentValue(String val) {
	}

}

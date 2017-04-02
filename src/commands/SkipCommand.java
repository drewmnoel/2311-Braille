package commands;

public class SkipCommand extends PlayerCommand {

	@Override
	public String toString() {
		return "Skip";
	}

	@Override
	public String serialize() {
		return "/~skip";
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

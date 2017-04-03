package commands;

public class SkipCommand extends PlayerCommand {
	
	private String skipTo = "";
	
	public SkipCommand(String skipTo) {
		this.skipTo = skipTo;
	}
	
	@Override
	public String toString() {
		return "Skip to: " + skipTo;
	}

	@Override
	public String serialize() {
		return "/~skip:" + skipTo;
	}

	@Override
	public String getEditLabel() {
		return "Enter label jump tag to skip to";
	}

	@Override
	public String getCurrentValue() {
		return skipTo;
	}

	@Override
	public void setCurrentValue(String val) {
		this.skipTo = val;
	}

}

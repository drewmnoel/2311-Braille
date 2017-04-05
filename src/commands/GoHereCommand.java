package commands;

public class GoHereCommand extends PlayerCommand {

	private String jumpTag = "";

	public GoHereCommand(String jumpTag) {
		this.jumpTag = jumpTag;
	}

	@Override
	public String toString() {
		return "Jump tag: " + jumpTag;
	}

	@Override
	public String serialize() {
		return "/~" + jumpTag;
	}

	@Override
	public String getEditLabel() {
		return "Enter name of jump tag";
	}

	@Override
	public String getCurrentValue() {
		return jumpTag;
	}

	@Override
	public void setCurrentValue(String jumpTag) {
		this.jumpTag = jumpTag;
	}

}

package commands;

public class SoundCommand extends PlayerCommand {

	private String file = "";

	public SoundCommand(String file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "Sound: " + file;
	}

	@Override
	public String serialize() {
		return "/~sound: " + file;
	}

	@Override
	public String getEditLabel() {
		return "File path: ";
	}

	@Override
	public String getCurrentValue() {
		return file;
	}

	@Override
	public void setCurrentValue(String file) {
		this.file = file;
	}

}

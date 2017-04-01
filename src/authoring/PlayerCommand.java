package authoring;

abstract public class PlayerCommand {
	protected String commandType;
	protected String description;

	@Override
	public String toString() {
		return commandType + description;
	}

	abstract public String serialize();
}

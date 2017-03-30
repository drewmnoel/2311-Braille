package authoring;

public class PlayerCommand {
	private static int refCount = 0;
	protected int myRef;
	protected String commandType = "";
	protected String description = "";
	
	public PlayerCommand() {
		myRef = refCount++;
	}

	@Override
	public String toString() {
		return (myRef) + " " + commandType + description;
	}
}

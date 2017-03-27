package authoring;

public class PlayerCommand {
	private static int refCount = 0;
	private int myRef;

	public PlayerCommand() {
		myRef = refCount++;
	}

	@Override
	public String toString() {
		return (myRef) + "";
	}
}

package authoring;

public class PlayerCommand {
	private static int refCount = 0;
	@Override
	public String toString() {
		return (refCount++) + "";
	}
}

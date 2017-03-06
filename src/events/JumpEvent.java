package events;

public class JumpEvent extends Event {

	@Override
	public int execute() {
		return Integer.parseInt(this.getDetails());
	}

}
package events;

/**
 * Representation of an unconditional Jump Event in the scenario. Events are
 * created by FileParser parsing an input file. Execution of this event will
 * indicate how many lines should be skipped in order to continue.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-03-05
 *
 */
public class JumpEvent extends Event {

	@Override
	public int execute() {
		return Integer.parseInt(this.getDetails());
	}

}
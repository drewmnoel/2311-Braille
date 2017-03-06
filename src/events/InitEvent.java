package events;

/**
 * Representation of the Init Event in the scenario. Events are created by
 * FileParser parsing an input file. Execution of this event will do nothing,
 * since this is only a dummy placeholder event type.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-03-05
 *
 */
public class InitEvent extends Event {

	@Override
	public int execute() {
		// This is a dummy event, created just as a placeholder for init
		return 1;
	}
}
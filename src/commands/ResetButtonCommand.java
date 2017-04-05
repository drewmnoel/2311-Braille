package commands;

/**
 * Command wrapper to represent the /~reset-buttons command in the player. No
 * values are required for this command, and any given are ignored.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-04-01
 */
public class ResetButtonCommand extends PlayerCommand {

	public ResetButtonCommand(String args) {
	}

	@Override
	public String toString() {
		return "Reset Buttons";
	}

	@Override
	public String serialize() {
		return "/~reset-buttons";
	}

	@Override
	public String getEditLabel() {
		return "Ignored";
	}

	@Override
	public String getCurrentValue() {
		return null;
	}

	@Override
	public void setCurrentValue(String val) {
	}

}

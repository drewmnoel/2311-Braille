package commands;

/**
 * Command wrapper to represent the /~skip command in the player. Values are
 * given as a String containing the target jump that should be sought to.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-04-01
 */
public class SkipCommand extends PlayerCommand {

	private String skipTo = "";

	public SkipCommand(String skipTo) {
		this.skipTo = skipTo;
	}

	@Override
	public String toString() {
		return "Skip to: " + skipTo;
	}

	@Override
	public String serialize() {
		return "/~skip:" + skipTo;
	}

	@Override
	public String getEditLabel() {
		return "Enter jump tag to skip to";
	}

	@Override
	public String getCurrentValue() {
		return skipTo;
	}

	@Override
	public void setCurrentValue(String val) {
		this.skipTo = val;
	}

}

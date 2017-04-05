package commands;

/**
 * Command wrapper to represent the /~disp-cell-pins command in the player.
 * Values are given as a cell number, followed by the pins that should be set
 * (the two components are separated by a space)
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-04-01
 */
public class SetPinsCommand extends PlayerCommand {

	private String cellAndPins = "";

	public SetPinsCommand(String cellAndPins) {
		this.cellAndPins = cellAndPins;
	}

	@Override
	public String toString() {
		return "Set Pins: " + cellAndPins;
	}

	@Override
	public String serialize() {
		return "/~disp-cell-pins:" + cellAndPins;
	}

	@Override
	public String getEditLabel() {
		return "Cell and pins (space separated)";
	}

	@Override
	public String getCurrentValue() {
		return cellAndPins;
	}

	@Override
	public void setCurrentValue(String cellAndPins) {
		this.cellAndPins = cellAndPins;
	}

}

package commands;

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
		return "/~disp-cell-pins: " + cellAndPins;
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

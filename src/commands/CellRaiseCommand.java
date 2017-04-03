package commands;

public class CellRaiseCommand extends PlayerCommand {

	private String cellAndPin = "";

	public CellRaiseCommand(String cellAndPin) {
		this.cellAndPin = cellAndPin;
	}

	@Override
	public String toString() {
		return "Cell and Pin Raise: " + cellAndPin;
	}

	@Override
	public String serialize() {
		return "/~disp-cell-raise: " + cellAndPin;
	}

	@Override
	public String getEditLabel() {
		return "Cell and Pin to raise (space separated)";
	}

	@Override
	public String getCurrentValue() {
		return cellAndPin;
	}

	@Override
	public void setCurrentValue(String cellAndPin) {
		this.cellAndPin = cellAndPin;
	}

}

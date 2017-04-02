package commands;

public class CellLowerCommand extends PlayerCommand {

	private String cellAndPin = "";

	@Override
	public String toString() {
		return "Cell and Pin Lower: " + cellAndPin;
	}

	@Override
	public String serialize() {
		return "/~disp-cell-lower: " + cellAndPin;
	}

	@Override
	public String getEditLabel() {
		return "Cell and Pin to lower (space separated)";
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

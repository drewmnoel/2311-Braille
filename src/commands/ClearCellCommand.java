package commands;

public class ClearCellCommand extends PlayerCommand {

	private String cellNumber = "";

	public ClearCellCommand(String cellNumber) {
		this.cellNumber = cellNumber;
	}

	@Override
	public String toString() {
		return "Clear Cell " + cellNumber;
	}

	@Override
	public String serialize() {
		return "/~disp-clear-cell:" + cellNumber;
	}

	@Override
	public String getEditLabel() {
		return "Cell number";
	}

	@Override
	public String getCurrentValue() {
		return cellNumber;
	}

	@Override
	public void setCurrentValue(String cellNumber) {
		this.cellNumber = cellNumber;
	}

}

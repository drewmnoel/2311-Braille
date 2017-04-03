package commands;

public class CellCharCommand extends PlayerCommand {

	private String cellAndChar = "";

	public CellCharCommand(String cellAndChar) {
		this.cellAndChar = cellAndChar;
	}

	@Override
	public String toString() {
		return "Cell and Char: " + cellAndChar;
	}

	@Override
	public String serialize() {
		return "/~disp-cell-char:" + cellAndChar;
	}

	@Override
	public String getEditLabel() {
		return "Cell and character (space separated)";
	}

	@Override
	public String getCurrentValue() {
		return cellAndChar;
	}

	@Override
	public void setCurrentValue(String cellAndChar) {
		this.cellAndChar = cellAndChar;
	}

}

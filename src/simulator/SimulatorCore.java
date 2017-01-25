package simulator;

import java.util.ArrayList;

public class SimulatorCore {
	
	private ArrayList<int[]> cellList;
	private int buttons;
	
	//Constructor creating a simulator with numCells braille cells and numButton buttons
	public SimulatorCore (int numCells, int numButton) throws Exception {
		if (numCells >= 10 || numCells < 1) {
			Exception error = new Exception("Enter a number of cells between 1 and 10");
			throw error;
		}
		
		int[] cell = {0, 0, 0, 0, 0, 0};
		cellList = new ArrayList<int[]>(numCells);
		
		for (int i = 0; i<numCells; i++) {
			cellList.add(cell);
		}
		
		buttons = numButton;
	}
	
	//Method to set a specific braille cell to raise/lower specified dots
	public void setCell(int cellNumber, int[] dots) throws Exception {
		if (cellNumber >= cellList.size() || cellNumber < 1) {
			Exception error = new Exception("Enter a legal cell number");
			throw error;
		}
		if (dots.length != 6) {
			Exception error = new Exception("Must set value for 6 dots");
			throw error;
		}
		cellList.set(cellNumber, dots);
	}
	
	//method to set all braille cells to lowered
	public void clearCells() {
		int[] cell = {0, 0, 0, 0, 0, 0};
		for (int i = 0; i<cellList.size(); i++) {
			cellList.set(i, cell);
		}
	}

	//method to return the braille cell at index i
	public int[] cellAt(int i) {
		return cellList.get(i);
	}
	
	//method to return a copy of the list of braille cells
	public ArrayList<int[]> allCells() {
		ArrayList<int[]> brailleCells = new ArrayList<int[]>();
		for(int i =0; i<cellList.size(); i++) {
			brailleCells.add(cellList.get(i));
		}
		return brailleCells;
	}
	
}

package simulator;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;

public class SimulatorCore {
	
	private ArrayList<int[]> cellList;
	private ObservableList<int[]> cellObserver;
	private int buttons;
	
	//Constructor creating a simulator with numCells braille cells and numButton buttons
	public SimulatorCore (int numCells, int numButton) throws Exception {
		if (numCells >= 10 || numCells < 1) {
			Exception error = new Exception("Enter a number of cells between 1 and 10");
			throw error;
		}
	
		cellList = new ArrayList<int[]>(numCells);
		
		for (int i = 0; i<numCells; i++) {
			int[] cell = {0, 0, 0, 0, 0, 0, 0, 0};
			cellList.add(cell);
		}
		cellObserver = FXCollections.observableList(cellList);
		cellObserver.addListener(new ListChangeListener() {
			
			@Override
			public void onChanged(ListChangeListener.Change change) {
				//add changes to display here
				
			}
		});
		
		buttons = numButton;
	}
	
	//Method to set a specific braille cell to raise/lower specified dots
	public void setCell(int cellNumber, int[] dots) throws Exception {
		if (cellNumber >= cellObserver.size() || cellNumber < 0) {
			Exception error = new Exception("Enter a legal cell number");
			throw error;
		}
		if (dots.length != 8) {
			Exception error = new Exception("Must set value for 8 dots");
			throw error;
		}
		cellObserver.set(cellNumber, dots);
	}
	
	//method to set all braille cells to lowered
	public void clearCells() {
		
		for (int i = 0; i<cellObserver.size(); i++) {
			int[] cell = {0, 0, 0, 0, 0, 0, 0, 0};
			cellObserver.set(i, cell);
		}
	}

	//method to return the braille cell at index i
	public int[] cellAt(int i) {
		return cellObserver.get(i);
	}
	
	//method to return a copy of the list of braille cells
	public ArrayList<int[]> allCells() {
		ArrayList<int[]> brailleCells = new ArrayList<int[]>();
		for(int i =0; i<cellObserver.size(); i++) {
			brailleCells.add(cellObserver.get(i));
		}
		return brailleCells;
	}
	
	public int numOfCells() {
		return cellObserver.size();
	}
	
}

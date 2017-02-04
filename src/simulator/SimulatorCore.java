package simulator;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * <h1>SimulatorCore</h1>
 * 
 * <p>The SimulatorCore class provides a simulation of the function of a braille player hardware. It supports the creation of a simulation of hardware with 1 to 10 braille cells and 1 to 5 buttons.</p>
 * 
 * 
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 *
 */

public class SimulatorCore {

	protected static ObservableList<int[]> cellObserver = null;
	private static int buttons;
	private static SimulatorCore sc;
	private static boolean ready = false;

	static {
		sc = new SimulatorCore();
	}

	private SimulatorCore() {}

	public static SimulatorCore getInstance() {
		return sc;
	}

	// Constructor creating a simulator with numCells braille cells and
	// numButton buttons
	public SimulatorCore(int numCells, int numButton) throws SimulatorException {
		populate(numCells, numButton);
	}

	/**
	 * Actually populate the elements of the core
	 *
	 * @param numCells Number of cells desired
	 * @param numButton Number of buttons desired
	 * @throws SimulatorException if the values are outside accepted ranges
	 */
	public void populate(int numCells, int numButton) throws SimulatorException {
		if (numCells >= 11 || numCells < 1) {
			SimulatorException error = new SimulatorException("Enter a number of cells between 1 and 10");
			throw error;
		}
		if (numButton >= 6 || numButton < 1) {
			SimulatorException error = new SimulatorException("Enter a number of buttons between 1 and 5");
			throw error;
		}

		if (cellObserver == null) {
			cellObserver = FXCollections.observableList(new ArrayList<int[]>(numCells));
		}

		cellObserver.clear();

		for (int i = 0; i < numCells; i++) {
			int[] cell = { 0, 0, 0, 0, 0, 0, 0, 0 };
			cellObserver.add(i, cell);
		}

		buttons = numButton;
		ready = true;
	}

	// Method to set a specific braille cell to raise/lower specified dots
	public void setCell(int cellNumber, int[] dots) throws SimulatorException {
		if (cellNumber >= cellObserver.size() || cellNumber < 0) {
			SimulatorException error = new SimulatorException("Enter a legal cell number");
			throw error;
		}
		if (dots.length != 8) {
			SimulatorException error = new SimulatorException("Must set value for 8 dots");
			throw error;
		}
		cellObserver.set(cellNumber, dots);
	}

	// method to set all braille cells to lowered
	public void clearCells() {

		for (int i = 0; i < cellObserver.size(); i++) {
			int[] cell = { 0, 0, 0, 0, 0, 0, 0, 0 };
			cellObserver.set(i, cell);
		}
	}

	// method to return the braille cell at index i
	public int[] cellAt(int i) throws SimulatorException {
		if (i >= cellObserver.size() || i < 0) {
			SimulatorException error = new SimulatorException("Enter a legal cell number");
			throw error;
		}
		return cellObserver.get(i);
	}

	// method to return an ArrayList copy of the list of braille cells
	public ArrayList<int[]> allCells() {
		ArrayList<int[]> brailleCells = new ArrayList<int[]>();
		for (int i = 0; i < cellObserver.size(); i++) {
			brailleCells.add(cellObserver.get(i));
		}
		return brailleCells;
	}

	//Returns the number of braille cells
	public int numOfCells() {
		return cellObserver.size();
	}

	public int numOfButtons() {
		return buttons;
	}

	public static boolean ready() {
		return ready;
	}
}

package simulator;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * <h1>SimulatorCore</h1>
 * 
 * <p>The SimulatorCore class provides a simulation of the function of a braille player hardware. 
 * It supports the creation of a simulation of hardware with 1 to 10 braille cells and 1 to 5 buttons.
 * The SimulatorCore class is implemented using a singleton design, to ensure that only one simulation is instantiated at a time.
 * 
 * 
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-02-07
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

	/**
	 * This constructor is made public for testing purposes. 
	 * In actual implementation it will be made private.
	 * 
	 * @param numCells Number of cells desired, must be between 1 and 10
	 * @param numButton Number of buttons desired, must be between 1 and 5
	 * @throws SimulatorException if the values are outside the accepted ranges
	 */
	public SimulatorCore(int numCells, int numButton) throws SimulatorException {
		populate(numCells, numButton);
	}

	/**
	 * Use this method to set the number of braille cells and buttons desired in the braille hardware simulation.
	 * This method sets all braille cells to have their pins in the default 'lowered' position.
	 * Until the number of braille cells and buttons is set using this method, no braille cells or buttons will be present on the simulator.
	 *
	 * @param numCells Number of cells desired, must be between 1 and 10
	 * @param numButton Number of buttons desired, must be between 1 and 5
	 * @throws SimulatorException if the values are outside the accepted ranges
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
	/**
	 * Use this method to set the pin states for a specific braille cell in the simulator
	 * Braille cells are 0 indexed.
	 * The pins on a braille cell are represented using an integer array of length 8
	 * Values of '0' indicate lowered pins, values of '1' indicate raised pins
	 * Pins are a 0 indexed version of the standard numbering of dots in a braille cell
	 * 
	 * @param cellNumber The index of the braille cell to be set
	 * @param dots A length 8 integer array
	 * @throws SimulatorException
	 */
	// Method to set a specific braille cell to raise/lower specified dots
	public void setCell(int cellNumber, int[] dots) throws SimulatorException {
		if (cellNumber >= cellObserver.size() || cellNumber < 0) {
			SimulatorException error = new SimulatorException("Enter a legal cell number");
			throw error;
		}
		if (dots.length != 8) {
			SimulatorException error = new SimulatorException("Must set value for 8 pins");
			throw error;
		}
		for (int i=0; i<8; i++) {
			if (dots[i] != 0 && dots[i] != 1) {
				SimulatorException error = new SimulatorException("Argument dots must only have values of 0 or 1");
				throw error;
			}
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

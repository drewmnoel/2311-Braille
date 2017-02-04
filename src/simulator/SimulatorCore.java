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
	 * Use this method to set the pin states for a specific braille cell in the simulator.
	 * Braille cells are 0 indexed.
	 * The pins on a braille cell are represented using an integer array of length 8.
	 * Values of '0' indicate lowered pins, values of '1' indicate raised pins.
	 * Pins are numbered using a 0 indexed version of the standard numbering of dots in a braille cell.
	 * 
	 * @param cellNumber The index of the braille cell to be set
	 * @param dots A length 8 integer array containing only 0's and 1's
	 * @throws SimulatorException if cellNumber is not a valid braille cell index
	 * @throws SimulatorException if dots is not length 8
	 * @throws SimulatorException if dots contains an integer other than 0 or 1
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

	
	/**
	 * Use this method to set all the pins in all braille cells in the simulator to 'lowered' position
	 */
	public void clearCells() {

		for (int i = 0; i < cellObserver.size(); i++) {
			int[] cell = { 0, 0, 0, 0, 0, 0, 0, 0 };
			cellObserver.set(i, cell);
		}
	}

	/**
	 * Use this method to retrieve the state of pins in a single braille cell in the simulator.
	 * Braille cells 0 indexed.
	 * This method returns a length 8 integer array .
	 * Pins are numbered using a 0 indexed version of the standard numbering of dots in a braille cell.
	 * Values of '0' indicate lowered pins, values of '1' indicate raised pins.
	 * 
	 * @param i Index of the braille cell whose state is being returned
	 * @return A length 8 integer array of 0's and 1's
	 * @throws SimulatorException if i is not a valid braille cell index
	 */
	public int[] cellAt(int i) throws SimulatorException {
		if (i >= cellObserver.size() || i < 0) {
			SimulatorException error = new SimulatorException("Enter a legal cell number");
			throw error;
		}
		return cellObserver.get(i);
	}

	
	/**
	 * Use this method to retrieve the state of all braille cells in the simulator.
	 * Braille cells are represented as length 8 integer arrays, with '0' indicating lowered and '1' indicating raised pins
	 * The ArrayList returned by this method is ordered the same as the braille cells in the simulator
	 * 
	 * @return an Arraylist of int arrays representations of all the braille cells in the simulator
	 */
	public ArrayList<int[]> allCells() {
		ArrayList<int[]> brailleCells = new ArrayList<int[]>();
		for (int i = 0; i < cellObserver.size(); i++) {
			brailleCells.add(cellObserver.get(i));
		}
		return brailleCells;
	}

	/**
	 * Use this method to retrieve the number of braille cells present in the simulator
	 * 
	 * @return an int between 1 and 10 of how many braille cells are present in the simulator
	 */
	public int numOfCells() {
		return cellObserver.size();
	}

	/**
	 * Use this method to retrieve the number of buttons present in the simulator
	 * 
	 * @return in int between 1 and 5 of how many buttons are present in the simulator
	 */
	public int numOfButtons() {
		return buttons;
	}

	/**
	 * Use this method to query the simulator to check if it has been properly initialized with braille cells and buttons
	 * 
	 * @return boolean value, with true indicating the simulator is properly intialized
	 */
	public static boolean ready() {
		return ready;
	}
}

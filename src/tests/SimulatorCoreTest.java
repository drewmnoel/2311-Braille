package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import simulator.SimulatorCore;
import simulator.SimulatorException;

/**
 * JUnit tests for the SimulatorCore class
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-01-30
 *
 */
public class SimulatorCoreTest {

	SimulatorCore testSim;
	SimulatorCore notReady;
	int[] emptyCell = new int[8];

	/**
	 * Set the expected exception for this set of tests
	 */
	@Rule
	public ExpectedException exception = ExpectedException.none();

	/**
	 * Create a basic simulator core for later user
	 *
	 * @throws SimulatorException If the simulator core generates an exception
	 */
	@Before
	public void setUp() throws SimulatorException {
		testSim = SimulatorCore.getInstance();
		testSim.populate(3,5);
		for(int i=0; i<8; i++) {
			emptyCell[i] = 0;
		}

		notReady = SimulatorCore.getInstance();
	}

	/**
	 * Checks that constructor creates a SimulatorCore is all 0 valued
	 *
	 * @throws SimulatorException
	 */
	@Test
	public void testCreation() throws SimulatorException {
		for(int i=0; i < testSim.numOfCells(); i++) {
			for(int j=0; j<8; j++) {
				int temp = testSim.cellAt(i)[j];
				assertEquals(0, temp);
			}
		}
	}

	/**
	 * Check basic functionality of numOfButtons()
	 */
	@Test
	public void testNumOfButtson() {
		assertEquals(5, testSim.numOfButtons());
	}

	/**
	 * Checks that setCell() changes cells correctly
	 *
	 * @throws SimulatorException
	 */
	@Test
	public void testSetCell() throws SimulatorException {

		int[]cell_0 = {0, 1, 0, 1, 0, 1, 0, 1};
		int[]cell_1 = {1, 0, 1, 0, 1, 0, 1, 0};
		int[]cell_2 = {1, 1, 1, 1, 1, 1, 1, 1};
		testSim.setCell(0, cell_0);
		testSim.setCell(1, cell_1);
		testSim.setCell(2, cell_2);

		assertEquals(testSim.cellAt(0), cell_0);
		assertEquals(testSim.cellAt(1), cell_1);
		assertEquals(testSim.cellAt(2), cell_2);
	}

	/**
	 * Tests clearCells() clears all cells to 0 after being assigned other values
	 *
	 * @throws SimulatorException
	 */
	@Test
	public void testClearCells() throws SimulatorException {
		int[]cell_0 = {0, 1, 0, 1, 0, 1, 0, 1};
		int[]cell_1 = {1, 0, 1, 0, 1, 0, 1, 0};
		int[]cell_2 = {1, 1, 1, 1, 1, 1, 1, 1};
		testSim.setCell(0, cell_0);
		testSim.setCell(1, cell_1);
		testSim.setCell(2, cell_2);

		testSim.clearCells();
		for(int i=0; i < testSim.numOfCells(); i++) {
			for(int j=0; j<8; j++) {
				int temp = testSim.cellAt(i)[j];
				assertEquals(0, temp);
			}
		}
	}

	/**
	 * Tests whether cellAt() returns the proper cell
	 *
	 * @throws SimulatorException
	 */
	@Test
	public void testCellAt() throws SimulatorException{
		int[]cell_1 = {1, 0, 1, 0, 1, 0, 1, 0};
		testSim.setCell(1, cell_1);

		for(int i=0; i<testSim.numOfCells(); i++) {
			for(int j=0; j<8; j++) {
				int temp = testSim.cellAt(i)[j];
				if(i != 1) {
					assertEquals(0, temp); //Test other cells are unchanged, still 0
				}
				else { //Test changed cell
					if(j%2==0) {
						assertEquals(1, temp);
					}
					else {
						assertEquals(0, temp);
					}
				}
			}
		}
	}

	/**
	 * Ensure allCells() returns a list containing all cells
	 *
	 * @throws SimulatorException
	 */
	@Test
	public void testAllCells() throws SimulatorException {
		int[]cell_0 = {0, 1, 0, 1, 0, 1, 0, 1};
		int[]cell_1 = {1, 0, 1, 0, 1, 0, 1, 0};
		int[]cell_2 = {1, 1, 1, 1, 1, 1, 1, 1};
		testSim.setCell(0, cell_0);
		testSim.setCell(1, cell_1);
		testSim.setCell(2, cell_2);

		ArrayList<int[]> testList1 = new ArrayList<int[]>();
		ArrayList<int[]> testListExpected = new ArrayList<int[]>();
		testListExpected.add(cell_0);
		testListExpected.add(cell_1);
		testListExpected.add(cell_2);

		testList1 = testSim.allCells();

		assertTrue(testListExpected.equals(testList1));
	}

	/**
	 * Ensure numCells() returns the right number of cells
	 *
	 * @throws SimulatorException
	 */
	@Test
	public void testNumCells() throws SimulatorException {
		assertEquals(3, testSim.numOfCells());
	}

	/**
	 * Tests that SimulatorCore constructor throws an exception for numCells < 1 or numCells > 10
	 *
	 * @throws SimulatorException
	 */
	@Test
	public void testConstructorException() throws SimulatorException {
		exception.expect(SimulatorException.class);
		exception.expectMessage("Enter a number of cells between 1 and 10");
		SimulatorCore badSim = SimulatorCore.getInstance();
		badSim.populate(11,5);
		badSim.populate(0,5);
	}

	/**
	 * Tests that setCell() throws the proper exception for an invalid cell number
	 *
	 * @throws SimulatorException
	 */
	@Test
	public void testSetCellExceptionIndex() throws SimulatorException {
		exception.expect(SimulatorException.class);
		exception.expectMessage("Enter a legal cell number");
		int[]cell_0 = {0, 1, 0, 1, 0, 1, 0, 1};
		testSim.setCell(3, cell_0);
	}

	/**
	 * Tests that setCell() throws the proper exception for a non-8 element array
	 *
	 * @throws SimulatorException
	 */
	@Test
	public void testSetCellExceptionCellSize() throws SimulatorException {
		exception.expect(SimulatorException.class);
		exception.expectMessage("Must set value for 8 pins");
		int[]cell_0 = {0, 1, 0, 1, 0, 1};
		testSim.setCell(1, cell_0);
	}

	/**
	 * Tests that cellAt() throws the proper exception for an invalid cell number
	 *
	 * @throws SimulatorException
	 */
	@Test
	public void testSetCellAtException() throws SimulatorException {
		exception.expect(SimulatorException.class);
		exception.expectMessage("Enter a legal cell number");
		testSim.cellAt(-1);
		testSim.cellAt(3);
	}

	/**
	 * Test instantiating SimulatorCores with 1 to 10 braille cells
	 *
	 * @throws SimulatorException
	 */
	@Test
	public void testCreateMultipleCells() throws SimulatorException {
		SimulatorCore testMultiCells;
		for (int i=1; i<=10; i++) {
			testMultiCells = new SimulatorCore(i, 2);
			assertEquals(i, testMultiCells.numOfCells());
			assertEquals(2, testMultiCells.numOfButtons());
		}
	}

	/**
	 * Test instantiating SimulatorCores with 1 to 5 buttons
	 *
	 * @throws SimulatorException
	 */
	@Test
	public void testCreateMultipleButtons() throws SimulatorException {
		SimulatorCore testMultiCells;
		for (int i=1; i<=5; i++) {
			testMultiCells = new SimulatorCore(3, i);
			assertEquals(3, testMultiCells.numOfCells());
			assertEquals(i, testMultiCells.numOfButtons());
		}
	}

	/**
	 * Test setCell() and cellAt() for SimCores with 1-10 braille cells
	 *
	 * @throws SimulatorException
	 */
	@Test
	public void testMutlipleCellsGetAndSet() throws SimulatorException {
		SimulatorCore[] simArray = new SimulatorCore[10];
		int[][] testCell = new int[10][8];

		//Create 10 SimulatorCores with 1-10 braille cells
		//Create 10 length 8 int arrays with a single 1 in them
		for (int i = 0; i <10; i++) {
			simArray[i] = new SimulatorCore(i+1, 3);
			testCell[i][i/2]=1;
		}
		//Set cell 0 for SimCore with 1 cell, set cell 1 for SimCore with 2 cells, etc.
		for (int i=0; i<10; i++) {
			simArray[i].setCell(i, testCell[i]);
		}

		//Check that the cells have been set properly
		for (int i=0; i<10; i++) {
			assertEquals(simArray[i].cellAt(i), testCell[i]);
		}
	}

	/**
	 * Tests clearCells() method for SimCores with 1-10 cells
	 *
	 * @throws SimulatorException
	 */
	@Test
	public void testMultipleCellClearCells() throws SimulatorException {
		int[] putCell = { 1, 1, 1, 1, 1, 1, 1, 1 };

		SimulatorCore[] simArray = new SimulatorCore[10];
		//Creates 10 SimCores with 1 to 10 braille cells
		//Sets all cells to be all 1's
		for (int i = 0; i <10; i++) {
			simArray[i] = new SimulatorCore(i+1, 3);
			for(int j=i; j>=0; j--) {
				simArray[i].setCell(j, putCell);
			}
		}
		//calls clearCells() for each SimCore
		for (int i = 0; i <10; i++) {
			simArray[i].clearCells();
		}

		//asserts that all cells are 0's
		for(int i=0; i<10; i++) {
			for(int j=i; j>=0; j--) {
				assertTrue(Arrays.equals(simArray[i].cellAt(j), emptyCell));
			}
		}
	}

	/**
	 * Test allCells for SimCores with 1-10 braille cells
	 *
	 * @throws SimulatorException
	 */
	@Test
	public void testMultipleCellsAllCells() throws SimulatorException {
		SimulatorCore[] simArray = new SimulatorCore[10];
		int[] putCell = { 0, 0, 0, 0, 0, 0, 0, 0 };
		ArrayList<int[]> expectedList = new ArrayList<int[]>();
		ArrayList<int[]> testList;

		//creates 10 SimCores, with 1-10 braille cells
		//and tests allCells() for each one
		for (int i = 0; i <10; i++) {
			simArray[i] = new SimulatorCore(i+1, 3);
			putCell[i%8] = 1;
			simArray[i].setCell(i, putCell);
			//populates the expected list
			for(int j=0; j<=i; j++) {
				if(j==i) {
					expectedList.add(putCell);
				}
				else {
					expectedList.add(emptyCell);
				}
			}
			//compare list returned by allCells() and expectedList
			testList = simArray[i].allCells();
			for(int j=0; j<=i; j++) {
				assertTrue(Arrays.equals(expectedList.get(j), testList.get(j)));
			}
			expectedList.clear();
		}
	}

	/**
	 * Test functionality of SimCores with 1 - 5 buttons
	 *
	 * @throws SimulatorException
	 */
	@Test
	public void testMultipleButtons() throws SimulatorException {
		SimulatorCore[] simArray = new SimulatorCore[5];
		for(int i = 0; i<5; i++) {
			simArray[i] = new SimulatorCore(i+1, i+1);
			assertEquals(i+1, simArray[i].numOfButtons());
			assertEquals(i+1, simArray[i].numOfCells());
		}
	}

	/**
	 * Test that trying to use setCell() with an array with values other than 0 or 1 throws an exception
	 *
	 * @throws SimulatorException
	 */
	@Test
	public void testSetCellNot0Or1() throws SimulatorException {
		int[] badCell = { 0, 2, 1, 0, 1, 0, 3, 0 };
		exception.expect(SimulatorException.class);
		exception.expectMessage("Argument dots must only have values of 0 or 1");
		testSim.setCell(0, badCell);

	}

	/**
	 * Test that ready() correctly returns true if the SimCore is initialized
	 *
	 * @throws SimulatorException
	 */
	@Test
	public void testReady() throws SimulatorException {

		assertTrue(SimulatorCore.ready());
	}
}

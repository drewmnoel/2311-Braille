package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import simulator.SimulatorCore;
import simulator.SimulatorException;

public class SimulatorCoreTest {
	
	SimulatorCore testSim;
	int[] emptyCell = new int[8];
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setUp() throws SimulatorException {
		testSim = new SimulatorCore(3,5);
		for(int i=0; i<8; i++) {	
			emptyCell[i] = 0;
		}
	}

	@Test
	//Checks that constructor creates a SimulatorCore is all 0 valued
	public void testCreation() {
		for(int i=0; i < testSim.numOfCells(); i++) {
			for(int j=0; j<8; j++) {
				int temp = testSim.cellAt(i)[j];
				assertEquals(0, temp);
			}
		}
	}
	
	@Test
	//Checks that setCell() changes cells correctly
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
	
	@Test 
	//Tests clearCells() clears all cells to 0 after being assigned other values
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

	@Test 
	//Tests whether cellAt() returns the proper cell
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
	
	@Test
	//Ensure allCells() returns a list containing all cells
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
	
	@Test
	//Ensure numCells() returns the right number of cells
	public void testNumCells() throws SimulatorException {
		assertEquals(3, testSim.numOfCells());
	}
	
	@Test
	//Tests that SimulatorCore constructor throws an exception for numCells > 10
	public void testConstructorExceptionGreaterThan10() throws SimulatorException {
		exception.expect(SimulatorException.class);
		exception.expectMessage("Enter a number of cells between 1 and 10");
		SimulatorCore badSim = new SimulatorCore(3,15);
	}
	
	@Test
	//Tests that SimulatorCore constructor throws an exception for numCells <1
	public void testConstructorExceptionLessThan1() throws SimulatorException {
		exception.expect(SimulatorException.class);
		exception.expectMessage("Enter a number of cells between 1 and 10");
		SimulatorCore badSim = new SimulatorCore(0,15);
	}
}

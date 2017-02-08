package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.sun.prism.paint.Color;

import javafx.scene.shape.Circle;
import simulator.BrailleCircle;
import simulator.SimulatorException;

/**
 * JUnit tests for the BrailleCircle class
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-01-31
 *
 */
public class BrailleCircleTest {

	private BrailleCircle circle;
	private int[][] array = {{1, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0},
	{1, 1, 0, 0, 0, 0, 0, 0},
	{1, 0, 0, 0, 1, 0, 0, 0},
	{1, 0, 0, 0, 1, 1, 0, 0},
	{1, 0, 0, 0, 0, 1, 0, 0},
	{1, 1, 0, 0, 1, 0, 0, 0},
	{1, 1, 0, 0, 1, 1, 0, 0},
	{1, 1, 0, 0, 0, 1, 0, 0},
	{0, 1, 0, 0, 1, 0, 0, 0},
	{0, 1, 0, 0, 1, 1, 0, 0},
	{1, 0, 1, 0, 0, 0, 0, 0},
	{1, 1, 1, 0, 0, 0, 0, 0},
	{1, 0, 1, 0, 1, 0, 0, 0},
	{1, 0, 1, 0, 1, 1, 0, 0},
	{1, 0, 1, 0, 0, 1, 0, 0},
	{1, 1, 1, 0, 1, 0, 0, 0},
	{1, 1, 1, 0, 1, 1, 0, 0},
	{1, 1, 1, 0, 0, 1, 0, 0},
	{0, 1, 1, 0, 1, 0, 0, 0},
	{0, 1, 1, 0, 1, 1, 0, 0},
	{1, 0, 1, 0, 0, 0, 1, 0},
	{1, 1, 1, 0, 0, 0, 1, 0},
	{1, 0, 1, 0, 1, 0, 1, 0},
	{1, 0, 1, 0, 1, 1, 1, 0},
	{1, 0, 1, 0, 0, 1, 1, 0},
	{0, 1, 1, 0, 1, 1, 1, 0},
	{0, 0, 0, 0, 0, 0, 0, 0},
	{0, 0, 0, 0, 0, 0, 0, 1}
	};

	/**
	 * Set up the circle to be the last entry in the preset array. Verifies that no exceptions are thrown when setting a circle.
	 *
	 * @throws SimulatorException If the circle initialization generates an exception
	 */
	@Before
	public void setUp() throws SimulatorException {
		for(int i = 0;i < array.length;i++){
			for(int runningIndex = 0;runningIndex < 8;runningIndex++){
				circle = new BrailleCircle(array[i], runningIndex);
			}
		}
	}

	/**
	 * Tests that each element of the Braille cell will have the correct color.
	 */
	@Test
	public void testgetHexFillColor(){
		//get numeric value of red, green, blue in color BLACK and convert it to hexacimal value
		String testColor = "#" +(int)Color.BLACK.getRed()*255 + (int)Color.BLACK.getGreen() * 255 + (int)Color.BLACK.getBlue() * 255;
			//iterate though each alphabet array
			for(int i = 0;i < array.length;i++){
				//iterate though coordinates of alphabet and create circle accordingly
				for(int runningIndex = 0;runningIndex < 8;runningIndex++){
					circle = new BrailleCircle(array[i], runningIndex);
					//if coordinate value is 1 then circle must be filled with black color otherwise empty(i.e. white color)
					if(array[i][runningIndex] == 1){
						assertEquals(testColor,circle.getHexFillColor());
					}else{
						assertEquals(null,circle.getHexFillColor());
					}
				}
		}
	}

	/**
	 * Tests that the stroke color of each element of a Braille cell will be correct
	 */
	@Test
	public void testgetHexStrokeColor(){
		//get hexadecimal value of color black
		String testColor = "#" +(int)Color.BLACK.getRed()*255 + (int)Color.BLACK.getGreen() * 255 + (int)Color.BLACK.getBlue() * 255;
		for(int i = 0;i < array.length;i++){
			for(int j = 0;j < 8;j++){
				circle = new BrailleCircle(array[i], j);
				//check for border color of circle
				assertEquals(testColor,circle.getHexStrokeColor());
			}
		}
	}

	/**
	 * Minimal test to verify there is no null pointer
	 */
	@Test
	public void testGetCircle(){
		assertTrue(circle.getCircle() instanceof Circle);
	}


}

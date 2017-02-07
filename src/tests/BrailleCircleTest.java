package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.prism.paint.Color;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import simulator.BrailleCircle;
import simulator.SimulatorCore;
import simulator.SimulatorException;

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
	//iterating though possible letters and capital {0, 0, 0, 0, 0, 0, 0, 1} to initialize circles
	@Before
	public void setUp() throws SimulatorException {
		for(int i = 0;i < array.length;i++){		
			for(int runningIndex = 0;runningIndex < 8;runningIndex++){
				circle = new BrailleCircle(array[i], runningIndex);		
			}
		}
	}
	
	//
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
	
	@Test
	public void testGetCircle(){
		assertTrue(circle.getCircle() instanceof Circle);
	}
	

}

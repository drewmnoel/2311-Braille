package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.prism.paint.Color;

import javafx.scene.paint.Paint;
import simulator.BrailleCircle;

public class BrailleCircleTest {

	private BrailleCircle circle;
	private BrailleCircle testCircle;
	private int[] array = {0, 0, 0, 0, 0, 0, 0, 0};
	
	@Before
	public void setUp() throws Exception {				
			circle = new BrailleCircle(array, 0);
	}
	
	@Test
	public void testgetHexFillColor(){		
		String testColor = "#" +(int)Color.BLACK.getRed()*255 + (int)Color.BLACK.getGreen() * 255 + (int)Color.BLACK.getBlue() * 255;
		
		for(int i = 0;i < 8;i++){			
			if(array[i] == 1){
				//System.out.println("inside one");
				//System.out.println(array[i] + " " + circle.getHexFillColor());
				assertEquals(testColor,circle.getHexFillColor());				
			}else{
				//System.out.println("Inside zero");
				//System.out.println(array[i] + " " + circle.getHexFillColor());
				assertEquals(null,circle.getHexFillColor());				
			}	
		}
	}
	@Test
	public void testgetHexStrokeColor(){		
		String testColor = "#" +(int)Color.BLACK.getRed()*255 + (int)Color.BLACK.getGreen() * 255 + (int)Color.BLACK.getBlue() * 255;
		for(int i = 0;i < 8;i++){			
				assertEquals(testColor,circle.getHexStrokeColor());					
		}
	}
	

}

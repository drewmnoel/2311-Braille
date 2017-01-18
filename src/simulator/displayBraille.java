package simulator;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class displayBraille {
	
	static void putBraille(String input, GridPane gridPane) {	
		
		for(int i=0; i<input.length(); i++) {
			Label temp = new Label(Character.toString(input.charAt(i)));
			gridPane.add(temp, i+3, 3);
		}
	}
}

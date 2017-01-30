package simulator;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BrailleCircle {
	public static Node getCircle(int[] cell, int pos) {
		Circle circle = new Circle(CreateCells.BRAILLE_BOX_SIDE / 2, CreateCells.BRAILLE_BOX_SIDE / 2, CreateCells.BRAILLE_DOT_RADIUS);
		if (cell[pos] == 1) {
			circle.setFill(Color.BLACK);
		} else {
			circle.setVisible(false);
		}

		return circle;
	}
}
package simulator;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Utility class to represent a dot in a Braille cell pictographically
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-01-30
 *
 */
public class BrailleCircle {
	private Circle circle;

	/**
	 * Create a new circle from a pin setting. A value of 1 indicates a raised
	 * pin (filled in on the UI) and otherwise indicates a lowered pin.
	 *
	 * @param cell
	 *            A Braille cell array
	 * @param pos
	 *            The particular entry in the array to represent
	 */
	public BrailleCircle(int[] cell, int pos) {
		circle = new Circle(CreateCells.BRAILLE_BOX_SIDE / 2, CreateCells.BRAILLE_BOX_SIDE / 2,
				CreateCells.BRAILLE_DOT_RADIUS);
		if (cell[pos] == 1) {
			circle.setStroke(Color.BLACK);
			circle.setFill(Color.BLACK);
		} else {
			circle.setStroke(Color.BLACK);
			circle.setFill(null);
			circle.setStrokeWidth(1);
		}
	}

	/**
	 * Obtain the representation of the cell entry
	 *
	 * @return Circle of the requested cell entry
	 */
	public Circle getCircle() {
		return circle;
	}

	/**
	 * Obtain the current fill color in hex
	 *
	 * @return Standard hex representation of the fill color
	 */
	public String getHexFillColor() {
		if (circle.getFill() != null) {
			Color c = (Color) circle.getFill();
			String hex = String.format("#%x%x%x", (int) (c.getRed() * 255), (int) (c.getGreen() * 255),
					(int) (c.getBlue() * 255));
			return hex;
		} else {
			return null;
		}
	}

	/**
	 * Obtain the current line stroke color in hex
	 *
	 * @return Standard hex representation of the stroke color
	 */
	public String getHexStrokeColor() {
		if (circle.getStroke() != null) {
			Color c = (Color) circle.getStroke();
			String hex = String.format("#%x%x%x", (int) (c.getRed() * 255), (int) (c.getGreen() * 255),
					(int) (c.getBlue() * 255));
			return hex;
		} else {
			return null;
		}
	}

}
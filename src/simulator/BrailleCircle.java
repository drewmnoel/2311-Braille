package simulator;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BrailleCircle {
	private Circle circle;
	public BrailleCircle(int[] cell, int pos) {
		circle = new Circle(CreateCells.BRAILLE_BOX_SIDE / 2, CreateCells.BRAILLE_BOX_SIDE / 2, CreateCells.BRAILLE_DOT_RADIUS);
		if (cell[pos] == 1) {
			circle.setStroke(Color.BLACK);
			circle.setFill(Color.BLACK);
		} else {
			circle.setStroke(Color.BLACK);
			circle.setFill(null);
			circle.setStrokeWidth(1);
		}
	}
	public Circle getCircle(){
		return circle;
	}
	public String getHexFillColor(){
		if(circle.getFill() != null){
			Color c = (Color) circle.getFill();
			String hex = String.format( "#%x%x%x",
			            (int)( c.getRed() * 255 ),
			            (int)( c.getGreen() * 255 ),
			            (int)( c.getBlue() * 255 ) );
			return hex;
		}
		else{
			return null;
		}
	}
	public String getHexStrokeColor(){
		if(circle.getStroke() != null){
			Color c = (Color) circle.getStroke();
			String hex = String.format( "#%x%x%x",
			            (int)( c.getRed() * 255 ),
			            (int)( c.getGreen() * 255 ),
			            (int)( c.getBlue() * 255 ) );
			return hex;
		}else{
			return null;
		}
	}



}
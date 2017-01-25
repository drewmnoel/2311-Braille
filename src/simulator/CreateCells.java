package simulator;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import simulator.SimulatorCore;

public class CreateCells extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		int rows = 3;
		int columns = 2;
		primaryStage.setTitle("Enjoy your game.");
		
		SimulatorCore simCore = new SimulatorCore(3, 5);
		
		GridPane grid = new GridPane();
		//grid.getStyleClass().add("game-grid");
		Button yes = new Button("YES");
		Button no = new Button("NO");
		
		for(int i = 0;i < columns;i++){
			ColumnConstraints column = new ColumnConstraints(40);
			grid.getColumnConstraints().add(column);
		}
		for(int i = 0;i < rows;i++){
			RowConstraints row = new RowConstraints(40);
			grid.getRowConstraints().add(row);
		}
		 
		for(int k=0; k<simCore.numOfCells(); k++) {
			for (int i = 0; i < columns; i++) {
		            for (int j = 0; j < rows; j++) {
		                Pane pane = new Pane();
		                pane.getChildren().add(balls.getBalls());
		                pane.getStyleClass().add("game-grid-cell");
		                if (i == 0) {
		                    pane.getStyleClass().add("first-column");
		                }
		                if (j == 0) {
		                    pane.getStyleClass().add("first-row");
		                }
		                grid.add(pane, i, j);
		            }
			}
			//Add gaps between cells here
		}
		 grid.add(yes,1,4);
			grid.add(no,3,4);
		 Scene scene = new Scene(grid, (columns * 40) + 100, (rows * 40) + 100, Color.WHITE);
		scene.getStylesheets().add("application.css");
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static class balls{
		public static Node getBalls(){
			Circle circle = new Circle(20,20,5);
			circle.setFill(Color.BLACK);
			return circle;			
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}

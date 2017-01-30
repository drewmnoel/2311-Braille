package simulator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CreateCells extends Application {

	/** Number of columns wide a Braille cell is */
	private static final int BRAILLE_WIDTH = 2;
	/** Number of rows tall a Braille cell is */
	private static final int BRAILLE_HEIGHT = 4;
	/** Maximum rows this screen will have */
	private static final int SCREEN_ROWS = 10;
	/** Radius of Braille dot */
	static final int BRAILLE_DOT_RADIUS = 5;
	/** Length of Braille box */
	static final int BRAILLE_BOX_SIDE = 40;

	@Override
	public void start(Stage primaryStage) throws Exception {
		int numBrailleCells = 9;
		primaryStage.setTitle("Enjoy your game.");

		SimulatorCore simCore = new SimulatorCore(numBrailleCells, 5);

		// Set example cells
		simCore.setCell(0, new int[] {1, 1, 1, 0, 1, 0, 1, 1});
		simCore.setCell(1, new int[] {0, 0, 1, 0, 0, 0, 1, 0});
		simCore.setCell(2, new int[] {0, 1, 1, 1, 0, 0, 0, 0});


		TilePane grid = new TilePane();
		// grid.getStyleClass().add("game-grid");
		

		

		// Layout each of the cells
		for (int k = 0; k < simCore.numOfCells(); k++) {
			// Create a running index
			int[] cellArray = simCore.cellAt(k);
			GridPane secondary = new GridPane();
			// Constrain each column to be BRAILLE_BOX_SIDE wide
			

			// Constrain each row to be BRAILLE_BOX_SIDE tall
			
			int runningIndex = 0;
			for (int i = 0; i < BRAILLE_WIDTH; i++) {
				for (int j = 0; j < BRAILLE_HEIGHT; j++) {
					Pane pane = new Pane();
					pane.setPrefSize(BRAILLE_BOX_SIDE, BRAILLE_BOX_SIDE);
					pane.getChildren().add(BrailleCircle.getCircle(cellArray, runningIndex));
					pane.getStyleClass().add("game-grid-cell");
				
					secondary.add(pane, i + (3 * k), j);

					runningIndex++;
				}
			}
			grid.getChildren().add(secondary);
			grid.setHgap(20);
			grid.setVgap(20);
		}

		// Add two buttons
		/*grid.add(yes, 0, BRAILLE_HEIGHT);
		grid.add(no, 3, BRAILLE_HEIGHT);
*/
		// Scene width will be approx 3 braille boxes wide per braille cell
		// (except only 2 on last cell)
		int sceneWidth = 3 * BRAILLE_BOX_SIDE * numBrailleCells - BRAILLE_BOX_SIDE;

		// Scene height will be approx the height of 1 cell plus one more box
		int sceneHeight = ((BRAILLE_HEIGHT + 1) * BRAILLE_BOX_SIDE);

		Scene scene = new Scene(grid);
		scene.getStylesheets().add("application.css");

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

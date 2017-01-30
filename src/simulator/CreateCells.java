package simulator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
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
		int numBrailleCells = 3;
		primaryStage.setTitle("Enjoy your game.");

		SimulatorCore simCore = new SimulatorCore(numBrailleCells, 5);

		// Set example cells
		simCore.setCell(0, new int[] {1, 1, 1, 0, 1, 0, 1, 1});
		simCore.setCell(1, new int[] {0, 0, 1, 0, 0, 0, 1, 0});
		simCore.setCell(2, new int[] {0, 1, 1, 1, 0, 0, 0, 0});


		GridPane grid = new GridPane();
		// grid.getStyleClass().add("game-grid");
		Button yes = new Button("YES");
		Button no = new Button("NO");

		// Constrain each column to be BRAILLE_BOX_SIDE wide
		for (int i = 0; i < numBrailleCells * 3; i++) {
			ColumnConstraints column = new ColumnConstraints(BRAILLE_BOX_SIDE);
			grid.getColumnConstraints().add(column);
		}

		// Constrain each row to be BRAILLE_BOX_SIDE tall
		for (int i = 0; i < SCREEN_ROWS; i++) {
			RowConstraints row = new RowConstraints(BRAILLE_BOX_SIDE);
			grid.getRowConstraints().add(row);
		}

		// Layout each of the cells
		for (int k = 0; k < simCore.numOfCells(); k++) {
			// Create a running index
			int[] cellArray = simCore.cellAt(k);

			int runningIndex = 0;
			for (int i = 0; i < BRAILLE_WIDTH; i++) {
				for (int j = 0; j < BRAILLE_HEIGHT; j++) {
					Pane pane = new Pane();
					pane.getChildren().add(BrailleCirlce.getCircle(cellArray, runningIndex));
					pane.getStyleClass().add("game-grid-cell");
					if (i == 0) {
						pane.getStyleClass().add("first-column");
					}
					if (j == 0) {
						pane.getStyleClass().add("first-row");
					}
					grid.add(pane, i + (3 * k), j);

					runningIndex++;
				}
			}
		}

		// Add two buttons
		grid.add(yes, 0, BRAILLE_HEIGHT);
		grid.add(no, 3, BRAILLE_HEIGHT);

		// Scene width will be approx 3 braille boxes wide per braille cell
		// (except only 2 on last cell)
		int sceneWidth = 3 * BRAILLE_BOX_SIDE * numBrailleCells - BRAILLE_BOX_SIDE;

		// Scene height will be approx the height of 1 cell plus one more box
		int sceneHeight = ((BRAILLE_HEIGHT + 1) * BRAILLE_BOX_SIDE);

		Scene scene = new Scene(grid, sceneWidth, sceneHeight, Color.WHITE);
		scene.getStylesheets().add("application.css");

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

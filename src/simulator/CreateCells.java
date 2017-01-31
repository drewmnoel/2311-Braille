package simulator;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class CreateCells extends Application {

	/** Number of columns wide a Braille cell is */
	private static final int BRAILLE_WIDTH = 2;
	/** Number of rows tall a Braille cell is */
	private static final int BRAILLE_HEIGHT = 4;
	/** Radius of Braille dot */
	static final int BRAILLE_DOT_RADIUS = 5;
	/** Length of Braille box */
	static final int BRAILLE_BOX_SIDE = 40;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scanner stdin = new Scanner(System.in);

		// Get the word to show
		System.out.print("Word to display? ");
		String brailleText = stdin.nextLine();

		// Get the number of buttons
		System.out.print("Number of buttons? ");
		int numButtons = stdin.nextInt();

		primaryStage.setTitle("Enjoy your game.");

		SimulatorCore simCore = new SimulatorCore(brailleText.length(), numButtons);

		// Set the strings
		int[][] nCells = BrailleTextTranslator.translate(brailleText);
		for (int i = 0; i < brailleText.length(); i++) {
			simCore.setCell(i, nCells[i]);
		}

		TilePane grid = new TilePane();
		// grid.getStyleClass().add("game-grid");

		// TODO: Make programatically generated buttons

		// Layout each of the cells
		for (int k = 0; k < simCore.numOfCells(); k++) {
			// Create a running index
			int[] cellArray = simCore.cellAt(k);
			GridPane secondary = new GridPane();
			
			int runningIndex = 0;
			
			for (int i = 0; i < BRAILLE_WIDTH; i++) {
				for (int j = 0; j < BRAILLE_HEIGHT; j++) {
					Pane pane = new Pane();
					pane.setPrefSize(BRAILLE_BOX_SIDE, BRAILLE_BOX_SIDE);
					BrailleCircle circle = new BrailleCircle(cellArray, runningIndex);
					pane.getChildren().add(circle.getCircle());
					pane.getStyleClass().add("game-grid-cell");
					secondary.add(pane, i + (3 * k), j);

					runningIndex++;
				}
			}
			grid.getChildren().add(secondary);
			grid.setHgap(20);
			grid.setVgap(20);
		}

		Scene scene = new Scene(grid);
		scene.getStylesheets().add("application.css");

		primaryStage.setScene(scene);
		primaryStage.show();

		stdin.close();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
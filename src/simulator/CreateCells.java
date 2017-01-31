package simulator;

import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
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

	private static final SimulatorCore simCore = SimulatorCore.getInstance();

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
		simCore.populate(brailleText.length(), numButtons);

		// Set the strings
		int[][] nCells = BrailleTextTranslator.translate(brailleText);
		for (int i = 0; i < brailleText.length(); i++) {
			simCore.setCell(i, nCells[i]);
		}

		// Add the listener
		SimulatorCore.cellObserver.addListener(new ListChangeListener<int[]>() {
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends int[]> c) {
				try {
					primaryStage.setScene(makeScene());
				} catch (SimulatorException e) {
					// Do nothing if something went horribly wrong
					return;
				}
			}

		});

		primaryStage.setScene(makeScene());
		primaryStage.show();

		stdin.close();
	}

	private Scene makeScene() throws SimulatorException {
		TilePane grid = new TilePane();
		grid = drawCells(grid);
		Scene scene = new Scene(grid);
		scene.getStylesheets().add("application.css");

		return scene;
	}

	private TilePane drawCells(TilePane grid) throws SimulatorException {
		// Layout each of the TilePane
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

		return grid;
	}

	public static void main(String[] args) {
		launch(args);
	}
}

package simulator;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * GUI main class, creates and draws all the user interface. Listens for changes
 * and triggers redraws automatically.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-01-25
 */
public class CreateCells extends Application {

	/** Number of columns wide a Braille cell is */
	private static final int BRAILLE_WIDTH = 2;
	/** Number of rows tall a Braille cell is */
	private static final int BRAILLE_HEIGHT = 4;
	/** Radius of Braille dot */
	static final int BRAILLE_DOT_RADIUS = 5;
	/** Length of Braille box */
	static final int BRAILLE_BOX_SIDE = 40;
	/** Minimum WIDTH of Button */
	private static final int BUTTON_MIN_WIDTH = 50;
	/** Space Between cells */
	private static final int HORIZONTAL_PADDING = 20;
	private static final int VERTICAL_PADDING = 20;
	private static final SimulatorCore simCore = SimulatorCore.getInstance();

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Enjoy your game.");
		simCore.populate(5, 3);

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
		// 5 is number of horizontal boxes, padding space is 4 + borderPane
		// padding of 20
		primaryStage.setMinWidth(BRAILLE_BOX_SIDE * BRAILLE_WIDTH * 5 + (HORIZONTAL_PADDING * 7));
		primaryStage.show();
	}

	private Scene makeScene() throws SimulatorException {
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(20, 20, 20, 20));
		TilePane grid = new TilePane();
		grid = drawCells(grid);
		addButtons(borderPane);
		borderPane.setCenter(grid);
		Scene scene = new Scene(borderPane);

		scene.getStylesheets().add("application.css");

		return scene;
	}

	// create and add buttons to horizontal pane
	// horizontal pane gets added to stack pane
	// StackPane finally gets added to BorderPane
	private static void addButtons(BorderPane pane) {
		Button b = null;
		StackPane bottomPane = new StackPane();
		HBox hbButtons = new HBox();
		hbButtons.setPadding(new Insets(20, 0, 0, 0));
		for (int i = 1; i <= simCore.numOfButtons(); i++) {
			b = new Button(Integer.toString(i));
			b.setMinWidth(BUTTON_MIN_WIDTH);
			b.setOnAction((event) -> {
				System.out.println("Button " + event.getTarget() + " was clicked");
			});
			hbButtons.getChildren().add(b);
		}
		hbButtons.setAlignment(Pos.CENTER);
		bottomPane.getChildren().add(hbButtons);
		pane.setBottom(bottomPane);
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
			grid.setHgap(HORIZONTAL_PADDING);
			grid.setVgap(VERTICAL_PADDING);
		}

		return grid;
	}
}

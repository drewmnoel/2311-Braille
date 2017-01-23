package simulator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SimulatorDisplay extends Application {

	@Override
	public void start(Stage stage) {

		// Create the main grid pane
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(25, 25, 25, 25));

		// Create the numbered buttons
		CreateButtons.createNumButtons(gridPane);

		// Create the scene
		Scene scene = new Scene(gridPane, 800, 300);

		// Set the stage with the scene
		stage.setTitle("Braille Simulator");
		stage.setScene(scene);
		stage.show();
	}
}

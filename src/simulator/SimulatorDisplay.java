package simulator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SimulatorDisplay extends Application {

	protected GridPane pane;

	@Override
	public void start(Stage stage) {
		// Create the main grid pane
		this.pane = new GridPane();
		this.pane.setPadding(new Insets(25, 25, 25, 25));

		// Create the numbered buttons
		generateButtons(1, new String[] { "a" });

		// Create the scene
		Scene scene = new Scene(this.pane, 800, 300);

		// Set the stage with the scene
		stage.setTitle("Braille Simulator");
		stage.setScene(scene);
		stage.show();
	}

	protected void generateButtons(int numButtons, String[] names) {
		int x = 1;
		for (int i = 0, y = 1; i < numButtons; i++, y++) {
			String buttonName = names[i];

			Button btn = new Button(buttonName);
			btn.setOnAction(new BlankEventHandler());
			this.pane.add(btn, y, x);
		}
	}
}

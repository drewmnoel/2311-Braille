package simulator;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;

public class SimulatorDisplay extends Application {

	protected GridPane pane;

	@Override
	public void start(Stage stage) {
		// Create the main grid pane
		this.pane = new GridPane();
		this.pane.setPadding(new Insets(25, 25, 25, 25));

		// Create the numbered buttons
		ArrayList<EventHandler<ActionEvent>> actions = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			actions.add(new BlankEventHandler());
		}

		generateButtons(5, new String[] { "Left", "2nd", "Middle", "4th", "Right" }, actions);

		// Create the scene
		Scene scene = new Scene(this.pane, 800, 300);

		// Set the stage with the scene
		stage.setTitle("Braille Simulator");
		stage.setScene(scene);
		stage.show();
	}

	protected void generateButtons(int numButtons, String[] names, ArrayList<EventHandler<ActionEvent>> actions) {
		int x = 1;
		for (int i = 0, y = 1; i < numButtons; i++, y++) {
			String buttonName = names[i];

			Button btn = new Button(buttonName);
			btn.setOnAction(actions.get(i));
			this.pane.add(btn, y, x);
		}
	}
}

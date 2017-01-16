package simulator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SimulatorDisplay extends Application {

	@Override
	public void start(Stage stage) {
		// Create the left button
		Button btnLeft = new Button();
		btnLeft.setText("Left Button");
		btnLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Left button clicked");
			}
		});

		// Create the right button
		Button btnRight = new Button();
		btnRight.setText("Right Button");
		btnRight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Right button clicked");
			}
		});
		
		
		//Create the push button
		Button btnPush = new Button("Push");
		btnPush.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("OMG stop pushing me");
			}
		});

		// Create the main grid pane
		GridPane gridPane = new GridPane();
		gridPane.setVgap(20);
		gridPane.setHgap(50);
		gridPane.setPadding(new Insets(25, 25, 25, 25));

		//Create new button
		Button btnNew = new Button();
		btnNew.setText("New Button");
		btnNew.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//System.out.println("New button clicked");
				String test = "⠌⠜⠬test ";
				displayBraille.putBraille(test, gridPane);
			}
		});
		
		// Populate the main pane with the buttons
		gridPane.add(btnLeft, 0, 1);
		gridPane.add(btnRight, 1, 1);
		gridPane.add(btnPush, 0, 2);
		gridPane.add(btnNew, 1, 2);
		
		//String charTest = new String("⠥⠧⠭⠽⠵⠯⠿⠷⠮⠾abc");
		//displayBraille.putBraille(charTest, gridPane);
		
		// Create the scene
		Scene scene = new Scene(gridPane, 800, 300);

		// Set the stage with the scene
		stage.setTitle("Braille Simulator");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
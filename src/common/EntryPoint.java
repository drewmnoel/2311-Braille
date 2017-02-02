package common;

import javafx.application.Application;
import simulator.CreateCells;

public class EntryPoint {
	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new MainThread());
		t.start();

		Application.launch(CreateCells.class);
	}
}

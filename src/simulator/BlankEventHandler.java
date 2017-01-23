package simulator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BlankEventHandler implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		System.out.println("Event triggered: " + event.toString());

	}

}

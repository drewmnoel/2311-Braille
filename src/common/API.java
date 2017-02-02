package common;

import javafx.application.Platform;
import simulator.SimulatorCore;
import simulator.SimulatorException;

public class API {
	public void setText(String input) {
		Platform.runLater(() -> {
			SimulatorCore simcore = SimulatorCore.getInstance();
			try {
				simcore.populate(input.length(), 0);
				int[][] resultCells = BrailleTextTranslator.translate(input);
				for (int i = 0; i < input.length(); i++) {
					simcore.setCell(i, resultCells[i]);
				}
			} catch (SimulatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
}

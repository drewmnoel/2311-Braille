package common;

import javafx.application.Platform;
import simulator.SimulatorCore;
import simulator.SimulatorException;

/**
 * API Wrapper for interacting with the system. Currently connected to the
 * Simulator, but is abstracted such that it may connected to the hardware
 * instead.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-02-02
 */
public class API {
	private String lastText = "";
	private int lastButtons = 1;
	private SimulatorCore simcore = SimulatorCore.getInstance();

	/**
	 * Set the text display of the UI
	 *
	 * @param input
	 *            Text to show on the screen
	 */
	public void setText(String input) {
		redraw(input, lastButtons);
	}

	/**
	 * Set the number of buttons on the UI
	 *
	 * @param numButtons
	 *            Number of buttons that should be shown
	 */
	public void setButtons(int numButtons) {
		redraw(lastText, numButtons);
	}

	/**
	 * Helper function to trigger a redraw
	 *
	 * @param text
	 *            Text to draw as Braille cells
	 * @param numButtons
	 *            Number of buttons to generate
	 */
	protected void redraw(String text, int numButtons) {
		Platform.runLater(() -> {
			try {
				simcore.populate(text.length(), numButtons);
				int[][] resultCells = BrailleTextTranslator.translate(text);
				for (int i = 0; i < text.length(); i++) {
					simcore.setCell(i, resultCells[i]);
				}

				this.lastText = text;
				this.lastButtons = numButtons;
			} catch (SimulatorException e) {
			}
		});
	}
}

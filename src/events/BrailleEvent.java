package events;

import simulator.Simulator;

/**
 * Representation of a Braille Event in the scenario. Events are created by
 * FileParser parsing an input file. Execution of this event will show the
 * appropriate string on the braille cells of the simulator
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-03-05
 *
 */
public class BrailleEvent extends Event {

	// set to private after testing
	/**
	 * Adds 'capital letter flags '@'' infront of ever capital letter in a
	 * string @ is alt-175
	 *
	 * @param message
	 *            string to have flags added
	 * @return modified string
	 */
	public static String addCapsCells(String message) {
		StringBuffer modified = new StringBuffer();
		char ch;
		for (int i = 0; i < message.length(); i++) {
			ch = message.charAt(i);
			if (Character.toUpperCase(ch) == ch && Character.isLetter(ch)) {
				modified.append('@');
				modified.append(Character.toLowerCase(ch));
			} else {
				modified.append(ch);
			}
		}
		return modified.toString();
	}

	// set to private after testing
	/**
	 * Create a string with sentinel character markers added for Braille numeric
	 * translation
	 *
	 * @param message
	 *            Standard message for processing
	 *
	 * @return Modified message with sentinel markers added before each numeric
	 *         group
	 */
	public static String addDigitCells(String message) {
		StringBuffer modified = new StringBuffer();
		char ch, lastCh;

		for (int i = 0; i < message.length(); i++) {
			ch = message.charAt(i);
			if (i > 0) {
				lastCh = message.charAt(i - 1);
			} else {
				// To satisfy that lastCh is init'd before the below check
				lastCh = 'X';
			}

			// Only add the flag to the beginning of each group
			if ((i == 0 || !Character.isDigit(lastCh)) && Character.isDigit(ch)) {
				modified.append('#');
				modified.append(ch);
			} else {
				modified.append(ch);
			}
		}

		return modified.toString();
	}

	// set to private after testing
	/**
	 * Checks if a string contains capital letters
	 *
	 * @param message
	 *            string to be checked
	 * @return True if there are capitals, false otherwise
	 */
	public static boolean hasCaps(String message) {
		boolean capFlag = false;
		char ch;
		for (int i = 0; i < message.length(); i++) {
			ch = message.charAt(i);
			if (Character.toUpperCase(ch) == ch && Character.isLetter(ch)) {
				capFlag = true;
			}
		}
		return capFlag;
	}

	// set to private after testing
	/**
	 * Check if the input string contains any digits (e.g. "503", "1", and
	 * "3232309").
	 *
	 * @param message
	 *            Any input string which should be checked
	 * @return True if and only if there is at least one digit
	 */
	public static boolean hasDigits(String message) {
		boolean digitFlag = false;
		char ch;
		for (int i = 0; i < message.length(); i++) {
			ch = message.charAt(i);
			if (Character.isDigit(ch)) {
				digitFlag = true;
			}
		}
		return digitFlag;
	}

	@Override
	public int execute() {
		Simulator sim = Simulator.getInstance();
		sim.displayString(getDetails());

		return 1;
	}

	@Override
	public String getDetails() {
		String representation = super.getDetails();
		if (hasCaps(representation) && representation.toUpperCase().equals(representation)) {
			representation = "@@" + representation;
		}
		// if string has some caps but is not all caps, set cap flags before
		// very capital letter
		else {
			representation = addCapsCells(representation);
		}

		// if string has numbers, add flags before the first digit of the digit
		// group
		if (hasDigits(representation)) {
			representation = addDigitCells(representation);
		}
		return representation;

	}
}
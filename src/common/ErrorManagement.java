package common;

import java.util.List;
import java.util.logging.Logger;

import events.Event;

/**
 * This class acts as a validation utility to ensure that the events as read by
 * the file are sane and contain no errors. A list of events is considered valid
 * if: there is exactly one INIT event and it is the first in the list, there
 * are between 1 and 8 buttons, there are between 1 and 10 cells. Additionally,
 * all BUTTON events must not reference more buttons than were declared in INIT
 * and nor set a braille string with more cells than were declared in the INIT.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-03-05
 */
public class ErrorManagement {

	/**
	 * Wrapper method to run checkInitFirst(), checkMultiInit(), checkButtons()
	 * and checkCells()
	 *
	 * @param eventList
	 * @throws Exception
	 */
	public static void checkEventError(List<Event> eventList) throws Exception {
		checkInitFirst(eventList);
		checkMultiInit(eventList);
		checkButtons(eventList);
		checkCells(eventList);
	}

	/**
	 * Checks if there is an INIT event is on the first line, and if so does the
	 * INIT event declares a number of buttons and cells in the accepted range
	 *
	 * @param eventList
	 *            list of events parsed from player file
	 * @throws Exception
	 */
	private static void checkInitFirst(List<Event> eventList) throws Exception {
		// Get the first event
		Event iterEvent = eventList.get(0);

		int buttons;
		int cells;

		// Check if first event is an INIT event
		if (iterEvent instanceof events.InitEvent) {
			// parse declared number of buttons and cells
			buttons = Integer.parseInt(iterEvent.getDetails().split(" ", -1)[0]);
			cells = Integer.parseInt(iterEvent.getDetails().split(" ", -1)[1]);

			// check if declared buttons is out of 1-8 range
			if (buttons < 1 || buttons > 8) {
				throw new Exception("Error: INIT must declare a number of buttons between 1-8");
			}
			// check if declared braille cells is out of 1-10 range
			if (cells < 1 || cells > 10) {
				throw new Exception("Error: INIT must declare a number of braille cells between 1-10");
			}
		}
		// Throw an exception if first event is not INIT
		else {
			throw new Exception("Error: First event is not an INIT event");
		}
	}

	/**
	 * Checks if there is more than one INIT event in the event lsit If so,
	 * throws an exception
	 *
	 * @param eventList
	 *            list of events parsed from player file
	 * @throws Exception
	 */
	private static void checkMultiInit(List<Event> eventList) throws Exception {
		Event iterEvent;
		// iterate over events to see if there are other INIT events
		for (int i = 1; i < eventList.size(); i++) {
			// If another INIT event is found
			iterEvent = eventList.get(i);
			Logger.getGlobal().severe(iterEvent.getDetails());
			if (iterEvent instanceof events.InitEvent) {
				throw new Exception("Error: Can only have one INIT event in a player file");
			}
		}
	}

	/**
	 * Checks if any BUTTON Events use a button that hasn't been initialized in
	 * the simulator If one or more is found, throws an exception with a
	 * description that a ButtonEvent was not properly defined
	 *
	 * @param eventList
	 *            List of events parsed from player file
	 * @throws Exception
	 */
	private static void checkButtons(List<Event> eventList) throws Exception {
		// parse the number of buttons in simulator from InitEvent
		int buttons = Integer.parseInt(eventList.get(0).getDetails().split(" ", -1)[0]);

		// Iterate over the event list
		for (int i = 1; i < eventList.size(); i++) {
			// If the event is a ButtonEvent
			if (eventList.get(i).getClass() == events.ButtonEvent.class) {
				// Check the number of buttons referenced in the description is
				// greater than in the simulator
				// If so, throw an exception
				if (eventList.get(i).getDetails().split(" ", -1).length > buttons) {
					throw new Exception("BUTTON Event declared with an illegal number of buttons.");
				}
			}
		}

	}

	/**
	 * Checks if any BRAILLE Event use a braille cell that hasn't been
	 * initialized in the simulator If one or more is found, throws an exception
	 * with a description that a BrailleEvent was not properly defined
	 *
	 * @param eventList
	 * @throws Exception
	 */
	private static void checkCells(List<Event> eventList) throws Exception {
		// Parse the number of cells in simulator from InitEvent
		int cells = Integer.parseInt(eventList.get(0).getDetails().split(" ", -1)[1]);

		// Iterate over the event list
		for (int i = 1; i < eventList.size(); i++) {
			// If the event is a ButtonEvent
			if (eventList.get(i).getClass() == events.BrailleEvent.class) {
				// Check the number of buttons referenced in the description is
				// greater than in the simulator
				// If so, throw an exception
				if (eventList.get(i).getDetails().length() > cells) {
					throw new Exception("BRAILLE Event declared with an illegal number of cells.");
				}
			}
		}

	}
}

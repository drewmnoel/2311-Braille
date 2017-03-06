package common;

import java.util.List;

import events.Event;
import player.FileParser;
import simulator.Simulator;

/**
 * This class acts as the client thread, which performs various actions on the
 * UI. Required since JavaFX eats the normal Main method.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-02-02
 */
public class MainThread implements Runnable {
	@Override
	public void run() {

		try {
			//File parser to parse events from input file
			FileParser fp = new FileParser();
			fp.setFileTarget("test.txt");
			//List of events parsed from input file. parseFile return is implemented as an array list
			List<Event> eventList = fp.parseFile();
			//Checking for possible errors with respect to
			ErrorManagement.errorManage(eventList);

			//Temporary event object to hold events when iterating over eventList
			Event iterEvent;

			//Get the first event in eventList to initialize the simulator
			iterEvent = eventList.get(0);
			executeInitializeSim(iterEvent);

			//Keep track of the index of next event to execute
			int index = 1;
			//Keep track of how many steps to take as set by the current event being executed
			int steps;
			//Iterate over eventList until the whole sequence of events is over
			while(index <= eventList.size() - 1 && index > 0) {
				//default is to go to the event immediately after this
				steps = 1;
				//set the next event in sequence
				iterEvent = eventList.get(index);
				//check what type of event iterEvent is, and execute it
				steps = iterEvent.execute();
				//if a jump in event order is needed, set it here
				index = index + steps;
			}
		} catch(Exception e) {
			System.err.println("Critical Error occured!");
			e.printStackTrace(System.err);
		}

	}

	/**
	 * Method to execute a initialize simulator event Will create a simulator
	 * with the specified number of buttons and cells in the event description
	 *
	 * @param iterEvent
	 *            Event whose description contains the number of buttons and
	 *            cells
	 */
	private void executeInitializeSim(Event iterEvent) {
		int buttons, cells;
		Simulator newSim;
		//parse the first number in thisEvent's details to integer buttons
		buttons = Integer.parseInt(iterEvent.getDetails().split(" ", -1)[0]);
		//parse the second number in thisEvent's details to integer cells
		cells = Integer.parseInt(iterEvent.getDetails().split(" ", -1)[1]);

		newSim = Simulator.getInstance();
		newSim.init(cells, buttons);
	}
}


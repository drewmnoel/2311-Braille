package common;

import java.util.List;

import player.AudioPlayer;
import player.Event;
import player.FileParser;
import player.TextToSpeech;
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

	//create a new audio player object
	AudioPlayer ap = new AudioPlayer();
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
			Simulator sim = executeInitializeSim(iterEvent);

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
				steps = eventTypeSelector(iterEvent, sim);
				//if a jump in event order is needed, set it here
				index = index + steps;
			}
		} catch(Exception e) {
			System.err.println("Critical Error occured!");
			e.printStackTrace(System.err);
		}

	}

	/**
	 * Detects what type the event is and either execute the
	 * appropriate execute method or sets the simulator
	 *
	 * @param thisEvent Current event being looked at
	 * @param sim
	 * @return
	 */

	private int eventTypeSelector(Event thisEvent, Simulator sim) {
		int tempSteps = 1;
		//check what type of event thisEvent is
		if (thisEvent.isTTS()) {
			//say event description as TTS
			tempSteps = executeTTS(thisEvent);
		}
		else if (thisEvent.isAudioPlay()) {
			//play audio file in event description
			tempSteps = executeAudioPlay(thisEvent);
		}
		else if (thisEvent.isSetBraille()) {
			//set braille cells to display give word
			sim.displayString(thisEvent.getEventDetails());
		}
		else if (thisEvent.isJump()) {
			//jump to an event non-sequentially
			tempSteps = executeJump(thisEvent);
		}
		else if (thisEvent.isButton()) {
			// Determine how many buttons we have to figure out
			int numButtons = thisEvent.getEventDetails().split(" ").length;

			// Create a shared object to track which button gets clicked
			SharedObject sharedObject = new SharedObject();
			sharedObject.setId(-1);

			// Create the listeners and give them access to the shared object
			for (int i = 0; i < numButtons; i++) {
				sim.getButton(i).addActionListener(new JumpButtonListener(sharedObject, i));
			}

			// Wait for a button to change the shared value.
			while (sharedObject.getId() == -1) {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {}
			}

			// Shared Object now contains the number of the button that was pressed.
			return Integer.parseInt(thisEvent.getEventDetails().split(" ")[sharedObject.getId()]);

		}

		return tempSteps;
	}

	/**
	 * Method to execute a text-to-speech event
	 * Will say the event description  with TTS and
	 * tell the player to proceed to the next event in sequence
	 *
	 * @param thisEvent Event whose description will be read by text to speech
	 * @return 1, indicates go to next event in sequence
	 */
	private int executeTTS(Event thisEvent) {
		//create a new text to speech object
		TextToSpeech tts;
		//try-catch for text-to-speech exceptions
		try {
			tts = new TextToSpeech();
			//use text to speech to say the string stored in thisEvent's details
			tts.say(thisEvent.getEventDetails());
		} catch(Exception e) {}
		//go to next event in list
		return 1;
	}

	/**
	 * Method to execute a play audio file event
	 * Will play the audio file specified in event description
	 * and tell the player to proceed to the next event in sequence
	 *
	 * @param thisEvent Event whose description contains the path to the audio file to play
	 * @return 1, indicates go to next event in sequence
	 */
	private int executeAudioPlay(Event thisEvent) {
		
		//catch possible exceptions in opening audio file
		try {
			//play audio file specified in thisEvent's details
			ap.playFile(thisEvent.getEventDetails());
		} catch (Exception e) {}
		//go to next event in list
		return 1;
	}

	/**
	 * Method to execute a initialize simulator event
	 * Will create a simulator with the specified number of buttons and
	 * cells in the event description, and return this created simulator
	 *
	 * @param thisEvent Event whose description contains the number of buttons and cells
	 * @return the newly initialized simulator
	 */
	private Simulator executeInitializeSim(Event thisEvent) {
		int buttons, cells;
		Simulator newSim;
		//parse the first number in thisEvent's details to integer buttons
		buttons = Integer.parseInt(thisEvent.getEventDetails().split(" ", -1)[0]);
		//parse the second number in thisEvent's details to integer cells
		cells = Integer.parseInt(thisEvent.getEventDetails().split(" ", -1)[1]);

		newSim = new Simulator(cells, buttons);
		return newSim;
	}

	/**
	 * Method to jump a number of events rather than going to the next in order
	 * Forward jump are 1 for normal sequence, for more is -1 from what you set
	 * Ex: 2 will jump 1 more than normal, 5 will jump 4 more than normal
	 * Backwards jumps are negative, with the number set being the number of events jumped
	 * Ex: -1 will jump backwards 1 event, -5 will jump back 5 events
	 * @param thisEvent Event whose description contains the number of events jump
	 * @return the number of events to jump in sequence. Negative values indicate going backwards
	 */
	private int executeJump(Event thisEvent) {
		int jump;
		jump = Integer.parseInt(thisEvent.getEventDetails().split(" ", -1)[0]);
		if (jump == 0)
			jump = 1;
		return jump;
	}
	
}


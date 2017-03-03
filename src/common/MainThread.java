package common;

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

	@Override
	public void run() {
		Simulator sim = Simulator.getInstance();
		TextToSpeech tts;
		FileParser fp = new FileParser();
		AudioPlayer ap = new AudioPlayer();

		// If anything goes wrong, bail out all at once.
		try {
			tts = new TextToSpeech();
			fp.setFileTarget("test.txt");

			// Parse the scenario file
			for (Event e : fp.parseFile()) {
				if (e.isTTS()) {
					tts.say(e.getEventDetails());
				} else if (e.isAudioPlay()) {
					ap.playFile(e.getEventDetails());
				} else if (e.isSetBraille()) {
					sim.displayString(e.getEventDetails());
				}
			}
		} catch (Exception e) {}
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
		//create a new audio player object
		AudioPlayer ap = new AudioPlayer();
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

}


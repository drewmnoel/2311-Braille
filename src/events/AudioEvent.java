package events;

import player.AudioPlayer;

/**
 * Representation of an Audio Event in the scenario. Events are created by
 * FileParser parsing an input file. Execution of this event will play an audio
 * file through the default audio sink.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-03-05
 *
 */
public class AudioEvent extends Event {

	/**
	 * Method to execute a play audio file event Will play the audio file
	 * specified in event description and tell the player to proceed to the next
	 * event in sequence
	 *
	 * @return 1, indicates go to next event in sequence
	 */
	@Override
	public int execute() {
		AudioPlayer ap = new AudioPlayer();

		//catch possible exceptions in opening audio file
		try {
			//play audio file specified in thisEvent's details
			ap.playFile(getDetails());
		} catch (Exception e) {}
		//go to next event in list
		return 1;
	}

}

package events;

import player.AudioPlayer;

public class AudioEvent extends Event {

	/**
	 * Method to execute a play audio file event
	 * Will play the audio file specified in event description
	 * and tell the player to proceed to the next event in sequence
	 *
	 * @param thisEvent Event whose description contains the path to the audio file to play
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

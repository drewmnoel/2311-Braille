package events;

import player.TTSException;
import player.TextToSpeech;

public class TTSEvent extends Event {

	/**
	 * Method to execute a text-to-speech event
	 * Will say the event description  with TTS and
	 * tell the player to proceed to the next event in sequence
	 *
	 * @param thisEvent Event whose description will be read by text to speech
	 * @return 1, indicates go to next event in sequence
	 */
	@Override
	public int execute() {
		TextToSpeech tts;
		try {
			tts = new TextToSpeech();
			tts.say(getDetails());
		} catch (TTSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

}
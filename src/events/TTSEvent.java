package events;

import javax.speech.EngineException;

import player.PlayerException;
import player.TextToSpeech;

/**
 * Representation of a Text-to-Speech Event in the scenario. Events are created
 * by FileParser parsing an input file. Execution of this event read the
 * relevant text aloud
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-03-05
 *
 */
public class TTSEvent extends Event {

	/**
	 * Method to execute a text-to-speech event Will say the event description
	 * with TTS and tell the player to proceed to the next event in sequence
	 *
	 * @return 1, indicates go to next event in sequence
	 */
	@Override
	public int execute() throws PlayerException {
		TextToSpeech tts;
		try {
			tts = new TextToSpeech();
			tts.say(getDetails());
		} catch (EngineException e) {
			throw new PlayerException("Failed to register speech engine", e);
		}
		return 1;
	}

}
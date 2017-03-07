package player;

import javax.speech.Central;
import javax.speech.EngineException;

import com.sun.speech.freetts.VoiceManager;

/**
 * Uses freetts library to implement a TTS bot that takes text from text file
 * and reads it aloud. This class declares bot voice, defines engine and 
 * sets up voice using VoiceManager. It accepts string text and reads text
 * through speakers.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-02-12
 */
public class TextToSpeech {
	/** String representing the voice name to use */
	protected static final String VOICE_NAME = "kevin16";
	private com.sun.speech.freetts.Voice voice;

	/**
	 * Create a new TTS bot with default voice
	 *
	 * @throws EngineException
	 *             Exception is thrown if a general engine failure occurs.
	 */
	public TextToSpeech() throws EngineException {
		// Required so that we don't require a properties file
		System.setProperty("FreeTTSSynthEngineCentral", "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");

		// Register FreeTTS as our engine of choice
		Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");

		// Create the voice from the FreeTTS library
		VoiceManager voiceManager = VoiceManager.getInstance();
		voice = voiceManager.getVoice(VOICE_NAME);

		// Finish creation of the voice
		voice.allocate();
	}

	/**
	 * Speak text aloud using default speakers and settings
	 *
	 * @param text
	 *            The text to read aloud
	 */
	public void say(String text) {
		voice.speak(text);
	}
}

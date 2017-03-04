package player;

import javax.speech.Central;
import javax.speech.EngineException;

import com.sun.speech.freetts.VoiceManager;

/**
 * Implementation of a basic TTS bot
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-02-12
 */
public class TextToSpeech {
	/** String representing the voice name to use */
	protected final String VOICE_NAME = "kevin16";
	private com.sun.speech.freetts.Voice voice;

	/**
	 * Create a new TTS bot with default voice
	 *
	 * @throws TTSException
	 *             Exception is thrown if any general engine failure occurs or
	 *             if the voice could not be loaded.
	 */
	public TextToSpeech() throws TTSException {
		// Required so that we don't require a properties file
		System.setProperty("FreeTTSSynthEngineCentral", "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");

		// Register FreeTTS as our engine of choice
		try {
			Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
		} catch (EngineException e) {
			throw new TTSException("Could not register the FreeTTS Engine", e);
		}

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
	 * @throws TTSException
	 *             Exception is thrown if the audio could not be played or the
	 *             current thread is interrupted while playing the audio
	 */
	public void say(String text) throws TTSException {
		voice.speak(text);
	}
}

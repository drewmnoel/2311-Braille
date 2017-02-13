package player;

import java.beans.PropertyVetoException;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineModeDesc;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

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
	private Synthesizer synthesizer;

	/**
	 * Create a new TTS bot with default voice
	 *
	 * @throws TTSException Exception is thrown if any general engine failure occurs or if the voice could not be loaded.
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

		// Create and allocate the synthesizer thread
		try {
			synthesizer = Central.createSynthesizer(null);
			synthesizer.allocate();
		} catch (IllegalArgumentException | EngineException e) {
			throw new TTSException("Synthesizer could not be created or allocated", e);
		}

		EngineModeDesc desc = synthesizer.getEngineModeDesc();

		// Synthesizers do not support retrieving voices by name natively.
		// To remedy this, scan through all available voices for one that has the name we want.
		Voice[] voices = ((SynthesizerModeDesc) desc).getVoices();
		Voice voice = null;
		for (int i = 0; i < voices.length; i++) {
			if (voices[i].getName().equals(VOICE_NAME)) {
				voice = voices[i];
				break;
			}
		}

		// If the voice wasn't found, we should abort
		if (voice == null) {
			throw new TTSException("Synthesizer does not have voice " + VOICE_NAME);
		}

		// Attempt to set the voice. This can be vetoed somehow.
		try {
			synthesizer.getSynthesizerProperties().setVoice(voice);
		} catch (PropertyVetoException e) {
			throw new TTSException("The voice set was vetoed", e);
		}

	}

	/**
	 * Indicate that the TTS bot is done, and that it should be destroyed. TTS commands after this will always fail!
	 *
	 * @throws TTSException Exception is thrown if the engine enters an invalid state
	 */
	public void shutdown() throws TTSException {
		try {
			synthesizer.deallocate();
		} catch (EngineException e) {
			throw new TTSException("A general engine failure occured", e);
		}
	}

	/**
	 * Speak text aloud using default speakers and settings
	 *
	 * @param text The text to read aloud
	 * @throws TTSException Exception is thrown if the audio could not be played or the current thread is interrupted while playing the audio
	 */
	public void say(String text) throws TTSException {
		try {
			synthesizer.resume();
		} catch (AudioException e) {
			throw new TTSException("An audio exception occured", e);
		}

		synthesizer.speakPlainText(text, null);

		try {
			synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
		} catch (IllegalArgumentException | InterruptedException e) {
			throw new TTSException("A thread interruption occured", e);
		}

		synthesizer.pause();
	}
}

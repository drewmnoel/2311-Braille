package player;

import javax.speech.Central;
import javax.speech.EngineModeDesc;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

/**
 * Implementation of a basic TTS bot
 *
 * @author dnoel
 *
 */
public class TextToSpeech {
	protected final String VOICE_NAME = "kevin16";
	private Synthesizer synthesizer;

	public TextToSpeech() throws Exception {
		// Required so that we don't require a properties file
		System.setProperty("FreeTTSSynthEngineCentral", "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
		Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
		Central.createSynthesizer(null);

		EngineModeDesc desc = new SynthesizerModeDesc();

		synthesizer = Central.createSynthesizer(null);
		if (synthesizer == null) {
			System.err.println("NO");
			System.exit(1);
		}
		synthesizer.allocate();
		synthesizer.resume();

		desc = synthesizer.getEngineModeDesc();

		javax.speech.synthesis.Voice[] voices = ((SynthesizerModeDesc) desc).getVoices();
		javax.speech.synthesis.Voice voice = null;
		for (int i = 0; i < voices.length; i++) {
			if (voices[i].getName().equals(VOICE_NAME)) {
				voice = voices[i];
				break;
			}
		}

		if (voice == null) {
			System.err.println("Synthesizer does not have a voice named " + VOICE_NAME + ".");

			System.exit(1);
		}
		synthesizer.getSynthesizerProperties().setVoice(voice);
	}

	public void say(String text) throws Exception {
		synthesizer.speakPlainText(text, null);
		synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
		synthesizer.deallocate();
	}

	public static void main(String[] args) throws Exception {
		TextToSpeech tts = new TextToSpeech();
		tts.say("Marbles marbles marbles marbles marbles marbles marbles");
	}
}

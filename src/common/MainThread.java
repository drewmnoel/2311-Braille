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
				}
			}
		} catch (Exception e) {}
	}

}

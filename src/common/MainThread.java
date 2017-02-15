package common;

import player.AudioPlayer;
import player.Event;
import player.FileParser;
import player.TextToSpeech;
import simulator.SimulatorCore;

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
		API api = new API();
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

			// Wait for the core information to be prepopulated to avoid null
			// pointers
			while (!SimulatorCore.ready()) {
				Thread.sleep(1);
			}

			// Core is ready, demo setting the display to several strings
			api.setText("test");
			Thread.sleep(1500);

			api.setText("hello");
			api.setButtons(4);
			Thread.sleep(1500);

			api.setText("bye");

			tts.shutdown();
		} catch (Exception e) {}
	}

}

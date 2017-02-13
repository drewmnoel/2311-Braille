package player;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Basic implementation of an Audio Player to play select filetypes
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-02-12
 */
public class AudioPlayer {
	/**
	 * Create an audio player with default settings
	 */
	public AudioPlayer() {
	}

	/**
	 * Play a given audio file.
	 *
	 * @param filename
	 *            Path to file which should be played. Accepts both absolute and
	 *            relative paths.
	 */
	public void playFile(String filename) {
		Media fileToPlay = new Media(new File(filename).toURI().toString());
		MediaPlayer audioPlayer = new MediaPlayer(fileToPlay);
		audioPlayer.play();
	}
}

package player;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 * Basic implementation of an Audio Player to play select filetypes
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-02-12
 */
public class AudioPlayer {
	private static boolean playCompleted;
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
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 */

		public void playFile(String fileName) {

		    /**
		     * this flag indicates whether the playback completes or not.
		     */


		    /**
		     * Play a given audio file.
		     * @param fileName Path of the audio file.
		     */

		        File file = new File(fileName);

		        try {
		            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
		            AudioFormat format = stream.getFormat();
		            DataLine.Info info = new DataLine.Info(Clip.class, format);
		            Clip audioClip = (Clip) AudioSystem.getLine(info);
		            audioClip.open(stream);

		            // Add a custom listener which auto-closes the clip when done
		            audioClip.addLineListener(new LineListener() {
						@Override
						public void update(LineEvent event) {
							if (event.getType() == LineEvent.Type.STOP)
			                    audioClip.close();
						}
		            });

		            audioClip.start();
		        } catch (UnsupportedAudioFileException e) {
		            e.printStackTrace();
		        } catch (LineUnavailableException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
				}

		    }

}

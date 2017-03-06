package player;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

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
	 * @param fileName
	 *            Path to the file to be played
	 *
	 * @param filename
	 *            Path to file which should be played. Accepts both absolute and
	 *            relative paths.
	 * @throws PlayerException
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 */
	public void playFile(String fileName) throws PlayerException {
		File file = new File(fileName);
		byte[] buffer = new byte[4096];
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			AudioFormat format = stream.getFormat();
			SourceDataLine audioClip = AudioSystem.getSourceDataLine(format);
			audioClip.open(format);
			audioClip.start();

			while(stream.available() > 0) {
				int len = stream.read(buffer);
				audioClip.write(buffer, 0, len);
			}

			audioClip.drain();
			audioClip.close();

		} catch (UnsupportedAudioFileException e) {
			throw new PlayerException("An unsupported audio file was given. Only WAV files are currently supported.", e);
		} catch (LineUnavailableException e) {
			throw new PlayerException("Output audio sink could not be accessed", e);
		} catch (IOException e) {
			throw new PlayerException("Reading audio file failed", e);
		}

	}

}

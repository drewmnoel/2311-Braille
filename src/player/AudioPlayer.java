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
 * Basic implementation of an Audio Player to play select filetypes. Currently
 * only WAV, AU, AIFF, and MIDI files are supported.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-02-12
 */
public class AudioPlayer {
	/**
	 * Play a given audio file. Attempts to open the file (given in either
	 * relative or absolute path) and play it through the default audio sink.
	 * Supports WAV, AU, AIFF and MIDI files. Method will begin and wait until audio file is
	 * completed before returning. Since this will tie the current thread until
	 * the file is done playing, using another thread may be desired.
	 *
	 * @param fileName
	 *            Path to the file to be played
	 * @throws PlayerException
	 *             If there is any issue with playing the file. This exception
	 *             wrappers three types of problems: wrong file format (e.g. not
	 *             WAV), inaccessible audio sink, and file I/O errors. The
	 *             exception is chained with the source of the problem.
	 */
	public void playFile(String fileName) throws PlayerException {
		// initialize file and set buffer size
		File file = new File(fileName);
		byte[] buffer = new byte[4096];

		try {
			// Opens up audio stream to fileName
			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			// Acquires audio format of sound data from audio stream
			AudioFormat format = stream.getFormat();
			// obtains data line to which sound bytes can be written to
			SourceDataLine audioClip = AudioSystem.getSourceDataLine(format);
			audioClip.open(format);
			audioClip.start();
			// Retrieves length of buffer and writes audio bytes until all bytes
			// have been read
			while (stream.available() > 0) {
				int len = stream.read(buffer);
				audioClip.write(buffer, 0, len);
			}
			// drains out bytes and closes data line
			audioClip.drain();
			audioClip.close();
			// Catches different exceptions
		} catch (UnsupportedAudioFileException e) {
			throw new PlayerException("An unsupported audio file was given. Only WAV files are currently supported", e);
		} catch (LineUnavailableException e) {
			throw new PlayerException("Output audio sink could not be accessed", e);
		} catch (IOException e) {
			throw new PlayerException("Reading audio file failed", e);
		}

	}

}

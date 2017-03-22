package authoring;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/*
 * Initializes recorder and sets fileName
 * records until Thread is interrupted
 */
public class ThreadRunnable extends Thread {
	private final AudioRecorder recorder = new AudioRecorder();
	private TargetDataLine line;

	/**
	 * Create a new thread for recording, given some file name.
	 *
	 * @param fileName The path (local/absolute) to the output file to save
	 */
	public ThreadRunnable() {
		// Define the audio format to be WAVE audio, Microsoft PCM, 16 bit,
		// stereo 44100 Hz
		AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100.0F, 16, 2, 4, 44100.0F, false);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);

		try {
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(audioFormat);
		} catch (LineUnavailableException ex) {
			ex.printStackTrace(); // TODO: Handler this error gracefully
		}

		// Inform the recorder of the line and filename
		recorder.setAudioLine(line);
		recorder.setFileName(fileName);
	}

	@Override
	public void start() {
		// Begin listening on the recording line
		line.start();

		// Now call Thread's start (which triggers ThreadRunnable's run() for
		// us)
		super.start();
	}

	@Override
	public void run() {
		// Start recording
		recorder.recordAudio();
	}

	/**
	 * Close the line, which will terminate the recorder and therefore stop the thread.
	 */
	public void stopRecording() {
		// Stop the line and then close it. The recorder will drain the line
		// itself.
		line.stop();
		line.close();
	}

}

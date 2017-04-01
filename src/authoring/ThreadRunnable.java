package authoring;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;

/*
 * Initializes recorder and sets fileName
 * records until Thread is interrupted
 */
public class ThreadRunnable extends Thread {
	private final AudioRecorder recorder = new AudioRecorder();
	private TargetDataLine line;
	private File temporaryRecordingFile;

	/**
	 * Create a new thread for recording, will save in a temporary location
	 * until completed.
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
		try {
			temporaryRecordingFile = File.createTempFile("recording", ".wav");
		} catch (IOException e) {
			// If we couldn't create a temporary file, a filesystem error
			// occured. Bail.
			e.printStackTrace();
			return;
		}
		recorder.setAudioLine(line);
		recorder.setFileName(temporaryRecordingFile.getAbsolutePath());
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
	 * Close the line, which will terminate the recorder and request the user to
	 * save the generated file someplace. Afterward, the thread will be stopped
	 * automatically.
	 */
	public void stopRecording() {
		// Stop the line and then close it. The recorder will drain the line
		// itself.
		line.stop();
		line.close();

		// Create a file chooser
		JFileChooser saveFile = new JFileChooser();

		// Create a new file filter to only allow WAV files
		FileNameExtensionFilter wavFileFilter = new FileNameExtensionFilter("wav files (*.wav)", "wav");
		saveFile.addChoosableFileFilter(wavFileFilter);
		saveFile.setFileFilter(wavFileFilter);

		// Show the save dialog and wait for the user to save the file
		saveFile.showSaveDialog(null);

		// Get the file and fix the extension if it's wrong
		File file = saveFile.getSelectedFile();
		if (!FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("wav")) {
			file = new File(file.toString() + ".wav");
		}

		// Move the file
		try {
			Files.move(this.temporaryRecordingFile.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// If the move failed, a filesystem error occured. Could be a
			// permissions error.
			e.printStackTrace();
			return;
		}
	}

}
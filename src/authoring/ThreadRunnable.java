package authoring;

/*
 * Initializes recorder and sets fileName
 * records until Thread is interrupted
 */
public class ThreadRunnable implements Runnable {
	private final AudioRecorder recorder = new AudioRecorder();
	private String fileName = "testAudio.wav";

	@Override
	public void run() {
		recorder.setFileName(fileName);
		// start recording
		recorder.recordAudio();
		// stop recording when thread is interrupted
		if (Thread.currentThread().isInterrupted()) {
			recorder.stopRecording();
		}
	}

}

package authoring;
import authoring.AudioRecorder;


public class TestAudioRecorder {
	public static void main(String[] args) {
        final AudioRecorder recorder = new AudioRecorder();
        String fileName = "testAudio.wav";
        // creates a new thread that waits for a specified
        // of time before stopping
        Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                recorder.stopRecording();
            }
        });
        System.out.println("Enter a file name: ");
        
        recorder.setFileName(fileName);
        
        stopper.start();
 
        // start recording
        recorder.recordAudio();
    }
}

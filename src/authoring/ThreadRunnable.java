package authoring;
/*
 * Initializes recorder and sets fileName
 * recods until Thread is interrupted
 */
public class ThreadRunnable implements Runnable{
	AudioRecorder recorder = new AudioRecorder();
	String fileName = "testAudio.wav";
	
	
	public void run() {
		while(!Thread.interrupted())
		{
			/*try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }*/
            
			recorder.recordAudio();
		}
		if(Thread.interrupted()){
			recorder.stopRecording();
		}
		
	}
	
}

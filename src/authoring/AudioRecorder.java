package authoring;

import javax.sound.sampled.*;
import java.io.*;

/**
 * AudioRecorder class
 * 
 * This class is intended to be used as a dependency by a client GUI class to record audio
 * from a microphone and save it as a .wav file. This class configures and stores the parameters of the
 * input lines needed for audio recording, such as sample rate,  without need for user input. Before 
 * recording, a user should set the name of the file they want the audio to be saved to using the method
 * setFileName(). Audio recording  started by calling the recordAudio() method, and stopped by calling 
 * the stopAudio() method.  
 */
public class AudioRecorder {
	//File for audio to be saved as, will be specified by user
	File wavFile;
	//Audio file format, set as .wav
	AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
	//Target line to capture audio data from
	TargetDataLine line;
	//Audio format parameters of the recording
	AudioFormat format = getAudioFormat();
	//Data line information object
	DataLine.Info info;
	 
	/**
     * Method to specify audio format parameters, such as sampling rate
     * and number of audio channels
     * 
     * Returns an AudioFormat object set to these parameters
     */
    private AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                                             channels, signed, bigEndian);
        return format;
    } 
	
    /**
     * Method to set and configure the audio line in
     */
    private void setAudioLine() {
    	try {
    		//set audio line information to that specified by AudioFormat
    		info = new DataLine.Info(TargetDataLine.class, format);
    		//checks if system supports this type of data line, if not exit
    		if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                System.exit(0);
            }
    		//sets the audio line
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
    	}catch (LineUnavailableException ex) {
    		//catch exceptions if data line does not set properly
            ex.printStackTrace();
        } 
    }
    
    /**
     * Method to start recording audio
     * Hook this up to play button in GUI
     * wavFile must be set by setFileName beforehand
     * info and line must be set by setAudioLine beforehand
     * Recording is stopped by method stopRecording
     */
    public void recordAudio() {
    	try {
    		//Sets the audio line in
    		this.setAudioLine();
    		//start capturing from line in
    		line.start();
    		//create audio input stream
    		AudioInputStream ais = new AudioInputStream(line);
    		//start recording
    		AudioSystem.write(ais, fileType, wavFile);
    		
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    /**
     * Method to stop audio recording
     * Stops and closes the line in
     * Hook this up with stop button in GUI
     */
    public void stopRecording() {
    	line.stop();
    	line.close();
    }
    
    /**
     * Method to set the name of the audio file to be recorded 
     * Hook this up to GUI file to enter file name
     * @param fileName file path and name of the file
     */
    public void setFileName(String fileName) {
    	wavFile = new File(fileName);
    }
	
    
}

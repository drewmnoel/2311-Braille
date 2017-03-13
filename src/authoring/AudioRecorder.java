package authoring;

import javax.sound.sampled.*;
import java.io.*;

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
     * Specifies audio format parameters, such as sampling rate
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
    	}catch (LineUnavailableException ex) {
    		//catch exceptions if data line does not set properly
            ex.printStackTrace();
        } 
    }
    
    /**
     * Sets the name of the audio file to be recorded 
     * @param fileName file path and name of the file
     */
    public void setFileName(String fileName) {
    	wavFile = new File(fileName);
    }
	
    
}

package authoring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AudioGUI extends JFrame implements ActionListener{
	private JButton btnStart = new JButton("Start");
	private JButton btnStop = new JButton("Stop");
	private JButton readFile =  new JButton("Read Audio File");
	AudioThread audioThread;
		
	public AudioGUI(){
		audioThread = new AudioThread();
		setTitle("AudioRecorder");
		
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(btnStart);
		getContentPane().add(btnStop);
		getContentPane().add(readFile);
		
		btnStart.addActionListener(this);
		btnStop.addActionListener(this);
	}

	
	public static void main(String[] args)
	{
		AudioGUI frame = new AudioGUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 200);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStart) {
			System.out.println("Button start");
			Thread thread = new Thread(audioThread);
			thread.start();
		} else if (e.getSource() == btnStop) {
			System.out.println("Button stop");
			audioThread.stop();
		}
		
	}
}
class AudioThread implements Runnable{
	AudioRecorder recorder = new AudioRecorder();
	String fileName = "testAudio.wav";
	boolean check = false;
	
	public void run() {
		while(check)
		{
			try{
				Thread.sleep(10);
			}catch(InterruptedException ex){
				ex.printStackTrace();
			}
			recorder.recordAudio();
		}
		
	}
	public void stop(){
		recorder.stopRecording();
		check = false;
	}
	
}

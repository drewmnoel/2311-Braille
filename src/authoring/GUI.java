package authoring;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/*
 * Creates Buttons, threads
 * if start button is pressed then start thread
 * if stop button is pressed then end thread
 */
public class GUI extends JFrame implements ActionListener {
	private JButton btnStart = new JButton("Start");
	private JButton btnStop = new JButton("Stop");
	private JButton readFile = new JButton("Read Audio File");
	ThreadRunnable audioThread;

	public GUI() {
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(btnStart);
		getContentPane().add(btnStop);
		getContentPane().add(readFile);

		btnStart.addActionListener(this);
		btnStop.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnStart) {
			audioThread = new ThreadRunnable("testAudio.wav");
			audioThread.start();
			btnStop.setEnabled(true);
			btnStart.setEnabled(false);
		} else if (e.getSource() == btnStop) {
			audioThread.stopRecording();
			btnStart.setEnabled(true);
			btnStop.setEnabled(false);
		}

	}
}
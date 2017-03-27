package common;

import javax.swing.JFrame;

import authoring.GUI;

public class MainThread implements Runnable {

	@Override
	public void run() {
		GUI frame = new GUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
}

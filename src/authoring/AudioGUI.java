package authoring;

import javax.swing.JFrame;
/**
 * Testing GUI for audio recording
 *
 */
public class AudioGUI{
	public static void main(String[] args)
	{
		GUI frame = new GUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
}



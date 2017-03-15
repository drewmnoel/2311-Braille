package authoring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Testing GUI for audio recording
 *
 */
public class AudioGUI{
	public static void main(String[] args)
	{
		GUI frame = new GUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 200);
		frame.setVisible(true);
	}
}



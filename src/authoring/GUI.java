package authoring;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Creates Buttons, threads
 * if start button is pressed then start thread
 * if stop button is pressed then end thread
 */
public class GUI extends JFrame implements ActionListener {
	private JButton btnStart = new JButton("Start Recording");
	private JButton btnStop = new JButton("Stop Recording");
	private JButton readFile = new JButton("Read Audio File");
	private JButton btnMoveUp = new JButton("Move Item Up");
	private JButton btnMoveDown = new JButton("Move Item Down");
	private JButton btnDelete = new JButton("Delete Item");
	private JButton btnNew = new JButton("New Item");
	ThreadRunnable audioThread;

	public GUI() {
		// Create the root panel
		JPanel rootContainer = new JPanel();
		rootContainer.setLayout(new BoxLayout(rootContainer, BoxLayout.X_AXIS));

		// Create the command list pane
		JPanel listPanel = new LeftPanel();
		rootContainer.add(listPanel);

		// Create the buttons pane
		JPanel buttonsPanel = new JPanel(new GridLayout(10,1));
		buttonsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Commands"));
		buttonsPanel.add(btnStart);
		buttonsPanel.add(btnStop);
		buttonsPanel.add(readFile);
		buttonsPanel.add(btnMoveUp);
		buttonsPanel.add(btnMoveDown);
		buttonsPanel.add(btnDelete);
		buttonsPanel.add(btnNew);

		rootContainer.add(buttonsPanel);

		// Hook in the button listeners for the buttons
		btnStart.addActionListener(this);
		btnStop.addActionListener(this);

		// Add the root container to the JFrame
		add(rootContainer);
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
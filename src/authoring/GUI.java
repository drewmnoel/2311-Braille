package authoring;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Creates Buttons, threads
 * if start button is pressed then start thread
 * if stop button is pressed then end thread
 */
public class GUI extends JFrame {
	ThreadRunnable audioThread;

	public GUI() {
		// Create the root panel
		JPanel rootContainer = new JPanel();
		rootContainer.setLayout(new BoxLayout(rootContainer, BoxLayout.X_AXIS));

		// Create the command list pane
		JPanel listPanel = new LeftPanel(this);
		rootContainer.add(listPanel);

		// Create the buttons pane
		JPanel buttonsPanel = new RightPanel(this);
		rootContainer.add(buttonsPanel);

		// Add the root container to the JFrame
		add(rootContainer);
	}

	public void setAudioThread(ThreadRunnable audioThread) {
		this.audioThread = audioThread;
	}

	public ThreadRunnable getAudioThread() {
		return this.audioThread;
	}
}
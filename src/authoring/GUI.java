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
	private LeftPanel<String> leftPanel;
	private RightPanel rightPanel;

	public GUI() {
		// Create the root panel
		JPanel rootContainer = new JPanel();
		rootContainer.setLayout(new BoxLayout(rootContainer, BoxLayout.X_AXIS));

		// Create the command list pane
		leftPanel = new LeftPanel(this);
		rootContainer.add(leftPanel);

		// Create the buttons pane
		rightPanel = new RightPanel(this);
		rootContainer.add(rightPanel);

		// Add the root container to the JFrame
		add(rootContainer);
	}

	public void setAudioThread(ThreadRunnable audioThread) {
		this.audioThread = audioThread;
	}

	public ThreadRunnable getAudioThread() {
		return this.audioThread;
	}

	public LeftPanel<String> getLeftPanel() {
		return this.leftPanel;
	}

	public RightPanel getRightPanel() {
		return this.rightPanel;
	}
}
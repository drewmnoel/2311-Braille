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
	private static final long serialVersionUID = -1291725446662111704L;
	private transient ThreadRunnable audioThread;
	private LeftPanel leftPanel;
	private RightPanel rightPanel;
	private SettingsPanel settingsPanel;

	public GUI() {
		// Create the root panel
		JPanel rootContainer = new JPanel();
		rootContainer.setLayout(new BoxLayout(rootContainer, BoxLayout.Y_AXIS));

		// Add the top settings panel
		settingsPanel = new SettingsPanel(this);
		rootContainer.add(settingsPanel);

		JPanel bottomContainer = new JPanel();
		bottomContainer.setLayout(new BoxLayout(bottomContainer, BoxLayout.X_AXIS));

		rootContainer.add(bottomContainer);

		// Create the command list pane
		leftPanel = new LeftPanel(this);
		bottomContainer.add(leftPanel);


		// Create the buttons pane
		rightPanel = new RightPanel(this);
		bottomContainer.add(rightPanel);

		// Add the root container to the JFrame
		add(rootContainer);

		// Recalculate the button statuses
		leftPanel.recalculateButtonStatus();
	}

	public void setAudioThread(ThreadRunnable audioThread) {
		this.audioThread = audioThread;
	}

	public ThreadRunnable getAudioThread() {
		return this.audioThread;
	}

	public LeftPanel getLeftPanel() {
		return this.leftPanel;
	}

	public RightPanel getRightPanel() {
		return this.rightPanel;
	}

	public SettingsPanel getSettingsPanel() {
		return this.settingsPanel;
	}
}
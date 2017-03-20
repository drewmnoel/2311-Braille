package authoring;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class RightPanel extends JPanel implements ActionListener {
	private JButton btnStart = new JButton("Start Recording");
	private JButton btnStop = new JButton("Stop Recording");
	private JButton readFile = new JButton("Read Audio File");
	private JButton btnMoveUp = new JButton("Move Item Up");
	private JButton btnMoveDown = new JButton("Move Item Down");
	private JButton btnDelete = new JButton("Delete Item");
	private JButton btnNew = new JButton("New Item");
	private GUI gui;

	public RightPanel(GUI gui) {
		super(new GridLayout(10,1));
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Commands"));
		add(btnStart);
		add(btnStop);
		add(readFile);
		add(btnMoveUp);
		add(btnMoveDown);
		add(btnDelete);
		add(btnNew);

		// Hook in the button listeners for the buttons
		btnStart.addActionListener(this);
		btnStop.addActionListener(this);
		btnNew.addActionListener(this);

		this.gui = gui;
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnStart) {
			gui.setAudioThread(new ThreadRunnable("testAudio.wav"));
			gui.getAudioThread().start();
			btnStop.setEnabled(true);
			btnStart.setEnabled(false);
		} else if (e.getSource() == btnStop) {
			gui.getAudioThread().stopRecording();
			btnStart.setEnabled(true);
			btnStop.setEnabled(false);
		} else if (e.getSource() == btnNew) {
			gui.getLeftPanel().addItem("New Item");
		}

	}
}

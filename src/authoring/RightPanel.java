package authoring;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RightPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 4894342125883442327L;
	private JButton btnStart = new JButton("Start Recording");
	private JButton btnStop = new JButton("Stop Recording");
	private JButton readFile = new JButton("Read Audio File");
	private JButton btnMoveUp = new JButton("Move Item Up");
	private JButton btnMoveDown = new JButton("Move Item Down");
	private JButton btnDelete = new JButton("Delete Item");
	private JButton btnNew = new JButton("New Item");
	private JButton btnExport = new JButton("Export");
	private JButton btnImport = new JButton("Import");
	private GUI gui;

	 JButton openButton, saveButton;
	    JTextArea log;
	    JFileChooser fc = new JFileChooser();;

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
		add(btnExport);
		add(btnImport);

		// Hook in the button listeners for the buttons
		btnStart.addActionListener(this);
		btnStop.addActionListener(this);
		btnMoveUp.addActionListener(this);
		btnMoveDown.addActionListener(this);
		btnNew.addActionListener(new listeners.NewButtonListener(gui));
		btnDelete.addActionListener(this);
		readFile.addActionListener(this);
		btnExport.addActionListener(new listeners.ExportListener(gui));
		btnImport.addActionListener(new listeners.ImportListener(gui));
		this.gui = gui;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStart) {
			gui.setAudioThread(new ThreadRunnable());
			gui.getAudioThread().start();
			btnStop.setEnabled(true);
			btnStart.setEnabled(false);
		} else if (e.getSource() == btnStop) {
			gui.getAudioThread().stopRecording();
			btnStart.setEnabled(true);
			btnStop.setEnabled(false);
		} else if (e.getSource() == btnMoveUp) {
			gui.getLeftPanel().moveUp();
		} else if (e.getSource() == btnMoveDown) {
			gui.getLeftPanel().moveDown();
		} else if (e.getSource() == btnDelete) {
			gui.getLeftPanel().deleteItem();
		} else if(e.getSource() == readFile) {
			this.fileChooser();
		}

		gui.getLeftPanel().recalculateButtonStatus();
	}

	private void fileChooser(){
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("wav files (*.wav)", "wav");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       System.out.println("You chose to open this file: " +
	            chooser.getSelectedFile());
	       String name = chooser.getSelectedFile().toString();
	       ReadFile read = new ReadFile();
	       read.playSound(name);
	    }
	}

	public void setUp(boolean status) {
		btnMoveUp.setEnabled(status);
	}

	public void setDown(boolean status) {
		btnMoveDown.setEnabled(status);
	}

	public void setDelete(boolean status) {
		btnDelete.setEnabled(status);
	}
}

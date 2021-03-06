package authoring;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import commands.SoundCommand;
import listeners.ExportListener;
import listeners.ImportListener;
import listeners.NewButtonListener;
import listeners.NewQuestionListener;
import listeners.NewScenarioListener;

/**
 * RightPanel is the class responsible for creating the control buttons as well
 * as implementing the simple listeners. It adds the relevant hooks to the more
 * complex listeners (located in the listeners package). It is instantiated by
 * the GUI during runtime.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-03-17
 *
 */
public class RightPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 4894342125883442327L;
	private JButton btnStart = new JButton("Start Recording");
	private JButton btnStop = new JButton("Stop Recording");
	private JButton readFile = new JButton("Read Audio File");
	private JButton btnMoveUp = new JButton("Move Item Up");
	private JButton btnMoveDown = new JButton("Move Item Down");
	private JButton btnDelete = new JButton("Delete Item");
	private JButton btnNew = new JButton("New Item (Advanced)");
	private JButton btnExport = new JButton("Export");
	private JButton btnImport = new JButton("Import");
	private JButton btnNewScenario = new JButton("New Scenario");
	private JButton btnNewQuestion = new JButton("New Question");
	private GUI gui;

	private static final String FONT_FACE = "Arial";
	private static final int FONT_SIZE = 12;

	JButton openButton, saveButton;
	JTextArea log;
	JFileChooser fc = new JFileChooser();

	/**
	 * Create a new right panel of the GUI
	 *
	 * @param gui
	 *            Reference to the overall GUI object
	 * @param mapper
	 *            Reference to the shared colour mapper object
	 */
	public RightPanel(GUI gui, ColourMapper mapper) {
		super(new GridLayout(15, 1));
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Commands"));

		// Set font sizes
		btnNewScenario.setFont(new Font(FONT_FACE, Font.PLAIN, FONT_SIZE));
		btnImport.setFont(new Font(FONT_FACE, Font.PLAIN, FONT_SIZE));
		btnExport.setFont(new Font(FONT_FACE, Font.PLAIN, FONT_SIZE));
		btnStart.setFont(new Font(FONT_FACE, Font.PLAIN, FONT_SIZE));
		btnStop.setFont(new Font(FONT_FACE, Font.PLAIN, FONT_SIZE));
		readFile.setFont(new Font(FONT_FACE, Font.PLAIN, FONT_SIZE));
		btnMoveUp.setFont(new Font(FONT_FACE, Font.PLAIN, FONT_SIZE));
		btnMoveDown.setFont(new Font(FONT_FACE, Font.PLAIN, FONT_SIZE));
		btnDelete.setFont(new Font(FONT_FACE, Font.PLAIN, FONT_SIZE));
		btnNew.setFont(new Font(FONT_FACE, Font.PLAIN, FONT_SIZE));
		btnNewQuestion.setFont(new Font(FONT_FACE, Font.PLAIN, FONT_SIZE));

		// Add the buttons
		add(btnNewScenario);
		add(btnImport);
		add(btnExport);
		add(btnStart);
		add(btnStop);
		add(readFile);
		add(btnMoveUp);
		add(btnMoveDown);
		add(btnDelete);
		add(btnNew);
		add(btnNewQuestion);

		// Hook in the button listeners for the buttons
		btnStart.addActionListener(this);
		btnStop.addActionListener(this);
		btnMoveUp.addActionListener(this);
		btnMoveDown.addActionListener(this);
		btnNew.addActionListener(new NewButtonListener(gui));
		btnDelete.addActionListener(this);
		readFile.addActionListener(this);
		btnExport.addActionListener(new ExportListener(gui));
		btnImport.addActionListener(new ImportListener(gui, mapper));
		btnNewScenario.addActionListener(new NewScenarioListener(gui));
		btnNewQuestion.addActionListener(new NewQuestionListener(gui, mapper));

		// Do not allow this component to enlarge ever
		this.setMaximumSize(this.getPreferredSize());

		// Enabling to be false so that user initializes with a New Scenario
		// first
		btnStop.setEnabled(false);
		btnNew.setEnabled(false);
		btnExport.setEnabled(false);
		readFile.setEnabled(false);
		btnStart.setEnabled(false);
		btnNewQuestion.setEnabled(false);

		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Cases for button events
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
		} else if (e.getSource() == readFile) {
			this.fileChooser();
		}

		// A call to a method that programmatically checks button status
		gui.getLeftPanel().recalculateButtonStatus();
	}

	/**
	 * A method that incorporates the ability to choose and open a file in the
	 * computer's directory
	 */
	private void fileChooser() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("wav files (*.wav)", "wav");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile());
			String name = chooser.getSelectedFile().toString();
			ReadFile read = new ReadFile();
			gui.getLeftPanel().addItem(new SoundCommand(name));
			read.playSound(name);
		}
	}

	/**
	 * A method that changes the availability of a btnMoveUp button
	 *
	 * @param status
	 *            parameter that is either true or false
	 */
	public void setUp(boolean status) {
		btnMoveUp.setEnabled(status);
	}

	/**
	 * A method that changes the availability of a btnMoveDown button
	 *
	 * @param status
	 *            parameter that is either true or false
	 */
	public void setDown(boolean status) {
		btnMoveDown.setEnabled(status);
	}

	/**
	 * A method that changes the availability of a btnDelete button
	 *
	 * @param status
	 *            parameter that is either true or false
	 */
	public void setDelete(boolean status) {
		btnDelete.setEnabled(status);
	}

	/**
	 * A method that changes the availability of a btnStart button
	 *
	 * @param status
	 *            parameter that is either true or false
	 */
	public void setStart(boolean status) {
		btnStart.setEnabled(status);
	}

	/**
	 * A method that changes the availability of a readFile button
	 *
	 * @param status
	 *            parameter that is either true or false
	 */
	public void setReadFile(boolean status) {
		readFile.setEnabled(status);
	}

	/**
	 * A method that changes the availability of a btnExport button
	 *
	 * @param status
	 *            parameter that is either true or false
	 */
	public void setExport(boolean status) {
		btnExport.setEnabled(status);
	}

	/**
	 * A method that changes the availability of a btnNew button
	 *
	 * @param status
	 *            parameter that is either true or false
	 */
	public void setNew(boolean status) {
		btnNew.setEnabled(status);
	}

	/**
	 * A method that changes the availability of a btnNewQuestion button
	 *
	 * @param status
	 *            parameter that is either true or false
	 */
	public void setNewQuestion(boolean status) {
		btnNewQuestion.setEnabled(status);
	}

}

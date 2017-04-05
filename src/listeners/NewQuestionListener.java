package listeners;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import authoring.GUI;
import commands.ResetButtonCommand;
import commands.GoHereCommand;
import commands.PauseCommand;
import commands.PlayerCommand;
import commands.SetStringCommand;
import commands.SkipButtonCommand;
import commands.SkipCommand;
import commands.TTSCommand;
import commands.UserInputCommand;

/**
 * This class is used as an action listener whenever the "New Question" button
 * is clicked. It serves as a way to create question by asking user about
 * introduction text, braille text, repeating text, correct button, text for
 * incorrect.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 4/3/2017
 *
 */
public class NewQuestionListener extends JPanel implements ActionListener {
	private static final long serialVersionUID = 7443038348707836054L;
	private GUI gui;
	private JComboBox<String> buttons;
	private JTextField introField;
	private JTextField brailleField;
	private JTextField repeatField;
	private JTextField buttonField;

	/**
	 * Create the NewQuestionListener with a reference to the current GUI
	 * object. Access to this object is required in order to access the left
	 * panel
	 *
	 * @param gui
	 *            Instance of currently running GUI
	 */
	public NewQuestionListener(GUI gui) {
		this.gui = gui;

		setLayout(new GridBagLayout());

		JLabel introLabel = new JLabel("Introduction Text:", SwingConstants.RIGHT);
		JLabel brailleLabel = new JLabel("Braille Text:", SwingConstants.RIGHT);
		JLabel repeatLabel = new JLabel("Repeating Text:", SwingConstants.RIGHT);
		JLabel correctLabel = new JLabel("Correct Button:", SwingConstants.RIGHT);
		JLabel incorrectLabel = new JLabel("Text For Incorrect:", SwingConstants.RIGHT);

		introField = new JTextField();
		brailleField = new JTextField();
		repeatField = new JTextField();
		buttonField = new JTextField();
		this.buttons = new JComboBox<String>();

		introField.setMinimumSize(new Dimension(200, 15));
		brailleField.setMinimumSize(new Dimension(200, 15));
		buttonField.setMinimumSize(new Dimension(200, 15));
		repeatField.setMinimumSize(new Dimension(200, 15));
		buttons.setMinimumSize(new Dimension(200, 15));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(introLabel, gbc);

		gbc.gridy++;
		add(brailleLabel, gbc);

		//gbc.gridy++;
		//add(repeatLabel, gbc);

		gbc.gridy++;
		add(correctLabel, gbc);

		gbc.gridy++;
		add(incorrectLabel, gbc);

		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx++;
		gbc.gridy = 0;
		add(introField, gbc);

		gbc.gridy++;
		add(brailleField, gbc);

		//gbc.gridy++;
		//add(buttonField, gbc);

		gbc.gridy++;
		add(buttons, gbc);

		gbc.gridy++;
		add(repeatField, gbc);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Generate a psuedorandom identifier
		Random r = new Random();
		String randomLabel = "" + r.nextInt(500);
		String strNumOfButtons = gui.getSettingsPanel().getButtonField();
		if (strNumOfButtons == null || strNumOfButtons.isEmpty()) {
			return;
		}

		int numOfButtons = Integer.parseInt(strNumOfButtons);
		buttons.removeAllItems();
		for (int i = 0; i < numOfButtons; i++) {
			buttons.addItem("Button " + (i + 1));
		}

		int result = JOptionPane.showConfirmDialog(null, this, "Enter Question Details", JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION) {
			// At this point we have enough information to create a basic question.
			// It is composed of TTS, Braille string, a repeat section, a repeat
			// button, some error
			PlayerCommand holder;
			gui.getLeftPanel().addItem(new ResetButtonCommand(""));
			gui.getLeftPanel().addItem(new TTSCommand(introField.getText()));
			gui.getLeftPanel().addItem(new PauseCommand("1"));

			// Set the Braille fields
			holder = new SetStringCommand(brailleField.getText());
			gui.getLeftPanel().addItem(holder);

			// Start of the repeat section
			holder = new GoHereCommand(randomLabel + "-start");
			gui.getLeftPanel().addItem(holder);

			// Loop through all the buttons defined
			for (int i = 0; i < numOfButtons; i++) {
				if (i != buttons.getSelectedIndex()) {
					// All buttons that are wrong will just repeat the question
					// (bad)
					holder = new SkipButtonCommand("" + i + " " + randomLabel + "-bad");
					gui.getLeftPanel().addItem(holder);
				} else {
					// The correct button skips to the end
					holder = new SkipButtonCommand("" + i + " " + randomLabel + "-good");
					gui.getLeftPanel().addItem(holder);
				}
			}
			// Adds UserInputCommand to wait for button presses
			gui.getLeftPanel().addItem(new UserInputCommand());

			// Labels for bad
			gui.getLeftPanel().addItem(new GoHereCommand("" + randomLabel + "-bad"));
			gui.getLeftPanel().addItem(new TTSCommand(repeatField.getText()));
			gui.getLeftPanel().addItem(new SkipCommand(randomLabel + "-start"));

			// Label for good
			gui.getLeftPanel().addItem(new GoHereCommand("" + randomLabel + "-good"));
		}
		
	}

}

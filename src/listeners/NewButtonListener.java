package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import authoring.GUI;
import commands.PauseCommand;
import commands.RepeatButtonCommand;
import commands.RepeatCommand;
import commands.SetVoiceCommand;
import commands.SkipButtonCommand;
import commands.SoundCommand;
import commands.UserInputCommand;

public class NewButtonListener implements ActionListener {

	private GUI gui;

	public NewButtonListener(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Show the Add Item dialog
		String[] possibilities = { "Pause", "Text-to-speech", "Repeat", "Button Repeat", "Button Skip", "User Input", "Sound"};
		String answer;
		answer = (String)JOptionPane.showInputDialog(gui, "Select the type of the item", "Add Item",
				JOptionPane.PLAIN_MESSAGE, null, possibilities, "");

		switch(answer) {
		case "Pause":
			gui.getLeftPanel().addItem(new PauseCommand("1"));
			break;
		case "Text-to-speech":
			gui.getLeftPanel().addItem(new SetVoiceCommand(" "));
			break;
		case "Repeat":
			gui.getLeftPanel().addItem(new RepeatCommand(""));
			break;
		case "Button Repeat":
			gui.getLeftPanel().addItem(new RepeatButtonCommand(""));
			break;
		case "Button Skip":
			gui.getLeftPanel().addItem(new SkipButtonCommand());
			break;
		case "User Input":
			gui.getLeftPanel().addItem(new UserInputCommand());
			break;
		case "Sound":
			gui.getLeftPanel().addItem(new SoundCommand());
			break;
		default: break;
		}
	}

}

package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import authoring.GUI;
import commands.CellCharCommand;
import commands.CellLowerCommand;
import commands.CellRaiseCommand;
import commands.ClearAllCommand;
import commands.ClearCellCommand;
import commands.GoHereCommand;
import commands.PauseCommand;
import commands.RepeatButtonCommand;
import commands.RepeatCommand;
import commands.ResetButtonCommand;
import commands.SetPinsCommand;
import commands.SetStringCommand;
import commands.SetVoiceCommand;
import commands.SkipButtonCommand;
import commands.SkipCommand;
import commands.SoundCommand;
import commands.TTSCommand;
import commands.UserInputCommand;

public class NewButtonListener implements ActionListener {

	private GUI gui;

	public NewButtonListener(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Show the Add Item dialog
		String[] possibilities = { "Pause", "Text-to-speech", "Display String", "Repeat", "Button Repeat", "Button Skip", "User Input",
				"Sound", "Reset Buttons", "Skip", "Clear All", "Clear Cell", "Set Pins", "Set Char", "Raise Pin",
				"Lower Pin", "Set Voice", "Jump Tag" };
		String answer;
		answer = (String) JOptionPane.showInputDialog(gui, "Select the type of the item", "Add Item",
				JOptionPane.PLAIN_MESSAGE, null, possibilities, "");

		switch (answer) {
		case "Pause":
			gui.getLeftPanel().addItem(new PauseCommand("1"));
			break;
		case "Text-to-speech":
			gui.getLeftPanel().addItem(new TTSCommand(" "));
			break;
		case "Display String":
			gui.getLeftPanel().addItem(new SetStringCommand());
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
		case "Reset Buttons":
			gui.getLeftPanel().addItem(new ResetButtonCommand());
			break;
		case "Skip":
			gui.getLeftPanel().addItem(new SkipCommand(""));
			break;
		case "Clear All":
			gui.getLeftPanel().addItem(new ClearAllCommand());
			break;
		case "Clear Cell":
			gui.getLeftPanel().addItem(new ClearCellCommand());
			break;
		case "Set Pins":
			gui.getLeftPanel().addItem(new SetPinsCommand());
			break;
		case "Set Char":
			gui.getLeftPanel().addItem(new CellCharCommand());
			break;
		case "Raise Pin":
			gui.getLeftPanel().addItem(new CellRaiseCommand());
			break;
		case "Lower Pin":
			gui.getLeftPanel().addItem(new CellLowerCommand());
			break;
		case "Set Voice":
			gui.getLeftPanel().addItem(new SetVoiceCommand(" "));
			break;
		case "Jump Tag":
			gui.getLeftPanel().addItem(new GoHereCommand(" "));
			break;
		default:
			break;
		}
	}

}

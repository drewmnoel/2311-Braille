package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import authoring.GUI;
import commands.PauseCommand;
import commands.RepeatCommand;
import commands.SetVoiceCommand;

public class NewButtonListener implements ActionListener {

	private GUI gui;

	public NewButtonListener(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Show the Add Item dialog
		String[] possibilities = { "Pause", "Text-to-speech", "Repeat"};
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
		default: break;
		}
	}

}

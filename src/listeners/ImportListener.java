package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import authoring.GUI;
import commands.CellCharCommand;
import commands.CellLowerCommand;
import commands.CellRaiseCommand;
import commands.ClearAllCommand;
import commands.ClearCellCommand;
import commands.GoHereCommand;
import commands.PauseCommand;
import commands.PlayerCommand;
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

public class ImportListener implements ActionListener {

	private GUI gui;

	public ImportListener(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		List<String> inputLines = getInput();

		boolean inCommand = false;
		int i = 0;
		StringBuilder repeatHolder = new StringBuilder("");
		for (String line : inputLines) {

			if (line.isEmpty()) {
				continue;
			}

			// Skip first three lines
			if (i < 3) {
				i++;
				continue;
			}

			// When a repeat is seen, begin tracking the contents
			if (line.compareTo("/~repeat") == 0) {
				inCommand = true;
				repeatHolder = new StringBuilder("");
				continue;
			}

			// Track the repeat contents until the end is seen
			if (inCommand && line.compareTo("/~endrepeat") != 0) {
				repeatHolder.append(line.trim() + "\n");
				continue;
			}

			// Put the entire contents of the end into the command and resume
			// normal mode
			if (line.compareTo("/~endrepeat") == 0) {
				inCommand = false;
				PlayerCommand pc = new RepeatCommand(repeatHolder.toString());
				gui.getLeftPanel().addItem(pc);
				continue;
			}

			// Check for TTS, it has no header
			if (line.length() < 2 || line.substring(0, 2).compareTo("/~") != 0) {
				gui.getLeftPanel().addItem(new TTSCommand(line));
				continue;
			}

			// If here, all valid commands will have at least length 2 (/~)
			String components[] = line.substring(2).split(":", 2);
			String command = components[0];
			String args;

			if (components.length == 2) {
				args = components[1].trim();
			} else {
				args = "";
			}

			PlayerCommand pc;

			switch (command) {
			case "pause":
				pc = new PauseCommand(args);
				break;
			case "set-voice":
				pc = new SetVoiceCommand(args);
				break;
			case "disp-string":
				pc = new SetStringCommand(args);
				break;
			case "repeat-button":
				pc = new RepeatButtonCommand(args);
				break;
			case "skip-button":
				pc = new SkipButtonCommand(args);
				break;
			case "user-input":
				pc = new UserInputCommand();
				break;
			case "sound":
				pc = new SoundCommand(args);
				break;
			case "reset-buttons":
				pc = new ResetButtonCommand(args);
				break;
			case "skip":
				pc = new SkipCommand(args);
				break;
			case "disp-clearAll":
				pc = new ClearAllCommand(args);
				break;
			case "disp-clear-cell":
				pc = new ClearCellCommand(args);
				break;
			case "disp-cell-pins":
				pc = new SetPinsCommand(args);
				break;
			case "disp-cell-char":
				pc = new CellCharCommand(args);
				break;
			case "disp-cell-raise":
				pc = new CellRaiseCommand(args);
				break;
			case "disp-cell-lower":
				pc = new CellLowerCommand(args);
				break;
			default:
				pc = new GoHereCommand(line.substring(2));
				break;
			}

			gui.getLeftPanel().addItem(pc);
		}
	}

	private List<String> getInput() {
		try {
			JFileChooser importDialog = new JFileChooser();

			FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("text files (*.txt)", "txt");
			importDialog.addChoosableFileFilter(txtFilter);
			importDialog.setFileFilter(txtFilter);

			importDialog.showOpenDialog(gui);

			URI uri = importDialog.getSelectedFile().toURI();
			return Files.readAllLines(Paths.get(uri), Charset.defaultCharset());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<String>();
		}
	}

}

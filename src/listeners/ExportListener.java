package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;

import authoring.GUI;
import commands.PlayerCommand;

public class ExportListener implements ActionListener {

	private GUI gui;

	public ExportListener(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StringBuilder sb = new StringBuilder();
		// Build the file header first
		sb.append("Cell " + gui.getSettingsPanel().getCellField() + "\n");
		sb.append("Button " + gui.getSettingsPanel().getButtonField() + "\n");
		sb.append(gui.getSettingsPanel().getTitleField() + "\n\n");

		// Get the list of commands for export
		List<PlayerCommand> list = gui.getLeftPanel().getList();
		for (PlayerCommand pc : list) {
			sb.append(pc.serialize());
			sb.append("\n");
		}

		// sb now contains the export file contents
		// TODO: Save to file rather than printing out
		JFileChooser save = new JFileChooser();

		FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("text files (*.txt)", "txt");
		save.addChoosableFileFilter(txtFilter);
		save.setFileFilter(txtFilter);

		save.showSaveDialog(null);

		// Get the file and fix the extension if it's wrong
		File file = save.getSelectedFile();
		if (!FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("txt")) {
			file = new File(file.toString() + ".txt");
		}

		try {
			FileWriter fw = new FileWriter(file);
			fw.write(sb.toString());
			fw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}

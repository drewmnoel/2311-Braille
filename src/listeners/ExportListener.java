package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
		// Get the list of commands for export
		List<PlayerCommand> list = gui.getLeftPanel().getList();
		for (PlayerCommand pc : list) {
			sb.append(pc.serialize());
			sb.append("\n");
		}

		// sb now contains the export file contents
		// TODO: Save to file rather than printing out
		System.out.print(sb.toString());
	}
}

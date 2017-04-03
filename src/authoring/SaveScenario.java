package authoring;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;

import javafx.stage.FileChooser;

public class SaveScenario extends GUI {
	
	private GUI gui;
	
	public SaveScenario(GUI gui){
		this.gui = gui;
	}
	
	public void saveFile() {
		ArrayList sb = gui.getLeftPanel().getItem();
		
		JFileChooser save = new JFileChooser();
		
		FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("text files (*.txt)", "txt");
		save.addChoosableFileFilter(txtFilter);
		save.setFileFilter(txtFilter);
	
		save.showSaveDialog(null);
		
		try(FileWriter fw = new FileWriter(save.getSelectedFile() + ".txt")) {
			fw.write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Get the file and fix the extension if it's wrong
		File file = save.getSelectedFile();
		if (!FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("txt")) {
			file = new File(file.toString() + ".txt");
		}
	}
}

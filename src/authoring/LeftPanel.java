package authoring;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class LeftPanel extends JPanel {
	JScrollPane scrollPane = new JScrollPane();

	public LeftPanel() {
		super(new GridLayout(1,1));
		JList<String> commandList = new JList<String>();
		commandList.setModel(new DefaultListModel<String>());
		DefaultListModel<String> listModel = (DefaultListModel<String>) commandList.getModel();
		listModel.addElement("Init Event");
		listModel.addElement("TTS Event");
		listModel.addElement("Button Event");
		listModel.addElement("Button Event");
		listModel.addElement("TTS Event");
		commandList.setModel(listModel);
		scrollPane.setViewportView(commandList);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Scenario"));
		add(scrollPane);
	}
}

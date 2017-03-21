package authoring;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class LeftPanel extends JPanel {
	private JScrollPane scrollPane = new JScrollPane();
	private JList<String> commandList = new JList<>();
	private DefaultListModel<String> listModel = new DefaultListModel<>();

	public LeftPanel(GUI gui) {
		super(new GridLayout(1, 1));

		commandList.setModel(listModel);
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

	private void swapElements(int a, int b) {
		String strA = listModel.getElementAt(a);
		String strB = listModel.getElementAt(b);
		listModel.set(a, strB);
		listModel.set(b, strA);
	}

	public void addItem(String string) {
		listModel.addElement(string);
	}

	public void moveUp() {
		int selectedIndex = commandList.getSelectedIndex();

		// Do not move the top element "up"!
		if (selectedIndex == 0) {
			return;
		}

		// Swap the element with the one above it
		swapElements(selectedIndex, selectedIndex - 1);

		// Update the highlight position
		commandList.setSelectedIndex(selectedIndex - 1);
	}

	public void moveDown() {
		int selectedIndex = commandList.getSelectedIndex();

		// Do not move the bottom down!
		if (selectedIndex == listModel.size() - 1) {
			return;
		}

		// Swap the element with the one below it
		swapElements(selectedIndex, selectedIndex + 1);

		// Update the highlight position
		commandList.setSelectedIndex(selectedIndex + 1);
	}

	public void deleteItem() {
		int selectedIndex = commandList.getSelectedIndex();
		listModel.remove(selectedIndex);
	}
}

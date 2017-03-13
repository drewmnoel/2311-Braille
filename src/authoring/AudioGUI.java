package authoring;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class AudioGUI {
	private final JFrame audioFrame = new JFrame();
	private final JPanel audioPanel = new JPanel();
	private FlowLayout layout= new FlowLayout();
	private int height = 100;
	private int width = 300;
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public void displayGUI(){
		audioFrame.setSize(width, height);
		audioPanel.setLayout(layout);
		audioPanel.add(this.createButtons("Start"));
		audioPanel.add(this.createButtons("ReadFile"));
		audioPanel.add(this.createButtons("Stop"));
		audioFrame.add(audioPanel);
		audioFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		audioFrame.setVisible(true);
	}
	
	private JButton createButtons(String btnName){
		JButton btn = new JButton(btnName);
		return btn;
	}
	
}

package common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JumpButtonListener implements ActionListener {
	private SharedObject shared;
	public int id;

	public JumpButtonListener(SharedObject shared, int id) {
		this.shared = shared;
		this.id = id;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		shared.setId(id);
	}

}

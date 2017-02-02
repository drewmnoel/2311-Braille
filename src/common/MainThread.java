package common;

import simulator.SimulatorCore;

public class MainThread implements Runnable {

	@Override
	public void run() {
		API api = new API();

		while (!SimulatorCore.ready()) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			api.setText("test");
			Thread.sleep(1500);
			api.setText("hello");
			Thread.sleep(1500);
			api.setText("bye");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

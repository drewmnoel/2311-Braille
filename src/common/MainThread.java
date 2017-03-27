package common;

import player.SoundPlayer;

public class MainThread implements Runnable {

	@Override
	public void run() {
		SoundPlayer s = new SoundPlayer();
	    s.setScenarioFile("Scenario_One.txt");
	}
}

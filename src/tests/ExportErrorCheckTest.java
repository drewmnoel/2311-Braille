package tests;

import static org.junit.Assert.*;
import commands.*;
import authoring.ExportErrorCheck;
import java.util.ArrayList;

import org.junit.Test;

public class ExportErrorCheckTest {

	//Tests checkUserInput
	@Test
	public void testCheckUserInput() {
		ArrayList<PlayerCommand> commands = new ArrayList<PlayerCommand>();
		//ExportErrorCheck checker = new ExportErrorCheck();
		
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new UserInputCommand());
		commands.add(new PauseCommand(""));
		assertFalse("test", ExportErrorCheck.checkUserInput(commands));
		
		commands = new ArrayList<PlayerCommand>();
		commands.add(new GoHereCommand(""));
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new UserInputCommand());
		commands.add(new PauseCommand(""));
		
	}

}

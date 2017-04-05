package tests;

import static org.junit.Assert.*;
import commands.*;
import authoring.ExportErrorCheck;
import java.util.ArrayList;

import org.junit.Test;

public class ExportErrorCheckTest {
	
	ArrayList<PlayerCommand> commands;
	//Tests checkUserInput
	@Test
	public void testCheckUserInput() {
		commands = new ArrayList<PlayerCommand>();
		//ExportErrorCheck checker = new ExportErrorCheck();
		
		//Situation where there is a UserInputCommand without a button setup before, should be false
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new UserInputCommand());
		commands.add(new PauseCommand(""));
		assertFalse("test", ExportErrorCheck.checkUserInput(commands));
		
		//Situation where there is a SkipButtonCommand before UserInput, should be true
		commands = new ArrayList<PlayerCommand>();
		commands.add(new SkipButtonCommand(""));
		commands.add(new GoHereCommand(""));
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new UserInputCommand());
		commands.add(new PauseCommand(""));
		assertTrue("test", ExportErrorCheck.checkUserInput(commands));
		
		//Situation where there is a RepeatButtonCommand before UserInput, should be true
		commands = new ArrayList<PlayerCommand>();
		commands.add(new RepeatButtonCommand(""));
		commands.add(new GoHereCommand(""));
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new UserInputCommand());
		commands.add(new PauseCommand(""));
		assertTrue("test", ExportErrorCheck.checkUserInput(commands));
		
		//Situation where there is a RepeatButtonCommand and SkipButton before UserInput, should be true
		commands = new ArrayList<PlayerCommand>();
		commands.add(new RepeatButtonCommand(""));
		commands.add(new SkipButtonCommand(""));
		commands.add(new GoHereCommand(""));
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new UserInputCommand());
		commands.add(new PauseCommand(""));
		assertTrue("test", ExportErrorCheck.checkUserInput(commands));
		
		//Situation where there is no UserInput, should be true
		commands = new ArrayList<PlayerCommand>();
		commands.add(new RepeatButtonCommand(""));
		commands.add(new GoHereCommand(""));
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new PauseCommand(""));
		assertTrue("test", ExportErrorCheck.checkUserInput(commands));
	}
	
	//Tests checkCellNumber
	@Test
	public void testCheckCellNumber() {
		commands = new ArrayList<PlayerCommand>();
		
		//Situation where SetStringCommand sets a string that's too long, should return false
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new SetStringCommand("longstring"));
		assertFalse(ExportErrorCheck.checkCellNumber(commands, 5));
		
		//Situation where SetStringCommand sets a string that's not too long, should return true
		commands = new ArrayList<PlayerCommand>();
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new SetStringCommand("longs"));
		assertTrue(ExportErrorCheck.checkCellNumber(commands, 5));

		//Situation where CellCharCommand sets a cell that doesn't exist, should return false
		commands = new ArrayList<PlayerCommand>();
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new CellCharCommand("5 a"));
		commands.add(new SetStringCommand("longs"));
		assertFalse(ExportErrorCheck.checkCellNumber(commands, 5));
		
		//Situation where CellCharCommand sets a cell that does exist, should return true
		commands = new ArrayList<PlayerCommand>();
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new CellCharCommand("4 a"));
		commands.add(new SetStringCommand("longs"));
		assertTrue(ExportErrorCheck.checkCellNumber(commands, 5));
		
		//Situation where CellLowerCommand sets a cell that doesn't exist, should return false
		commands = new ArrayList<PlayerCommand>();
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new CellLowerCommand("5 a"));
		commands.add(new SetStringCommand("longs"));
		assertFalse(ExportErrorCheck.checkCellNumber(commands, 5));

		//Situation where CellLowerCommand sets a cell that does exist, should return true
		commands = new ArrayList<PlayerCommand>();
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new CellLowerCommand("4 a"));
		commands.add(new SetStringCommand("longs"));
		assertTrue(ExportErrorCheck.checkCellNumber(commands, 5));
		
		//Situation where CellRaiseCommand sets a cell that doesn't exist, should return false
		commands = new ArrayList<PlayerCommand>();
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new CellRaiseCommand("5 a"));
		commands.add(new SetStringCommand("longs"));
		assertFalse(ExportErrorCheck.checkCellNumber(commands, 5));

		//Situation where CellRaiseCommand sets a cell that does exist, should return true
		commands = new ArrayList<PlayerCommand>();
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new CellRaiseCommand("4 a"));
		commands.add(new SetStringCommand("longs"));
		assertTrue(ExportErrorCheck.checkCellNumber(commands, 5));
		
		//Situation where ClearCellCommand sets a cell that doesn't exist, should return false
		commands = new ArrayList<PlayerCommand>();
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new ClearCellCommand("5 a"));
		commands.add(new SetStringCommand("longs"));
		assertFalse(ExportErrorCheck.checkCellNumber(commands, 5));

		//Situation where ClearCellCommand sets a cell that does exist, should return true
		commands = new ArrayList<PlayerCommand>();
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new ClearCellCommand("4 a"));
		commands.add(new SetStringCommand("longs"));
		assertTrue(ExportErrorCheck.checkCellNumber(commands, 5));
		
		//Situation where SetPinsCommand sets a cell that doesn't exist, should return false
		commands = new ArrayList<PlayerCommand>();
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new SetPinsCommand("5 a"));
		commands.add(new SetStringCommand("longs"));
		assertFalse(ExportErrorCheck.checkCellNumber(commands, 5));

		//Situation where SetPinsCommand sets a cell that does exist, should return true
		commands = new ArrayList<PlayerCommand>();
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new SetPinsCommand("4 a"));
		commands.add(new SetStringCommand("longs"));
		assertTrue(ExportErrorCheck.checkCellNumber(commands, 5));
	}
	
	//Tests method checkButtonNumber
	@Test
	public void testCheckButtonNumber() {
		
		//If repeatButton command calls a button that exists, should return true
		commands = new ArrayList<PlayerCommand>();
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new SetPinsCommand("4 a"));
		commands.add(new SetStringCommand("longs"));
		commands.add(new RepeatButtonCommand("4"));
		assertTrue(ExportErrorCheck.checkButtonNumber(commands, 5));
		
		//If repeatButton command calls a button that doesn't exists, should return false
		commands = new ArrayList<PlayerCommand>();
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new SetPinsCommand("4 a"));
		commands.add(new SetStringCommand("longs"));
		commands.add(new RepeatButtonCommand("5"));
		assertFalse(ExportErrorCheck.checkButtonNumber(commands, 5));
		
		//If SkipButton command calls a button that exists, should return true
		commands = new ArrayList<PlayerCommand>();
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new SetPinsCommand("4 a"));
		commands.add(new SetStringCommand("longs"));
		commands.add(new SkipButtonCommand("4 test"));
		assertTrue(ExportErrorCheck.checkButtonNumber(commands, 5));

		//If SkipButton command calls a button that doesn't exists, should return false
		commands = new ArrayList<PlayerCommand>();
		commands.add(new GoHereCommand(""));
		commands.add(new TTSCommand(""));
		commands.add(new SetPinsCommand("4 a"));
		commands.add(new SetStringCommand("longs"));
		commands.add(new SkipButtonCommand("5 test"));
		assertFalse(ExportErrorCheck.checkButtonNumber(commands, 5));
	}

	//Test method checkJumpTag
	@Test
	public void testCheckJumpTag() {
		//When tags and jumps match up, should return true
		commands = new ArrayList<PlayerCommand>();
		commands.add(new GoHereCommand("test1"));
		commands.add(new SkipCommand("test1"));
		commands.add(new SkipButtonCommand("1 test1"));
		assertTrue(ExportErrorCheck.checkJumpTags(commands));
		
		//When there are jumps where tags don't exist, should return false
		commands = new ArrayList<PlayerCommand>();
		commands.add(new SkipButtonCommand("1 test2"));
		commands.add(new GoHereCommand("test1"));
		commands.add(new SkipCommand("test1"));
		commands.add(new SkipButtonCommand("1 test1"));
		commands.add(new SkipCommand("test2"));
		assertFalse(ExportErrorCheck.checkJumpTags(commands));
	}
}

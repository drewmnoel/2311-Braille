package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import commands.*;

/***
 * JUnit test cases for all the various command classes
 * @author Jon
 *
 */

public class CommandTests {

	CellCharCommand cellChar;
	CellLowerCommand cellLower;
	CellRaiseCommand cellRaise;
	ClearAllCommand clearAll;
	ClearCellCommand clearCell;
	GoHereCommand goHere;
	PauseCommand pause;
	RepeatButtonCommand repeatButton;
	RepeatCommand repeat;
	ResetButtonCommand resetButton;
	SetPinsCommand setPins;
	SetStringCommand setString;
	SetVoiceCommand setVoice;
	SkipButtonCommand skipButton;
	SkipCommand skip;
	SoundCommand sound;
	TTSCommand TTS;
	UserInputCommand userInput;
	
	/**
	 * Sets up instances of all the command classes
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}
	
	//Tests all the methods in CellCharCommand 
	@Test
	public void testCellCharCommand() {
		//alphabet, valid inputs to test
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		
		//tests toString()
		for(int i = 0; i<alphabet.length(); i++) {
			cellChar = new CellCharCommand(i + " " + alphabet.substring(i, i));
			assertEquals("Cell and Char: " + i + " " + alphabet.substring(i, i), cellChar.toString());
		}
		
		//tests serialize()
		for(int i = 0; i<alphabet.length(); i++) {
			cellChar = new CellCharCommand(i + " " + alphabet.substring(i, i));
			assertEquals("/~disp-cell-char:" + i + " " + alphabet.substring(i, i), cellChar.serialize());
		}
		
		//tests getEditLabel()
		for(int i = 0; i<alphabet.length(); i++) {
			cellChar = new CellCharCommand(i + " " + alphabet.substring(i, i));
			assertEquals("Cell and character (space separated)", cellChar.getEditLabel());
		}
		
		//tests getCurrentValue()
		for(int i = 0; i<alphabet.length(); i++) {
			cellChar = new CellCharCommand(i + " " + alphabet.substring(i, i));
			assertEquals(i + " " + alphabet.substring(i, i), cellChar.getCurrentValue());
		}
		
		//tests setCurrentValue()
		cellChar = new CellCharCommand(" ");
		for(int i = 0; i<alphabet.length(); i++) {
			cellChar.setCurrentValue(i + " " + alphabet.substring(i, i));
			assertEquals(i + " " + alphabet.substring(i, i), cellChar.getCurrentValue());
		}
	}
	
	
	
}

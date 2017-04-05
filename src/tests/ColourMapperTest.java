package tests;

import static org.junit.Assert.*;
import commands.SkipButtonCommand;
import commands.SkipCommand;
import commands.GoHereCommand;
import commands.PlayerCommand;
import commands.TTSCommand;
import commands.SetStringCommand;
import commands.UserInputCommand;
import authoring.ColourMapper;
import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ColourMapperTest {
	//list with 3 different location tags
	ArrayList<PlayerCommand> commandsFull = new ArrayList<PlayerCommand>();
	//list with no location tags
	ArrayList<PlayerCommand> commandsNoSkip =  new ArrayList<PlayerCommand>();
	
	//Set up the lists of commands
	@Before
	public void setUp() {
		commandsFull.add(new SkipCommand("one"));
		commandsFull.add(new GoHereCommand("one"));
		commandsFull.add(new SkipButtonCommand("1 one"));
		commandsFull.add(new TTSCommand("two"));
		commandsFull.add(new UserInputCommand());
		commandsFull.add(new GoHereCommand("two"));
		commandsFull.add(new TTSCommand("one"));
		commandsFull.add(new SkipCommand("two"));
		commandsFull.add(new SetStringCommand("three"));
		commandsFull.add(new SkipCommand("three"));
		
		commandsNoSkip.add(new TTSCommand("one"));
		commandsNoSkip.add(new TTSCommand("two"));
		commandsNoSkip.add(new SetStringCommand("three"));
		commandsNoSkip.add(new UserInputCommand());
	}
	
	//Test that mappings are created properly and returned properly
	@Test
	public void test() {
		ColourMapper colourMap_full = new ColourMapper();
		ColourMapper colourMap_noSkip = new ColourMapper();
		
		colourMap_full.addColourMapping(commandsFull);
		colourMap_noSkip.addColourMapping(commandsNoSkip);
		
		assertTrue(colourMap_noSkip.colourMap.isEmpty());
		assertEquals(Color.blue, colourMap_full.getColour("one"));
		assertEquals(Color.cyan, colourMap_full.getColour("two"));
		assertEquals(Color.red, colourMap_full.getColour("three"));
	}

}

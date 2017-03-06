package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import player.Event;

public class EventTests {
	Event testEvent;
	
	//Setting up Event class for testing each method
	@Before
	public void setUp() throws Exception {
		testEvent = new Event();		
	}
	
	//Testing isInitializeSim() 
	//Initialized with cell and button parameter and simulator initial event
	//Testing for simulator initial event value
	@Test
	public void testIsInitializedSim(){
		testEvent.setInitializeSim("10 10");
		assertTrue(testEvent.isInitializeSim());
	}
	
	//Testing isTTS()
	//Using setTTS() method to set TTS event type and TTS eventDetails
	//Checking to see if TTS event is of type TTS
	@Test
	public void testIsTTS(){
		testEvent.setTTS("This is testing TTS");
		assertTrue(testEvent.isTTS());
	}

	//Testing isAudioPlay()
	//Using setAudio() to set Audio type and AudioPlay eventDetail
	//Checking to see if Audio event is of type Audio
	@Test
	public void testIsAudioPlay(){
		testEvent.setAudioPlay("music.wav");
		assertTrue(testEvent.isAudioPlay());
	}
	
	//Testing isBraille()
	//Using setBraille() to set Braille type and Braille eventDetail
	//Checking to see if Braille event is of type Braille
	@Test
	public void testIsSetBraille(){
		testEvent.setBraille("two");
		assertTrue(testEvent.isSetBraille());
	}
	
	//Testing isButton()
	//Using setButton() to set Button type and Button eventDetail
	//Checking to see if Button event is of type Button
	@Test
	public void testIsButton(){
		testEvent.setButton("1 1 4");
		assertTrue(testEvent.isButton());
	}
	
	//Testing isJump()
	//Using setJump() to set unconditional jump and jump type
	//Checking to see if Jump event is of type Jump	
	@Test
	public void testIsJump(){
		testEvent.setJump("-6");
		assertTrue(testEvent.isJump());
	}
	
	
	//Testing eventType() method
	//Checking if the value returned matches that of event type assigned
	@Test
	public void testEventType(){
		testEvent.setTTS("This is test");
		assertEquals(0,testEvent.eventType());
		
		testEvent.setAudioPlay("finished.wav");
		assertEquals(1,testEvent.eventType());
		
		testEvent.setBraille("two");;
		assertEquals(2,testEvent.eventType());
		
		testEvent.setButton("1 1 4");
		assertEquals(3,testEvent.eventType());
		
		testEvent.setJump("-5");
		assertEquals(4,testEvent.eventType());
		
		testEvent.setInitializeSim("3 10");
		assertEquals(5,testEvent.eventType());		
	}
	
	//Testing getEventDetails() method
	//Checking if eventDetails matches the event details assigned to particular method
	@Test
	public void testGetEventDetails(){
		testEvent.setTTS("This is test");
		assertEquals("This is test",testEvent.getEventDetails());
		
		testEvent.setAudioPlay("finished.wav");
		assertEquals("finished.wav",testEvent.getEventDetails());
		
		testEvent.setBraille("two");;
		assertEquals("two",testEvent.getEventDetails());
		
		testEvent.setButton("1 1 4");
		assertEquals("1 1 4",testEvent.getEventDetails());
		
		testEvent.setJump("-5");
		assertEquals("-5",testEvent.getEventDetails());
		
		testEvent.setInitializeSim("3 10");
		assertEquals("3 10",testEvent.getEventDetails());	
	}
	
	
	@Test
	public void testHasCaps() {
		//strings with caps, should return true
		assertTrue(testEvent.hasCaps("aAbc"));
		assertTrue(testEvent.hasCaps("AAAbc deg  F"));
		assertTrue(testEvent.hasCaps("abcdefghijklmnopqrstuvwxyZ"));
		assertTrue(testEvent.hasCaps("Abcdefghijklmnopqurstuvwxyz"));
		
		//strings with no caps, should return false
		
	}
}

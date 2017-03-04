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
	@Before
	public void setUp() throws Exception {
		testEvent = new Event();		
	}
	
	@Test
	public void testIsInitializedSim(){
		testEvent.setInitializeSim("10 10");
		assertTrue(testEvent.isInitializeSim());
	}
	
	@Test
	public void testIsTTS(){
		testEvent.setTTS("This is testing TTS");
		assertTrue(testEvent.isTTS());
	}

	@Test
	public void testIsAudioPlay(){
		testEvent.setAudioPlay("music.wav");
		assertTrue(testEvent.isAudioPlay());
	}
	
	@Test
	public void testIsSetBraille(){
		testEvent.setBraille("two");
		assertTrue(testEvent.isSetBraille());
	}
	
	@Test
	public void testIsButton(){
		testEvent.setButton("1 1 4");
		assertTrue(testEvent.isButton());
	}
	
	@Test
	public void testIsJump(){
		testEvent.setJump("-6");
		assertTrue(testEvent.isJump());
	}
	
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
}

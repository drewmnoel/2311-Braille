package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import events.AudioEvent;
import events.BrailleEvent;
import events.ButtonEvent;
import events.Event;
import events.InitEvent;
import events.JumpEvent;
import events.TTSEvent;

public class EventTests {
	Event testEvent;

	//Testing isInitializeSim()
	//Initialized with cell and button parameter and simulator initial event
	//Testing for simulator initial event value
	@Test
	public void testInitializeSim(){
		testEvent = new InitEvent();
		testEvent.setDetails("10 10");
		assertEquals(1, testEvent.execute());
	}

	//Testing isTTS()
	//Using setTTS() method to set TTS event type and TTS eventDetails
	//Checking to see if TTS event is of type TTS
	@Test
	public void testTTS(){
		testEvent = new TTSEvent();
		testEvent.setDetails("This is testing TTS");
		assertEquals(1, testEvent.execute());
	}

	//Testing isAudioPlay()
	//Using setAudio() to set Audio type and AudioPlay eventDetail
	//Checking to see if Audio event is of type Audio
	@Test
	public void testAudioPlay(){
		testEvent = new AudioEvent();
		testEvent.setDetails("finished.wav");
		assertEquals(1, testEvent.execute());
	}

	//Testing isBraille()
	//Using setBraille() to set Braille type and Braille eventDetail
	//Checking to see if Braille event is of type Braille
	@Test
	public void testBraille(){
		testEvent = new BrailleEvent();
		testEvent.setDetails("two");
		assertEquals(1, testEvent.execute());
	}

	//Testing testButton()
	//Using setButton() to set Button type and Button eventDetail
	//Checking to see if Button event is of type Button
	@Test
	public void testButton(){
		testEvent = new ButtonEvent();
		testEvent.setDetails("1 1 4");

		// Cannot actually test the execute method since it relies on a human button press
		// This test just ensures there's no exception thrown
	}

	//Testing Jump Event
	//Using setJump() to set unconditional jump and jump type
	//Checking to see if Jump event is of type Jump
	@Test
	public void testIsJump(){
		testEvent = new JumpEvent();
		testEvent.setDetails("-6");
		assertEquals(-6, testEvent.execute());
	}

	//Testing hasCaps() method
	@Test
	public void testHasCaps() {
		//strings with caps, should return true
		assertTrue(BrailleEvent.hasCaps("aAbc"));
		assertTrue(BrailleEvent.hasCaps("AAAbc deg  F"));
		assertTrue(BrailleEvent.hasCaps("abcdefghijklmnopqrstuvwxyZ"));
		assertTrue(BrailleEvent.hasCaps("Abcdefghijklmnopqurstuvwxyz"));

		//strings with no caps, should return false
		assertFalse(BrailleEvent.hasCaps("abcdefghijklmnopqurstuvwxyz"));
		assertFalse(BrailleEvent.hasCaps("   "));
		assertFalse(BrailleEvent.hasCaps("@ g w 1 3 5"));
	}

	//Testing addCapsCells() method
	@Test
	public void testAddCapsCells() {
		//strings with no capitals, should be unchanged
		assertEquals("abcd efg",BrailleEvent.addCapsCells("abcd efg"));
		assertEquals("d",BrailleEvent.addCapsCells("d"));
		assertEquals("12abc",BrailleEvent.addCapsCells("12abc"));
		assertEquals("0",BrailleEvent.addCapsCells("0"));
		assertEquals("   ",BrailleEvent.addCapsCells("   "));

		//strings with capitals, but not all capitals, should have tags
		assertEquals("@abcd",BrailleEvent.addCapsCells("Abcd"));
		assertEquals("@ab cd e@f",BrailleEvent.addCapsCells("Ab cd eF"));
		assertEquals("@a@b@c@de@f",BrailleEvent.addCapsCells("ABCDeF"));
	}

	@Test
	public void testHasDigits() {
		assertFalse(BrailleEvent.hasDigits(""));
		assertFalse(BrailleEvent.hasDigits("One"));
		assertFalse(BrailleEvent.hasDigits("f_i_v_e_r"));

		assertTrue(BrailleEvent.hasDigits("0ne"));
		assertTrue(BrailleEvent.hasDigits("51,023,321"));
		assertTrue(BrailleEvent.hasDigits("0000000"));
	}

	@Test
	public void testAddDigitCells() {
		assertEquals("", BrailleEvent.addDigitCells(""));
		assertEquals("One", BrailleEvent.addDigitCells("One"));
		assertEquals("f_i_v_e_r", BrailleEvent.addDigitCells("f_i_v_e_r"));

		assertEquals("#0ne", BrailleEvent.addDigitCells("0ne"));
		assertEquals("#51,#023,#321", BrailleEvent.addDigitCells("51,023,321"));
		assertEquals("#0000000", BrailleEvent.addDigitCells("0000000"));
	}
}

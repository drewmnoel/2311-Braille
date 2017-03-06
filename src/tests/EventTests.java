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
import player.PlayerException;

/**
 * This class tests the execute method properties of different Event subtypes
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-03-01
 */
public class EventTests {
	Event testEvent;

	/**
	 * Testing InitEvent() Using setDetails() to set cell and button parameters
	 * Checking to see if Init Event executes and returns correct number of
	 * lines to skip
	 *
	 * @throws PlayerException
	 *             Thrown if the execute method throws the exception.
	 */
	@Test
	public void testInitializeSim() throws PlayerException {
		testEvent = new InitEvent();
		testEvent.setDetails("10 10");
		assertEquals(1, testEvent.execute());
	}

	/**
	 * Testing TTSEvent() Using setDetails() method to set TTS event type and
	 * TTS eventDetails Checking to see if TTS Event executes without exception
	 * and returns correct number of lines to skip
	 *
	 * @throws PlayerException
	 *             Thrown if the execute method throws the exception.
	 */
	@Test
	public void testTTS() throws PlayerException {
		testEvent = new TTSEvent();
		testEvent.setDetails("This is testing TTS");
		assertEquals(1, testEvent.execute());
	}

	/**
	 * Testing isAudioPlay() Using setDetails() to set AudioPlay eventDetail
	 * Checking to see if Audio Event executes without exception and returns
	 * correct number of lines to skip
	 *
	 * @throws PlayerException
	 *             Thrown if the execute method throws the exception.
	 */
	@Test
	public void testAudioPlay() throws PlayerException {
		testEvent = new AudioEvent();
		testEvent.setDetails("finished.wav");
		assertEquals(1, testEvent.execute());
	}

	/**
	 * Testing isBraille() Using setBraille() to set Braille type and Braille
	 * eventDetail Checking to see if Braille Event executes without exception
	 * and returns correct number of lines to skip
	 *
	 * @throws PlayerException
	 *             Thrown if the execute method throws the exception.
	 */
	@Test
	public void testBraille() throws PlayerException {
		testEvent = new BrailleEvent();
		testEvent.setDetails("two");
		assertEquals(1, testEvent.execute());
	}

	/**
	 * Testing ButtonEvent() Using setButton() to set Button type and Button
	 * eventDetail Checking to see if Button Event can be created without
	 * exception Cannot actually test the execute method since it relies on a
	 * human button press This test just ensures there's no exception thrown
	 *
	 * @throws PlayerException
	 *             Thrown if the execute method throws the exception.
	 */
	@Test
	public void testButton() {
		testEvent = new ButtonEvent();
		testEvent.setDetails("1 1 4");
	}

	/**
	 * Testing JumpEvent() Using setJump() to set unconditional jump and jump
	 * type Checking to see if Jump Event can be executed without exception and
	 * returns correct number of lines to skip
	 *
	 * @throws PlayerException
	 *             Thrown if the execute method throws the exception.
	 */
	@Test
	public void testIsJump() throws PlayerException {
		testEvent = new JumpEvent();
		testEvent.setDetails("-6");
		assertEquals(-6, testEvent.execute());
	}

	/**
	 * Testing hasCaps() method
	 */
	@Test
	public void testHasCaps() {
		// strings with caps, should return true
		assertTrue(BrailleEvent.hasCaps("aAbc"));
		assertTrue(BrailleEvent.hasCaps("AAAbc deg  F"));
		assertTrue(BrailleEvent.hasCaps("abcdefghijklmnopqrstuvwxyZ"));
		assertTrue(BrailleEvent.hasCaps("Abcdefghijklmnopqurstuvwxyz"));

		// strings with no caps, should return false
		assertFalse(BrailleEvent.hasCaps("abcdefghijklmnopqurstuvwxyz"));
		assertFalse(BrailleEvent.hasCaps("   "));
		assertFalse(BrailleEvent.hasCaps("@ g w 1 3 5"));
	}

	/**
	 * Testing addCapsCells() method
	 */
	@Test
	public void testAddCapsCells() {
		// strings with no capitals, should be unchanged
		assertEquals("abcd efg", BrailleEvent.addCapsCells("abcd efg"));
		assertEquals("d", BrailleEvent.addCapsCells("d"));
		assertEquals("12abc", BrailleEvent.addCapsCells("12abc"));
		assertEquals("0", BrailleEvent.addCapsCells("0"));
		assertEquals("   ", BrailleEvent.addCapsCells("   "));

		// strings with capitals, but not all capitals, should have tags
		assertEquals("@abcd", BrailleEvent.addCapsCells("Abcd"));
		assertEquals("@ab cd e@f", BrailleEvent.addCapsCells("Ab cd eF"));
		assertEquals("@a@b@c@de@f", BrailleEvent.addCapsCells("ABCDeF"));
	}

	/**
	 * Testing hasDigits() method
	 */
	@Test
	public void testHasDigits() {
		assertFalse(BrailleEvent.hasDigits(""));
		assertFalse(BrailleEvent.hasDigits("One"));
		assertFalse(BrailleEvent.hasDigits("f_i_v_e_r"));

		assertTrue(BrailleEvent.hasDigits("0ne"));
		assertTrue(BrailleEvent.hasDigits("51,023,321"));
		assertTrue(BrailleEvent.hasDigits("0000000"));
	}

	/**
	 * Testing addDigitCells() method
	 */
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

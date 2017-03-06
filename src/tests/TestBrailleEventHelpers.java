package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import events.BrailleEvent;

/**
 * This class tests the static helper methods from BrailleEvent. There are
 * several helper methods for manipulating text such that it can be properly
 * displayed by the methods defined in BrailleCell.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-03-06
 */
public class TestBrailleEventHelpers {
	/**
	 * Testing hasCaps() method. Method should detect if there is at least one
	 * capital letter in the input string.
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
	 * Testing addCapsCells() method. Method should add a marker tag ("@") in
	 * front of capital letters.
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
	 * Testing hasDigits() method. Method should detect if there is at least one
	 * digit in the input string.
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
	 * Testing addDigitCells() method. The method should add a marker tag ("#")
	 * in front of number groups.
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
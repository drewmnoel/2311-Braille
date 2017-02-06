package common;

/**
 * Utility class to convert between ASCII text and Braille cells
 *
 */
public class BrailleTextTranslator {

	/**
	 * Convert string input to array of Braille cells
	 * @param input Text to convert into Braille cells
	 * @return Braille result stored in an array of cells
	 */
	public static int[][] translate(String input) {
		int[][] result = new int[input.length()][];
		for (int i = 0; i < input.length(); i++) {
			String current = input.substring(i, i+1).toLowerCase();

			result[i] = BrailleTextSymbol.ht.get(current);
		}
		return result;
	}
}
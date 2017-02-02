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
		int i = 0;
		for (char c : input.toCharArray()) {
			String strC = new String(c + "");
			if (strC.equals(strC.toUpperCase())) {
				// TODO: What if there's capitals?
			}
			result[i++] = BrailleTextSymbol.ht.get(strC);
		}
		return result;
	}
}
package simulator;

public class BrailleTextTranslator {
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
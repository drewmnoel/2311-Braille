package simulator;

public class BrailleTextTranslator {
	public static String translate(String input) {
		StringBuilder result = new StringBuilder();
		for (char c: input.toCharArray()) {
			String strC = new String(c + "");
			if (strC.equals(strC.toUpperCase())) {
				System.out.print(BrailleTextSymbol.ht.get("capital"));
				strC = strC.toLowerCase();
			}
			System.out.print(BrailleTextSymbol.ht.get(strC));
		}
		return result.toString();
	}
}

package common;

import java.util.Hashtable;

public class BrailleTextSymbol {
	public static final Hashtable<String, int[]> ht = new Hashtable<>();

	static {
		ht.put("a", new int[]{1, 0, 0, 0, 0, 0, 0, 0});
		ht.put("b", new int[]{1, 1, 0, 0, 0, 0, 0, 0});
		ht.put("c", new int[]{1, 0, 0, 0, 1, 0, 0, 0});
		ht.put("d", new int[]{1, 0, 0, 0, 1, 1, 0, 0});
		ht.put("e", new int[]{1, 0, 0, 0, 0, 1, 0, 0});
		ht.put("f", new int[]{1, 1, 0, 0, 1, 0, 0, 0});
		ht.put("g", new int[]{1, 1, 0, 0, 1, 1, 0, 0});
		ht.put("h", new int[]{1, 1, 0, 0, 0, 1, 0, 0});
		ht.put("i", new int[]{0, 1, 0, 0, 1, 0, 0, 0});
		ht.put("j", new int[]{0, 1, 0, 0, 1, 1, 0, 0});
		ht.put("k", new int[]{1, 0, 1, 0, 0, 0, 0, 0});
		ht.put("l", new int[]{1, 1, 1, 0, 0, 0, 0, 0});
		ht.put("m", new int[]{1, 0, 1, 0, 1, 0, 0, 0});
		ht.put("n", new int[]{1, 0, 1, 0, 1, 1, 0, 0});
		ht.put("o", new int[]{1, 0, 1, 0, 0, 1, 0, 0});
		ht.put("p", new int[]{1, 1, 1, 0, 1, 0, 0, 0});
		ht.put("q", new int[]{1, 1, 1, 0, 1, 1, 0, 0});
		ht.put("r", new int[]{1, 1, 1, 0, 0, 1, 0, 0});
		ht.put("s", new int[]{0, 1, 1, 0, 1, 0, 0, 0});
		ht.put("t", new int[]{0, 1, 1, 0, 1, 1, 0, 0});
		ht.put("u", new int[]{1, 0, 1, 0, 0, 0, 1, 0});
		ht.put("v", new int[]{1, 1, 1, 0, 0, 0, 1, 0});
		ht.put("x", new int[]{1, 0, 1, 0, 1, 0, 1, 0});
		ht.put("y", new int[]{1, 0, 1, 0, 1, 1, 1, 0});
		ht.put("z", new int[]{1, 0, 1, 0, 0, 1, 1, 0});
		ht.put("w", new int[]{0, 1, 1, 0, 1, 1, 1, 0});
		//TODO: Numbers?
		ht.put("0", new int[]{0, 0, 0, 0, 0, 0, 0, 0});
		ht.put("1", new int[]{0, 0, 0, 0, 0, 0, 0, 0});
		ht.put("2", new int[]{0, 0, 0, 0, 0, 0, 0, 0});
		ht.put("3", new int[]{0, 0, 0, 0, 0, 0, 0, 0});
		ht.put("4", new int[]{0, 0, 0, 0, 0, 0, 0, 0});
		ht.put("5", new int[]{0, 0, 0, 0, 0, 0, 0, 0});
		ht.put("6", new int[]{0, 0, 0, 0, 0, 0, 0, 0});
		ht.put("7", new int[]{0, 0, 0, 0, 0, 0, 0, 0});
		ht.put("8", new int[]{0, 0, 0, 0, 0, 0, 0, 0});
		ht.put("9", new int[]{0, 0, 0, 0, 0, 0, 0, 0});
		ht.put("capital", new int[]{0, 0, 0, 0, 0, 0, 0, 1});
	}
}

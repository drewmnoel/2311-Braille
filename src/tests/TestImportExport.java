package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import commands.CellCharCommand;
import commands.PlayerCommand;
import commands.RepeatCommand;
import listeners.ExportListener;

public class TestImportExport {

	@Test
	public void testExportSimple() {
		List<PlayerCommand> list = new ArrayList<>();

		list.add(new CellCharCommand("a"));
		list.add(new RepeatCommand("Test repeat"));

		ExportListener el = new ExportListener(null);

		String result = el.parseCommands(list);
		String expectedResult = "/~disp-cell-char:a\n/~repeat\nTest repeat\n/~endrepeat";

		// Trim newlines from the end, ensure they're equal still
		assertEquals(result.trim(), expectedResult.trim());
	}
}

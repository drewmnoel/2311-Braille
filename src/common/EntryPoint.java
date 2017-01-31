package common;

import simulator.Simulator;
import simulator.SimulatorCore;

public class EntryPoint {
	public static void main(String[] args) throws Exception {
		// Demo of Simulator Core with 5 cells, 2 buttons
		SimulatorCore sc = SimulatorCore.getInstance();
		sc.populate(5, 2);
		sc.setCell(0, new int[] { 1, 1, 0, 0, 0, 0 }); // c
		sc.setCell(1, new int[] { 1, 0, 0, 1, 0, 0 }); // e
		sc.setCell(2, new int[] { 1, 0, 1, 0, 1, 0 }); // l
		sc.setCell(3, new int[] { 1, 0, 1, 0, 1, 0 }); // l
		sc.setCell(4, new int[] { 0, 1, 1, 0, 1, 0 }); // s

		// Print the cells on the terminal
		for (int j = 0; j < 6; j = j + 2) {
			for (int i = 0; i < 5; i++) {
				int[] cell = sc.cellAt(i);

				if (cell[j] == 1) {
					System.out.print(".");
				} else {
					System.out.print(" ");
				}

				if (cell[j + 1] == 1) {
					System.out.print(".");
				} else {
					System.out.print(" ");
				}

				System.out.print(" ");
			}

			System.out.println();
		}

		System.out.println();

		// Demo of GUI
		new Simulator();
	}
}

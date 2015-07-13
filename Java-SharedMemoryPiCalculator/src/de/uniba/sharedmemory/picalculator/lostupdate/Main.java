package de.uniba.sharedmemory.picalculator.lostupdate;


public class Main {

	public static void main(String[] args) {

		int numberOfElements = 20000;
		long tStart = System.currentTimeMillis();
		PiCalculator calculator = new PiCalculator(numberOfElements);
		calculator.process();
		long tEnd = System.currentTimeMillis();
		System.out.println("Result: " + calculator.getPi());
		System.out.println("Duration: " + (tEnd - tStart) + "ms");
	}
}

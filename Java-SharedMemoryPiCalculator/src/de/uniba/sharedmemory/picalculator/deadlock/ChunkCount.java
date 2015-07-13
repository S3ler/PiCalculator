package de.uniba.sharedmemory.picalculator.deadlock;

public class ChunkCount {
	private volatile int count = 0;

	public void increment() {
		count++;
	}

	public double getCount() {
		return count;
	}
}

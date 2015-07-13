package de.uniba.sharedmemory.picalculator.lostupdate;

import java.util.LinkedList;

public class PiCalculator {

	
	private Result result;

	private LinkedList<PiChunkThread> chunkThreads;
	private int nrOfChunks;

	public PiCalculator(int nrOfChunks) {
		this.nrOfChunks = nrOfChunks;
		this.result = new Result();
		this.chunkThreads = new LinkedList<PiChunkThread>();
	}

	public void process() {
		for (int i = 0; i < nrOfChunks; i++) {
			PiChunkThread thread = new PiChunkThread(i, result);
			chunkThreads.add(thread);
		}
		for (PiChunkThread thread : chunkThreads) {
			thread.start();
		}

		for (PiChunkThread thread : chunkThreads) {
			while (thread.isAlive()) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	public double getPi() {
		return result.getPi();
	}

}

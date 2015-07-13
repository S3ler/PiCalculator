package de.uniba.sharedmemory.picalculator.lostupdate;

public class PiChunkThread extends Thread {

	private int chunkNr;

	private Result result;

	public PiChunkThread(int chunkNr, Result result) {
		this.chunkNr = chunkNr;
		this.result = result;
	}

	private void writeResult(double chunk) {
		result.addToResult(chunk);
	}

	@Override
	public void run() {
		double chunk = 4.0d * (1.0d - ((double) chunkNr % 2.0d) * 2.0d)
				/ (2.0d * (double) chunkNr + 1.0d);
		writeResult(chunk);
	}

}

package de.uniba.sharedmemory.picalculator.withoutlostupdate;


public class PiChunkThread extends Thread {

	private int chunkNr;
	private volatile Result result;
	private volatile Object resultLock;

	public PiChunkThread(int chunkNr, Result result, Object resultLock) {
		this.chunkNr = chunkNr;
		this.result = result;
		this.resultLock = resultLock;
	}

	private void writeResult(double element) {
		synchronized (resultLock) {
			result.addToResult(element);
		}
	}

	@Override
	public void run() {
		double element = 4.0d * (1.0d - ((double) chunkNr % 2.0d) * 2.0d)
				/ (2.0d * (double) chunkNr + 1.0d);
		writeResult(element);
	}

}

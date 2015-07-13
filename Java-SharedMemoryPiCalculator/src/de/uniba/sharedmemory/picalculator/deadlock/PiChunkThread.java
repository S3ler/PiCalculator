package de.uniba.sharedmemory.picalculator.deadlock;

public class PiChunkThread extends Thread {

	private int chunkNr;
	private volatile Result result;
	private volatile Object resultLock;
	private volatile ChunkCount finChunks;
	private volatile Object finChunksLock;

	public PiChunkThread(int chunkNr, Result result, Object resultLock,
			ChunkCount finChunks, Object finChunksLock) {
		this.chunkNr = chunkNr;
		this.result = result;
		this.resultLock = resultLock;
		this.finChunks = finChunks;
		this.finChunksLock = finChunksLock;
	}

	private void writeResult(double element) {
		if (element < 0) {
			synchronized (resultLock) {
				result.addToResult(element);
				synchronized (finChunksLock) {
					finChunks.increment();
				}
			}
		} else if (element >= 0) {
			synchronized (finChunksLock) {
				finChunks.increment();
				synchronized (resultLock) {
					result.addToResult(element);
				}
			}
		}
	}

	@Override
	public void run() {
		double element = 4.0d * (1.0d - ((double) chunkNr % 2.0d) * 2.0d)
				/ (2.0d * (double) chunkNr + 1.0d);
		writeResult(element);
	}

}

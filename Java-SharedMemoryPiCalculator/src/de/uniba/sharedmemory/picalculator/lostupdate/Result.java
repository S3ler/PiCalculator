package de.uniba.sharedmemory.picalculator.lostupdate;

public class Result {
	
	private volatile double pi = 0.0d;
	
	public void addToResult(Double element) {
		this.pi += element;
	}

	public double getPi(){
		return pi;
	}
}

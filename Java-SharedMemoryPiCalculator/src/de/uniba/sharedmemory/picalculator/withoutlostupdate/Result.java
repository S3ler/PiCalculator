package de.uniba.sharedmemory.picalculator.withoutlostupdate;

public class Result {
	
	private volatile double pi = 0.0d;
	
	public void addToResult(Double element) {
		this.pi += element;
	}

	public double getPi(){
		return pi;
	}
}

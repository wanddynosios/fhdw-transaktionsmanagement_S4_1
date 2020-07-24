package model;

public class IO_Request {

	private long ressourceStart;
	private long ressourceLength;
	private boolean hasError;

	/**
	 * 
	 * @param ressourceStart Start resource block ID (in 4kB blocks)
	 * @param ressourceLength Length of consecutive blocks (4KB) in one operation
	 */
	public IO_Request(long ressourceStart, long ressourceLength) {
		this.ressourceStart = ressourceStart;
		this.ressourceLength = ressourceLength;
		this.hasError = false;
	}
	
	public long getRessourceStart() {
		return ressourceStart;
	}

	public long getRessourceLength() {
		return ressourceLength;
	}
	
	public boolean hasError() {
		return hasError;
	}
}
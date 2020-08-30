package version2.simulations.io;


import org.apache.commons.math3.distribution.UniformRealDistribution;
import version2.def.IO_Callback;
import version2.def.IO_Request;
import vorlesung.version2.scheduler.DESScheduler;

public class IORandomCallbackCounter implements IO_Callback {

	private long requestSize;
	private int RequestCounter;
	private long maxAddress;
	private UniformRealDistribution randomPlace;

	public IORandomCallbackCounter(long requestSize, long maxAddress) {
		this.requestSize = requestSize;
		this.maxAddress = maxAddress;
		this.randomPlace = new UniformRealDistribution(DESScheduler.getRandom(), 0, this.maxAddress);
		RequestCounter = 0;
	}

	public long getRequestSize() {
		return requestSize;
	}

	@Override
	public void operationComplete(IO_Request request) {
		this.RequestCounter--;
	}

	public IO_Request generateRequest() {
		this.RequestCounter++;
		return new IO_Request((long) this.randomPlace.sample(), this.requestSize);
	}
	
	public int getRequestCounter() {
		return RequestCounter;
	}

}

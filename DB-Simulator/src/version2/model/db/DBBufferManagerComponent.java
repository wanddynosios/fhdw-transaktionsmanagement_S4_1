package version2.model.db;


import version2.model.io.AbstractLocalController;
import version2.simulations.io.IORandomCallbackCounter;

public class DBBufferManagerComponent {
	
	private AbstractLocalController[] controller;

	public DBBufferManagerComponent(AbstractLocalController... controller) {
		this.controller = controller;
	}
	
	public void read(long dataID, long requestSize) {
		BufferRequest request = new BufferRequest();
		int targetID = (int) (dataID % controller.length);
		this.controller[targetID].read(request.generateRequest(dataID, (long) requestSize), request);
	}
	
	public void write(long dataID, long requestSize) {
		BufferRequest request = new BufferRequest();
		int targetID = (int) (dataID % controller.length);
		this.controller[targetID].write(request.generateRequest(dataID, (long) requestSize), request);
	}

	public void readLog(long dataID, int requestSize) {
		int targetID = (int) (dataID % controller.length);
		long maxAddress = this.controller[targetID].blockCount();
		IORandomCallbackCounter request = new IORandomCallbackCounter(2 * requestSize, maxAddress);
		this.controller[targetID].read(request.generateRequest(), request);
	}

	public void writeLog(long dataID, long requestSize) {
		int targetID = (int) (dataID % controller.length);
		long maxAddress = this.controller[targetID].blockCount();
		IORandomCallbackCounter request = new IORandomCallbackCounter(4 * requestSize, maxAddress);
		this.controller[targetID].write(request.generateRequest(), request);
	}

	
}

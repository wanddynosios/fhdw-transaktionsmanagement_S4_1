package version2.model.db;


import version2.def.IO_Callback;
import version2.def.IO_Request;

public class BufferRequest implements IO_Callback {

	private int RequestCounter;

	public BufferRequest() {
		RequestCounter = 0;
	}

	@Override
	public void operationComplete(IO_Request request) {
		this.RequestCounter--;
	}

	public IO_Request generateRequest(long dataID, long size) {
		this.RequestCounter++;
		return new IO_Request(dataID, size);
	}
	
	public int getRequestCounter() {
		return RequestCounter;
	}

}

package version2.model.io;


import version2.def.IO_Callback;
import version2.def.IO_Request;

public class IOCallbackConnector implements IO_Callback {
	
	private long callCount;
	private IO_Callback callback;
	private IO_Request request;

	public IOCallbackConnector(IO_Callback callback, IO_Request request) {
		this.callCount = 0;
		this.callback = callback;
		this.request = request;
	}
	
	public IOCallbackConnector() {
		this(null, null);
	}

	@Override
	public void operationComplete(IO_Request reqest) {
		this.callCount--;
		if(this.callCount <= 0) {
			if (this.callback != null) {
				this.callback.operationComplete(this.request);
			}
		}
	}
	
	public void addCall() {
		this.callCount++;
	}

	public void setCallCount(long callCount) {
		this.callCount = callCount;
	}
	
}

package version2.model.io.disks;


import org.apache.commons.math3.distribution.AbstractRealDistribution;
import version2.def.IO_Callback;
import version2.def.IO_Request;
import version2.model.io.AbstractIODevice;
import vorlesung.version2.modelling.ModelProcess;
import vorlesung.version2.modelling.ProcessStep;
import vorlesung.version2.modelling.ProcessStepDelay;

public class IOOperation {
	
	private long staticTime;
	private AbstractRealDistribution dynamicTime;
	private IO_Callback callback;
	private IO_Request request;
	private AbstractIODevice device;

	public IOOperation(long staticTime, AbstractRealDistribution dynamicTime, IO_Callback callback, IO_Request request, AbstractIODevice device) {
		this.staticTime = staticTime;
		this.dynamicTime = dynamicTime;
		this.callback = callback;
		this.request = request;
		this.device = device;
	}

	public void exec() {
		ModelProcess.scheduleProcess(this);
	}

	@ProcessStepDelay(0)
	public long startDelay() {
		return ((long) this.dynamicTime.sample() * this.request.getRessourceLength()) + staticTime;
	}
	
	@ProcessStep(0)
	public void scheduleNext() {
		this.callback.operationComplete(this.request);
		this.device.checkQueued();
	}
	
}

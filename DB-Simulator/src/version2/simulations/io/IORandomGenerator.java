package version2.simulations.io;


import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import version2.model.io.AbstractLocalController;
import vorlesung.version2.modelling.ModelProcess;
import vorlesung.version2.modelling.ProcessStep;
import vorlesung.version2.modelling.ProcessStepDelay;

public class IORandomGenerator {
	
	private AbstractRealDistribution waitDistribution;
	private int ioCalls;
	private double readRatio;
	private double callSplitter;
	private AbstractLocalController target;
	private IORandomCallbackCounter callback;

	public IORandomGenerator(AbstractLocalController controller, int ioCalls, double readRatio, long meanWait, int requestSize) {
		this.target = controller;
		this.waitDistribution = new ExponentialDistribution(meanWait);
		this.ioCalls = ioCalls;
		this.readRatio = readRatio;
		this.callSplitter = 0;
		this.callback = new IORandomCallbackCounter(requestSize, controller.blockCount());
		ModelProcess.scheduleProcess(this);
	}
	
	@ProcessStepDelay(0)
	public long startDelay() {
		return (long) waitDistribution.sample();
	}
	
	@ProcessStep(0)
	public void scheduleNext() {
		this.callSplitter += this.readRatio;
		
		if(callSplitter > 1) {
			target.read(this.callback.generateRequest(), this.callback);	
			this.callSplitter -= 1.0;
		}else {
			target.write(this.callback.generateRequest(), this.callback);	
		}
		
		this.ioCalls--;
		if(ioCalls > 0) {
			ModelProcess.scheduleProcess(this);
		}
		
	}
	
	
}

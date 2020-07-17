package vorlesung.version2.spielwiese;

import vorlesung.version2.evaluation.EvaluationInterval;
import vorlesung.version2.evaluation.aggregation.CountCharacteristic;
import vorlesung.version2.evaluation.aggregation.MeanCharacteristic;
import vorlesung.version2.evaluation.aggregation.StandardDeviationCharacteristic;
import vorlesung.version2.modelling.ModelProcess;
import vorlesung.version2.modelling.ProcessStep;
import vorlesung.version2.modelling.ProcessStepDelay;
import vorlesung.version2.scheduler.DESScheduler;
import org.apache.commons.math3.distribution.ExponentialDistribution;

public class NamedTestProcess {
	
	
	private String name;
	private EvaluationInterval triggerStats;
	private ExponentialDistribution time1To2;

	public NamedTestProcess(String name) {
		this.name = name;
		this.triggerStats = new EvaluationInterval("Process runtime", this, new MeanCharacteristic(), new StandardDeviationCharacteristic(), new CountCharacteristic());
		this.time1To2 = new ExponentialDistribution(DESScheduler.getRandom(), 100);
	}
	
	@ProcessStepDelay(0)
	public long startdelay() {
		return 0;
	}
	
	@ProcessStep(0)
	public void start() {
		DESScheduler.log("  Step 1 / " + DESScheduler.getSimulationTime());
		this.triggerStats.intervalStart();
	}
	
	@ProcessStepDelay(1)
	public long startTo1delay() {
		return (long) this.time1To2.sample();
	}
	
	@ProcessStep(1)
	public void step1() {
		DESScheduler.log("  Step 2 / " + DESScheduler.getSimulationTime());
	}
	
	@ProcessStepDelay(2)
	public long step1To2delay() {
		return 20;
	}
	
	@ProcessStep(2)
	public void step2() {
		DESScheduler.log("  Step 3 / " + DESScheduler.getSimulationTime());
		if(DESScheduler.getSimulationTime() < 1000000) {
			ModelProcess.scheduleProcess(this);
		}
		this.triggerStats.intervalStop();
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}

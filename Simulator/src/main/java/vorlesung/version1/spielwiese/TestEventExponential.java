package vorlesung.version1.spielwiese;

import vorlesung.version1.scheduler.DESOperation;
import vorlesung.version1.scheduler.DESScheduler;
import org.apache.commons.math3.distribution.ExponentialDistribution;

public class TestEventExponential implements DESOperation {
	
	private ExponentialDistribution timeGenerator;

	public TestEventExponential() {
		this.timeGenerator = new ExponentialDistribution(DESScheduler.getRandom(), 10);
	}

	public void process() {
		if(DESScheduler.getSimulationTime() < 100) {
			DESScheduler.log("Event executed: t = " + DESScheduler.getSimulationTime());
			DESScheduler.scheduleToFuture(this, timeGenerator.sample());
		}
	}

}

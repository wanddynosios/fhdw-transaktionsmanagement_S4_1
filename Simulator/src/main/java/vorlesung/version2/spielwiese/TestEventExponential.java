package vorlesung.version2.spielwiese;

import vorlesung.version2.scheduler.DESOperation;
import vorlesung.version2.scheduler.DESScheduler;
import org.apache.commons.math3.distribution.ExponentialDistribution;


public class TestEventExponential implements DESOperation {
	
	private ExponentialDistribution timeGenerator;

	public TestEventExponential() {
		this.timeGenerator = new ExponentialDistribution(DESScheduler.getRandom(), 10);
	}

	@Override
	public void process() {
		if(DESScheduler.getSimulationTime() < 100) {
			DESScheduler.log("Event executed: t = " + DESScheduler.getSimulationTime());
			DESScheduler.scheduleToFuture(this, timeGenerator.sample());
		}
	}

}

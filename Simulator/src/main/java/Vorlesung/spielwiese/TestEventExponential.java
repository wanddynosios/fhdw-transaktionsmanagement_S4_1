package Vorlesung.spielwiese;

import org.apache.commons.math3.distribution.ExponentialDistribution;

import de.fhdw.tm.des.scheduler.DESOperation;
import de.fhdw.tm.des.scheduler.DESScheduler;

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

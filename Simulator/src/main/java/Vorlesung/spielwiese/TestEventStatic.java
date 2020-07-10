package Vorlesung.spielwiese;


import Vorlesung.scheduler.DESOperation;
import Vorlesung.scheduler.DESScheduler;

public class TestEventStatic implements DESOperation {

	public void process() {
		if(DESScheduler.getSimulationTime() < 100) {
			DESScheduler.log("Event executed: t = " + DESScheduler.getSimulationTime());
			DESScheduler.scheduleToFuture(this, 10);
		}
	}

}

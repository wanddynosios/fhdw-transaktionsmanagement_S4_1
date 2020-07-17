package vorlesung.version1.spielwiese;


import vorlesung.version1.scheduler.DESOperation;
import vorlesung.version1.scheduler.DESScheduler;

public class TestEventStatic implements DESOperation {

	public void process() {
		if(DESScheduler.getSimulationTime() < 100) {
			DESScheduler.log("Event executed: t = " + DESScheduler.getSimulationTime());
			DESScheduler.scheduleToFuture(this, 10);
		}
	}

}

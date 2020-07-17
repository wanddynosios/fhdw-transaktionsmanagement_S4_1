package vorlesung.version2.spielwiese;


import vorlesung.version2.scheduler.DESOperation;
import vorlesung.version2.scheduler.DESScheduler;

public class TestEventStatic implements DESOperation {

	@Override
	public void process() {
		if(DESScheduler.getSimulationTime() < 100) {
			DESScheduler.log("Event executed: t = " + DESScheduler.getSimulationTime());
			DESScheduler.scheduleToFuture(this, 10);
		}
	}

}

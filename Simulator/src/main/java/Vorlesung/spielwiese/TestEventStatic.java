package Vorlesung.spielwiese;

import de.fhdw.tm.des.scheduler.DESOperation;
import de.fhdw.tm.des.scheduler.DESScheduler;

public class TestEventStatic implements DESOperation {

	@Override
	public void process() {
		if(DESScheduler.getSimulationTime() < 100) {
			DESScheduler.log("Event executed: t = " + DESScheduler.getSimulationTime());
			DESScheduler.scheduleToFuture(this, 10);
		}
	}

}

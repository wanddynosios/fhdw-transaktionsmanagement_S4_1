package Vorlesung.spielwiese;

import de.fhdw.tm.des.modelling.ModelProcess;
import de.fhdw.tm.des.modelling.ProcessStep;
import de.fhdw.tm.des.modelling.ProcessStepDelay;
import de.fhdw.tm.des.scheduler.DESScheduler;

public class TestProcess1 {
	
	@ProcessStepDelay(0)
	public long startdelay() {
		return 0;
	}
	
	@ProcessStep(0)
	public void start() {
		DESScheduler.log("  Step 1 / " + DESScheduler.getSimulationTime());
	}
	
	@ProcessStepDelay(1)
	public long startTo1delay() {
		return 10;
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
//			ModelProcess.scheduleProcess(new TestProcess1());
		}
	}
}

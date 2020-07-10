package Vorlesung.spielwiese;

import de.fhdw.tm.des.modelling.ModelProcess;
import de.fhdw.tm.des.scheduler.DESScheduler;
import de.fhdw.tm.des.scheduler.Simulation;
import de.fhdw.tm.des.scheduler.Simulator;

public class SingleSimDemo3 {

	public static void main(String[] args) {
		
		DESScheduler.setDebug(true);
		
		Simulator simulator = new Simulator(0);
		
		Simulation sim = new Simulation() {

			@Override
			public void injectStart() {
				ModelProcess.scheduleProcess(new TestProcess1());
				ModelProcess.scheduleProcesstoFuture(new TestProcess1(), 1);
			}

			@Override
			public void start() {
				System.out.println("Start...");
			}
			
			@Override
			public void finish() {
				System.out.println("Fertig");
			}
			
		};
		
		for (int i = 0; i < 1; i++) {			
			simulator.simulate(sim);
		}
		simulator.terminate();
	}

}

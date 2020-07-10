package Vorlesung.spielwiese;

import Vorlesung.scheduler.DESScheduler;
import Vorlesung.scheduler.Simulation;
import Vorlesung.scheduler.Simulator;


public class SingleSimDemo2 {

	public static void main(String[] args) {
		
		DESScheduler.setDebug(true);
		
		Simulator simulator = new Simulator(0);
		
		Simulation sim = new Simulation() {

			public void injectStart() {
				DESScheduler.schedule(new TestEventExponential(), 0);
				DESScheduler.schedule(new TestEventExponential(), 2);
			}

			public void start() {
				System.out.print("Start...");
			}
			
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

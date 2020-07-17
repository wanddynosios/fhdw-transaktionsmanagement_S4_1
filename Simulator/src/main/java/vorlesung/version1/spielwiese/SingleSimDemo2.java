package vorlesung.version1.spielwiese;

import vorlesung.version1.scheduler.DESScheduler;
import vorlesung.version1.scheduler.Simulation;
import vorlesung.version1.scheduler.Simulator;


public class SingleSimDemo2 {

	public static void main(String[] args) {
		
		DESScheduler.setDebug(true);
		
		Simulator simulator = new Simulator(0);
		
		Simulation sim = new Simulation() {

			public void injectStart() {
				DESScheduler.schedule(new TestEventExponential(), 0l);
				DESScheduler.schedule(new TestEventExponential(), 2l);
			}

			public void start() {
				System.out.println("Start...");
			}
			
			public void finish() {
				System.out.println("Fertig");
			}

		};
		
		for (int i = 0; i < 2; i++) {
			simulator.simulate(sim);
		}
		simulator.terminate();

	}

}

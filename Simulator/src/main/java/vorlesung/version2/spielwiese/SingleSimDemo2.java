package vorlesung.version2.spielwiese;


import vorlesung.version2.scheduler.DESScheduler;
import vorlesung.version2.scheduler.Simulation;
import vorlesung.version2.scheduler.Simulator;

public class SingleSimDemo2 {

	public static void main(String[] args) {
		
		DESScheduler.setDebug(true);
		
		Simulator simulator = new Simulator(0);
		
		Simulation sim = new Simulation() {

			@Override
			public void injectStart() {
				DESScheduler.schedule(new TestEventExponential(), 0);
				DESScheduler.schedule(new TestEventExponential(), 2);
			}

			@Override
			public void start() {
				System.out.print("Start...");
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

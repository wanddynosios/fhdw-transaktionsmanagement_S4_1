package vorlesung.version2.spielwiese;


import vorlesung.version2.modelling.ModelProcess;
import vorlesung.version2.scheduler.DESScheduler;
import vorlesung.version2.scheduler.Simulation;
import vorlesung.version2.scheduler.Simulator;

public class SingleSimDemo3 {

	public static void main(String[] args) {
		
		DESScheduler.setDebug(false);
		
		Simulator simulator = new Simulator(0);
		
		Simulation sim = new Simulation() {

			@Override
			public void injectStart() {
				ModelProcess.scheduleProcess(new TestProcess1());
				ModelProcess.scheduleProcessToFuture(new TestProcess1(), 1);
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
		
		simulator.simulate(sim, 100);
		simulator.terminate();
	}

}

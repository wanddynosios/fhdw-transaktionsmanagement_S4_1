package vorlesung.version2.spielwiese;

import vorlesung.version2.modelling.ModelProcess;
import vorlesung.version2.scheduler.DESScheduler;
import vorlesung.version2.scheduler.Simulation;
import vorlesung.version2.scheduler.SimulationResult;
import vorlesung.version2.scheduler.Simulator;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SingleSimDemo4 {

	private static final int SIMULATION_COUNT = 100;
	private static final int PROCESS_COUNT = 100;

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		DESScheduler.setDebug(false);
		
		Simulator simulator = new Simulator(0);
		
		Simulation sim = new Simulation() {

			@Override
			public void injectStart() {
				for (int i = 0; i < PROCESS_COUNT; i++) {
					ModelProcess.scheduleProcess(new NamedTestProcess("Process " + i));
				}
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
				
		simulator.simulate(sim, SIMULATION_COUNT);
		simulator.terminate();
		
		List<SimulationResult> results = simulator.readResults();
		int simID = 0;
		for (SimulationResult r : results) {
			System.out.println("Simulation: " + simID++);
			r.printResults();
	}
		
	}

}

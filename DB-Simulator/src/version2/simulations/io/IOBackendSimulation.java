package version2.simulations.io;

import version2.def.IO_Device;
import version2.model.io.AbstractIODevice;
import version2.model.io.AbstractLocalController;
import version2.model.io.IntelligentController;
import version2.model.io.disks.SSD;
import vorlesung.version2.scheduler.DESScheduler;
import vorlesung.version2.scheduler.Simulation;
import vorlesung.version2.scheduler.SimulationResult;
import vorlesung.version2.scheduler.Simulator;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class IOBackendSimulation implements Simulation {
	
	private static IO_Device[] generateDiskArray(AbstractIODevice disk, int countDisks) {
		IO_Device[] result = new IO_Device[countDisks];
		for (int i = 0; i < result.length; i++) {
			result[i] = disk.clonePrototype();
		}
		return result;
	}
	

	@Override
	public void injectStart() {
		int TB = (int) (1 * Math.pow(1024, 3) / 4096);

		IO_Device[] SSDArray = IOBackendSimulation.generateDiskArray(new SSD(1 * TB), 48);
		AbstractLocalController controller = new IntelligentController(4);
		controller.attachDevice(SSDArray);
		
		int ioCalls = 1000000;
		double readRatio = 0.7;
		
		for (int i = 0; i < 10; i++) {
			new IORandomGenerator(controller, ioCalls, readRatio, 100, 16);
		}

		DESScheduler.setFixedTermination(1000000000);
		
	}

	@Override
	public void start() {
		
	}

	@Override
	public void finish() {
		
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		DESScheduler.setDebug(true);
		
		Simulator simulator = new Simulator(0);
		
		for (int i = 1; i <= 1; i++) {			
			simulator.simulate(new IOBackendSimulation());
		}
		simulator.terminate();
		
		List<SimulationResult> results = simulator.readResults();
//		int simID = 0;
		for (SimulationResult r : results) {
//			System.out.println("Simulation: " + simID++ + " rate=" + r.getStaticPropierties().getProperty("ArrivalBaseRate"));
			r.printResults();
		}
	}
}

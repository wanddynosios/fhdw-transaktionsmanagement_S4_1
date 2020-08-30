package version2.simulations.sut;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;

import version2.def.DB_Transaction_Collector;
import version2.model.DBModelFactory;
import version2.model.io.disks.SSD;
import vorlesung.version2.scheduler.DESScheduler;
import vorlesung.version2.scheduler.Simulation;
import vorlesung.version2.scheduler.SimulationResult;
import vorlesung.version2.scheduler.Simulator;

public class DBFrontendSimulation implements Simulation {

	@Override
	public void injectStart() {
		int TB = (int) (1 * Math.pow(1024, 3) / 4096);
		DBModelFactory factory = new DBModelFactory();
		factory.preconstructDiskArray(new SSD(1 * TB), 48);
		DB_Transaction_Collector db = factory.construct();

		// User
		int userCount = 10000;
		int calls = 100;
		AbstractRealDistribution waitDistribution = new ExponentialDistribution(DESScheduler.getRandom(), 1000000);
		AbstractRealDistribution complexityDistribution = new NormalDistribution(DESScheduler.getRandom(), 1000, 100);

		for (int i = 0; i < userCount; i++) {
			new AbstractDBInquiryProcess(db, calls, waitDistribution, complexityDistribution, 0.2, 1000000);
		}

		// Automatic systems
		int systemCount = 10;
		calls = 100000;
		waitDistribution = new ExponentialDistribution(DESScheduler.getRandom(), 10000);
		complexityDistribution = new NormalDistribution(DESScheduler.getRandom(), 50, 10);	

		for (int i = 0; i < systemCount; i++) {
			new AbstractDBInquiryProcess(db, calls, waitDistribution, complexityDistribution, 0.2, 1000000);
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
			simulator.simulate(new DBFrontendSimulation());
		}
		simulator.terminate();

		List<SimulationResult> results = simulator.readResults();
		for (SimulationResult r : results) {
			r.printResults();
		}
	}

}

package Vorlesung.scheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.math3.random.MersenneTwister;

public class Simulator {

	private MersenneTwister rootRandom;
	private ExecutorService executor;

	public Simulator(long mainSeed) {
		this(mainSeed, Runtime.getRuntime().availableProcessors());
	}
	
	public Simulator(long mainSeed, int threads) {
		this.rootRandom = new MersenneTwister(mainSeed);
		executor = Executors.newFixedThreadPool(threads); //TODO True multisim ;)
	}
	
	public void terminate() {
		this.executor.shutdown();
	}
	
	public void simulate(Simulation sim) {
		this.executor.execute(new SimulationWrapper(sim, rootRandom.nextLong()));
	}
	
}

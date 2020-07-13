package Vorlesung.scheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Vorlesung.modelling.ModelException;
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
		try {
			this.executor.execute(new SimulationWrapper(sim, rootRandom.nextLong()));
		} catch (ModelException e){
			this.terminate();
			System.out.println("Error in concurrent execution of multiple simulations");
			System.out.println(e.getMessage());
		}
	}
	
}

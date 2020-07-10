package Vorlesung.scheduler;

import org.apache.commons.math3.random.MersenneTwister;

public class SimulationWrapper implements Runnable {

	private Simulation simulation;
	private long seed;

	public SimulationWrapper(Simulation sim, long seed) {
		this.simulation = sim;
		this.seed = seed;
	}
	
	public void run() {
		//TODO Seperate simulations
		DESScheduler.getScheduler().execute(this.simulation, new MersenneTwister(seed));
	}
	
	public Simulation getSimulation() {
		return simulation;
	}
	
	public long getSeed() {
		return seed;
	}

}

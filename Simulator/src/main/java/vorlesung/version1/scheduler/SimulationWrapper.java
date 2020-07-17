package vorlesung.version1.scheduler;

import vorlesung.version1.modelling.ModelException;
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
		try {
			DESScheduler.getScheduler().execute(this.simulation, new MersenneTwister(seed));
		} catch (ModelException e){
			System.out.println("ERROR: "+e.toString()+" "+e.getMessage());
		}
	}
	
	public Simulation getSimulation() {
		return simulation;
	}
	
	public long getSeed() {
		return seed;
	}

}

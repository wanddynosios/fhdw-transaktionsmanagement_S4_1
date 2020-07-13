package Vorlesung.scheduler;

import java.util.PriorityQueue;

import Vorlesung.modelling.ModelException;
import org.apache.commons.math3.random.RandomGenerator;

public class DirectScheduler extends DESScheduler {
	
	private PriorityQueue<DESEvent> heap;
	private long currentTime;
	private RandomGenerator randomGenerator;

	protected DirectScheduler() {
		this.heap = new PriorityQueue<DESEvent>();
		this.currentTime = 0;
		this.randomGenerator = null;      //TODO init 
	}

	@Override
	protected void inject(DESOperation operation, long time) {
		this.heap.offer(new DESEvent(operation, time)); 
	}

	@Override
	protected void injectToFuture(DESOperation operation, long time) {
		try {
			this.heap.offer(new DESEvent(operation, this.currentTime + time));
		} catch (RuntimeException e){
			throw new ModelException("Multiple Schedulers were trying to add an Element simultaneously: "+e.getMessage());
		}
	}

	@Override
	protected long getTime() {
		return this.currentTime;
	}

	@Override
	protected RandomGenerator getRandomGenerator() {
		return this.randomGenerator;
	}

	@Override
	protected void reset(RandomGenerator randomGenerator) {
		this.currentTime = 0;
		this.heap.clear();
		this.randomGenerator = randomGenerator;
	}
	
	@Override
	public void execute(Simulation sim, RandomGenerator randomGenerator) {
		this.getScheduler().reset(randomGenerator);
		sim.injectStart();
		sim.start();
			while(!this.heap.isEmpty()) {
				try{
					DESEvent event = this.heap.poll();
					this.currentTime = event.getTime(); //Nullpointer
					event.run();
				} catch (RuntimeException e){
					throw new ModelException("Multiple Schedulers were trying to poll the last element in heap! "+e.getMessage());
				}
			}
		sim.finish();
	}
}

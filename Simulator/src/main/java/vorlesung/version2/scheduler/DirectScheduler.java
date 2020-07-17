package vorlesung.version2.scheduler;

import java.util.PriorityQueue;

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
		this.heap.offer(new DESEvent(operation, this.currentTime + time));
	}

	@Override
	protected long getTime() {
		return this.currentTime;
	}

	//TODO Remove Method
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
	public void execute(Simulation sim) {
		DESScheduler.getScheduler().reset(randomGenerator);
		sim.injectStart();
		sim.start();
			while(! this.heap.isEmpty()) {
				DESEvent event = this.heap.poll();
				this.currentTime = event.getTime();
				event.run();
			}
		sim.finish();
	}

}

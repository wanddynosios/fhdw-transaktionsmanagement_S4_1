package vorlesung.version2.evaluation;

import vorlesung.version2.evaluation.aggregation.EvaluationPersistantCharacteristic;
import vorlesung.version2.scheduler.DESScheduler;

public class EvaluationInterval extends SimulationEvaluatorWithStore {
	
	private boolean active;
	private long lastTime;

	public EvaluationInterval(String name, Object sut, EvaluationPersistantCharacteristic... characteristic) {
		super(name, sut, characteristic);
		this.active = false;
	}
	
	public void trigger() {
		long currentTime = DESScheduler.getSimulationTime();
		if(this.active) {
			this.addData(currentTime - this.lastTime);	
		}
		this.lastTime = currentTime;
		this.active = true;
	}

	public void trigger(long creationTime){
		long currentTime = DESScheduler.getSimulationTime();
		if(this.active) {
			this.addData(currentTime - creationTime);
		}
		this.active = true;
	}
	
	public void intervalStart() {
		this.lastTime = DESScheduler.getSimulationTime();
		this.active = true;
	}

	public void intervalStop() {
		if(this.active) {
			this.addData(DESScheduler.getSimulationTime() - this.lastTime);	
		}
		this.active = false;
	}
}

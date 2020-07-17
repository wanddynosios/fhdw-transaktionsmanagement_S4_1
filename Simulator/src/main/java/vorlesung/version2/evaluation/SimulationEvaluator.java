package vorlesung.version2.evaluation;

import vorlesung.version2.scheduler.SimulationResult;

import java.util.Properties;

public abstract class SimulationEvaluator {
	
	private String name;

	public SimulationEvaluator(String name, Object sut) {
		this.name = name;
		SimulationResult.getResult().register(this, sut);
	}

	public abstract Properties eval();
	
	@Override
	public String toString() {
		return name;
	}

}

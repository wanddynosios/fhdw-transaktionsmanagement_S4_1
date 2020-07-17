package vorlesung.version2.evaluation;

import java.util.Properties;

import vorlesung.version2.evaluation.aggregation.EvaluationPersistantCharacteristic;
import org.apache.commons.math3.util.ResizableDoubleArray;

public abstract class SimulationEvaluatorWithStore extends SimulationEvaluator {
	
	private EvaluationPersistantCharacteristic[] characteristic;
	private ResizableDoubleArray data;

	public SimulationEvaluatorWithStore(String name, Object sut, EvaluationPersistantCharacteristic... characteristic) {
		super(name, sut);
		this.characteristic = characteristic;
		this.data = new ResizableDoubleArray();
	}

	@Override
	public Properties eval() {
		Properties result = new Properties();
		for (EvaluationPersistantCharacteristic c : this.characteristic) {
			result.put(c.getDescription(), c.eval(this.data));
		}
		return result;
	}
	
	protected void addData(double value) {
		this.data.addElement(value);
	}
	
	protected void addData(int value) {
		this.data.addElement(value);
	}
	
	protected void addData(long value) {
		this.data.addElement(value);
	}

}

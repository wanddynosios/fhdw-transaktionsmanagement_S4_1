package vorlesung.version2.evaluation.aggregation;

import org.apache.commons.math3.util.ResizableDoubleArray;

public class CountCharacteristic implements EvaluationPersistantCharacteristic {

	@Override
	public double eval(ResizableDoubleArray data) {
		return data.getNumElements();
	}

	@Override
	public String getDescription() {
		return "SampleCounter";
	}

}
